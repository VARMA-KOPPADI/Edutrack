package in.varma.edutrack.service;

import in.varma.edutrack.dto.CourseDto;
import in.varma.edutrack.dto.EnqFilterRequestDto;
import in.varma.edutrack.dto.EnquiryDto;
import in.varma.edutrack.entity.CounsellorEntity;
import in.varma.edutrack.entity.CourseEntity;
import in.varma.edutrack.entity.EnquiryEntity;
import in.varma.edutrack.repo.CounsellorRepo;
import in.varma.edutrack.repo.CourseRepo;
import in.varma.edutrack.repo.EnquiryRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnquiryServiceImpl implements EnquiryService {

    private EnquiryRepo enqRepo;

    private CounsellorRepo counsellorRepo;

    private CourseRepo courseRepo;

    private ModelMapper mapper;

    @Override
    public boolean insertEnquiry(EnquiryDto enquiryDto, Integer counsellorId) {

        CounsellorEntity counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
        CourseEntity course = courseRepo.findById(enquiryDto.getCourseId()).orElseThrow();

        EnquiryEntity enq = new EnquiryEntity();

        // copy dto obj data into entity obj
        BeanUtils.copyProperties(enquiryDto, enq);

        // association
        enq.setCourseEntity(course);
        enq.setCounsellorEntity(counsellor);

        EnquiryEntity savedEnq = enqRepo.save(enq);

        return savedEnq.getEnqId() != null;
    }

    @Override
    public List<EnquiryDto> getAllEnquiries(Integer counsellorId) {
        List<EnquiryEntity> enquiryEntityList = enqRepo.findByCounsellorEntityCounsellorId(counsellorId);

        List<EnquiryDto> dtoList = enquiryEntityList.stream()
                .map(enquiryEntity -> mapper.map(enquiryEntity, EnquiryDto.class)).toList();
        return dtoList;
    }

    @Override
    public List<EnquiryDto> getEnquiriesWithFilter(EnqFilterRequestDto filterDto, Integer counsellorId) {

        EnquiryEntity enq = new EnquiryEntity();

        // QBE implementation (Query By Example for Dynamic Query Preparation)
        CounsellorEntity counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
        enq.setCounsellorEntity(counsellor);

        if (filterDto.getClassMode() != null && !"".equals(filterDto.getClassMode())) {
            enq.setClassMode(filterDto.getClassMode());
        }

        if (filterDto.getCourseId() != null && filterDto.getCourseId() > 0) {
            CourseEntity course = courseRepo.findById(filterDto.getCourseId()).orElseThrow();
            enq.setCourseEntity(course);
        }

        if (filterDto.getEnqStatus() != null && !"".equals(filterDto.getEnqStatus())) {
            enq.setEnqStatus(filterDto.getEnqStatus());
        }

        List<EnquiryEntity> all = enqRepo.findAll(Example.of(enq));
        List<EnquiryDto> enquiryDtos = all.stream().map(enquiryEntity -> mapper.map(enquiryEntity, EnquiryDto.class)).toList();
        return enquiryDtos;
    }

    @Override
    public EnquiryDto getEnquiry(Integer enqId) {
        EnquiryEntity enquiryEntity = enqRepo.findById(enqId).orElseThrow();
        EnquiryDto enquiryDto = mapper.map(enquiryEntity, EnquiryDto.class);
        return enquiryDto;
    }

    @Override
    public boolean updateEnquiry(EnquiryDto enquiryDto) {

        Optional<EnquiryEntity> byId = enqRepo.findById(enquiryDto.getEnqId());

        if (byId.isPresent()) {
            EnquiryEntity enquiry = byId.get();
            enquiry.setEnqStatus(enquiryDto.getEnqStatus());
            enqRepo.save(enquiry);
            return true;
        }
        return false;
    }

    @Override
    public List<CourseDto> getCourses() {
        List<CourseEntity> courseEntityList = courseRepo.findAll();
        List<CourseDto> courseDtos = courseEntityList.stream().map(entity -> mapper.map(entity, CourseDto.class)).toList();
        return courseDtos;
    }
}
