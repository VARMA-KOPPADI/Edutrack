package in.varma.edutrack.rest;

import in.varma.edutrack.apiResponce.ApiResponse;
import in.varma.edutrack.dto.CourseDto;
import in.varma.edutrack.dto.EnqFilterRequestDto;
import in.varma.edutrack.dto.EnquiryDto;
import in.varma.edutrack.service.EnquiryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class EnquiryRestController {

    private EnquiryService enqService;

    @PostMapping("/enquiry/{counsellorId}")
    public ResponseEntity<ApiResponse<String>> addEnquiry(@RequestBody EnquiryDto enquiryDto, @PathVariable Integer counsellorId) {

        ApiResponse<String> response = new ApiResponse<>();

        if (enqService.insertEnquiry(enquiryDto, counsellorId)) {
            response.setStatus(201);
            response.setMessage("Enquiry Added Successfully");
            response.setData("Success");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        response.setStatus(500);
        response.setMessage("Failed to Add Enquiry");
        response.setData("Failed");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/enquiries/{counsellorId}")
    public ResponseEntity<ApiResponse<List<EnquiryDto>>> getAllEnquiries( @PathVariable Integer counsellorId) {

        log.debug("Fetching enquiries for counsellor ID: {}", counsellorId);

        List<EnquiryDto> enquiries = enqService.getAllEnquiries(counsellorId);

        ApiResponse<List<EnquiryDto>> response = new ApiResponse<>();

        if (enquiries.isEmpty()) {
            response.setStatus(404);
            response.setMessage("No Enquiries Found");
            response.setData(null);

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setStatus(200);
        response.setMessage("Enquiries Fetched Successfully");
        response.setData(enquiries);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/enquiries/filter/{counsellorId}")
    public ResponseEntity<ApiResponse<List<EnquiryDto>>> getEnquiriesWithFilter(@RequestBody EnqFilterRequestDto filterDto, @PathVariable Integer counsellorId) {

        List<EnquiryDto> enquiries =
                enqService.getEnquiriesWithFilter(filterDto, counsellorId);

        ApiResponse<List<EnquiryDto>> response = new ApiResponse<>();

        if (enquiries.isEmpty()) {
            response.setStatus(404);
            response.setMessage("No Enquiries Found For Given Filters");
            response.setData(null);

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setStatus(200);
        response.setMessage("Filtered Enquiries Fetched Successfully");
        response.setData(enquiries);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/enquiry/{enqId}")
    public ResponseEntity<ApiResponse<EnquiryDto>> getEnquiry(@PathVariable Integer enqId) {

        EnquiryDto enquiry = enqService.getEnquiry(enqId);

        ApiResponse<EnquiryDto> response = new ApiResponse<>();

        response.setStatus(200);
        response.setMessage("Enquiry Fetched Successfully");
        response.setData(enquiry);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/enquiry")
    public ResponseEntity<ApiResponse<String>> updateEnquiry(@RequestBody EnquiryDto enquiryDto) {

        ApiResponse<String> response = new ApiResponse<>();

        if (enqService.updateEnquiry(enquiryDto)) {
            response.setStatus(200);
            response.setMessage("Enquiry Updated Successfully");
            response.setData("Success");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setStatus(404);
        response.setMessage("Enquiry Not Found");
        response.setData("Failed");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<CourseDto>>> getCourses() {

        List<CourseDto> courses = enqService.getCourses();

        ApiResponse<List<CourseDto>> response = new ApiResponse<>();

        if (courses.isEmpty()) {
            response.setStatus(404);
            response.setMessage("No Courses Found");
            response.setData(null);

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setStatus(200);
        response.setMessage("Courses Fetched Successfully");
        response.setData(courses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

