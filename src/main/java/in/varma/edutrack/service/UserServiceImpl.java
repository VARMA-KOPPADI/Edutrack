package in.varma.edutrack.service;

import in.varma.edutrack.dto.CityDto;
import in.varma.edutrack.dto.CountryDto;
import in.varma.edutrack.dto.StateDto;
import in.varma.edutrack.dto.UserRegisterDto;
import in.varma.edutrack.entity.CityEntity;
import in.varma.edutrack.entity.CountryEntity;
import in.varma.edutrack.entity.StateEntity;
import in.varma.edutrack.entity.UserEntity;
import in.varma.edutrack.repo.CityRepository;
import in.varma.edutrack.repo.CountryRepository;
import in.varma.edutrack.repo.StateRepository;
import in.varma.edutrack.repo.UserRepo;
import in.varma.edutrack.utility.EmailSender;
import in.varma.edutrack.utility.RandomPasswordGenerator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private CountryRepository countryRepo;
    private StateRepository stateRepo;
    private CityRepository cityRepo;
    private UserRepo userRepo;
    private ModelMapper mapper;
    private EmailSender emailSender;

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
    public boolean rigisterUser(UserRegisterDto urDto) {

        if (userRepo.existsByEmail(urDto.getEmail())) {
            return false;
        }

        UserEntity userEntity = mapper.map(urDto, UserEntity.class);

        String tempPwd = RandomPasswordGenerator.randomPassword(6);

        userEntity.setTempPwd(tempPwd);
        userEntity.setPwdUpdated("NO");

        UserEntity savedUser = userRepo.save(userEntity);

        String subject = savedUser.getName() + " - Your account is created";
        String body = "Your temporary password is: " + savedUser.getTempPwd();

        emailSender.sendEmail(
                subject,
                body,
                savedUser.getEmail()
        );

        return true;
    }

}
