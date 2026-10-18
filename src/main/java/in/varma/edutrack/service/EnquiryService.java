package in.varma.edutrack.service;

import in.varma.edutrack.dto.CourseDto;
import in.varma.edutrack.dto.EnqFilterRequestDto;
import in.varma.edutrack.dto.EnquiryDto;
import in.varma.edutrack.entity.CourseEntity;

import java.util.List;

public interface EnquiryService {

    public List<CourseDto> getCourses();

    public boolean insertEnquiry(EnquiryDto enquiryDto, Integer counsellorId);

    public List<EnquiryDto> getAllEnquiries(Integer counsellorId);

    public List<EnquiryDto> getEnquiriesWithFilter(EnqFilterRequestDto filterDto, Integer counsellorId);

    public EnquiryDto getEnquiry(Integer enqId);

    public boolean updateEnquiry(EnquiryDto enquiry);

}