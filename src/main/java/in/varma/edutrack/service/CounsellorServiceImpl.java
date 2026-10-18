package in.varma.edutrack.service;

import in.varma.edutrack.dto.*;
import in.varma.edutrack.entity.*;
import in.varma.edutrack.exception.CityNotFoundException;
import in.varma.edutrack.exception.CountryNotFoundException;
import in.varma.edutrack.exception.DuplicateEmailException;
import in.varma.edutrack.exception.StateNotFoundException;
import in.varma.edutrack.repo.*;
import in.varma.edutrack.utility.EmailSender;
import in.varma.edutrack.utility.RandomPasswordGenerator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CounsellorServiceImpl implements CounsellorService {

    private CountryRepository countryRepo;
    private StateRepository stateRepo;
    private CityRepository cityRepo;
    private CounsellorRepo counsellorRepo;
    private ModelMapper mapper;
    private EmailSender emailSender;
    private EnquiryRepo enqRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<CountryDto> getCountries() {
        List<CountryEntity> countries = countryRepo.findAll();

        return countries.stream()
                .map(country -> mapper.map(country, CountryDto.class))
                .toList();
    }

    @Override
    public List<StateDto> getStates(Integer countryId) {
        List<StateEntity> states = stateRepo.findByCountryCountryId(countryId);

        return states.stream()
                .map(state -> mapper.map(state, StateDto.class))
                .toList();
    }

    @Override
    public List<CityDto> getCities(Integer stateId) {
        List<CityEntity> cities = cityRepo.findByStateStateId(stateId);

        return cities.stream()
                .map(city -> mapper.map(city, CityDto.class))
                .toList();
    }


    @Override
    public boolean registerCounsellor(CounsellorRegisterDto userDto) {

        if (counsellorRepo.existsByEmail(userDto.getEmail())) {
            throw new DuplicateEmailException("Email already Registered");
        }

        CountryEntity country = countryRepo.findById(userDto.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException("Country not found"));

        StateEntity state = stateRepo.findById(userDto.getStateId())
                .orElseThrow(() -> new StateNotFoundException("State not found"));

        CityEntity city = cityRepo.findById(userDto.getCityId())
                .orElseThrow(() -> new CityNotFoundException("City not found"));


        CounsellorEntity counsellorEntity = new CounsellorEntity();

        counsellorEntity.setName(userDto.getName());
        counsellorEntity.setEmail(userDto.getEmail());
        counsellorEntity.setPhno(userDto.getPhno());

        counsellorEntity.setCountry(country);
        counsellorEntity.setState(state);
        counsellorEntity.setCity(city);

        String tempPwd = RandomPasswordGenerator.randomPassword(6);

        counsellorEntity.setPwd(passwordEncoder.encode(tempPwd));
        counsellorEntity.setPwdUpdated("NO");

        CounsellorEntity saved = counsellorRepo.save(counsellorEntity);

        String subject = saved.getName() + " - Your account is created";
        String body = "Your temporary password is: " + tempPwd;

        emailSender.sendEmail(subject, body, saved.getEmail());

        return true;
    }

    @Override
    public CounsellorDto loginCounsellor(LogInDto loginDto) {

        CounsellorEntity counsellorEntity = counsellorRepo.findByEmail(loginDto.getEmail());

        if (counsellorEntity == null) {
            return null;
        }

        if (passwordEncoder.matches(loginDto.getPassword(), counsellorEntity.getPwd())) {
            return mapper.map(counsellorEntity, CounsellorDto.class);
        }

        return null;
    }

    @Override
    public boolean resetPassword(ResetPasswordDto resetDto) {

        CounsellorEntity counsellorEntity = counsellorRepo.findByEmail(resetDto.getEmail());

        if (counsellorEntity == null) {
            return false;
        }

        if (!resetDto.getNewPassword().equals(resetDto.getConfirmPassword())) {
            return false;
        }

        counsellorEntity.setPwd(passwordEncoder.encode(resetDto.getNewPassword()));
        counsellorEntity.setPwdUpdated("YES");

        counsellorRepo.save(counsellorEntity);

        return true;
    }

    @Override
    public DashboardResponceDto getDashboardInfo(Integer counsellorId) {

        List<EnquiryEntity> enqsList = enqRepo.findByCounsellorEntityCounsellorId(counsellorId);

        int totalCnt = enqsList.size();

        Map<String, Long> statusWiseCnt = enqsList.stream()
                .collect(Collectors.groupingBy(EnquiryEntity::getEnqStatus, Collectors.counting()));

        int openCnt = statusWiseCnt.getOrDefault("OPEN", 0l).intValue();
        int enrolledCnt = statusWiseCnt.getOrDefault("ENROLLED", 0l).intValue();
        int lostCnt = statusWiseCnt.getOrDefault("LOST", 0l).intValue();

        DashboardResponceDto responseDto = DashboardResponceDto.builder()
                .totalEnqs(totalCnt)
                .openEnqs(openCnt)
                .enrolledEnqs(enrolledCnt)
                .lostEnqs(lostCnt)
                .build();
        return responseDto;
    }

}
