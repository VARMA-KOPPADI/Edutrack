package in.varma.edutrack.rest;

import in.varma.edutrack.apiResponce.ApiResponse;
import in.varma.edutrack.dto.*;
import in.varma.edutrack.service.CounsellorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CounsellorRestController {

    private CounsellorService counsellorService;


    @GetMapping("/countries")
    public ResponseEntity<ApiResponse<List<CountryDto>>> getCountries() {

        log.debug("execution started");

        ApiResponse<List<CountryDto>> response = new ApiResponse<>();
        List<CountryDto> countries = counsellorService.getCountries();
        if (countries.isEmpty()) {
            response.setStatus(500);
            response.setMessage("No countries found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.setStatus(200);
            response.setMessage("Fetched Countries Successfully");
            response.setData(countries);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @GetMapping("/states/{countryId}")
    public ResponseEntity<ApiResponse<List<StateDto>>> getStates(@PathVariable Integer countryId) {
        ApiResponse<List<StateDto>> response = new ApiResponse<>();
        List<StateDto> states = counsellorService.getStates(countryId);
        if (states.isEmpty()) {
            response.setStatus(500);
            response.setMessage("No States Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.setStatus(200);
            response.setMessage("States fetched Successfully");
            response.setData(states);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/cities/{stateId}")
    public ResponseEntity<ApiResponse<List<CityDto>>> getCities(@PathVariable Integer stateId) {
        ApiResponse<List<CityDto>> response = new ApiResponse<>();
        List<CityDto> cities = counsellorService.getCities(stateId);
        if (cities.isEmpty()) {
            response.setStatus(500);
            response.setMessage("No Cities Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.setStatus(200);
            response.setMessage("Cities fetched Successfully");
            response.setData(cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @PostMapping("/CounsellorRegister")
    public ResponseEntity<ApiResponse<String>> registerCounsellor(@RequestBody CounsellorRegisterDto dto) {
        ApiResponse<String> responce = new ApiResponse<>();
        if (counsellorService.registerCounsellor(dto)) {
            responce.setStatus(200);
            responce.setMessage("Registration Successful");
            responce.setData("Success");
            return new ResponseEntity<>(responce, HttpStatus.OK);
        }

        responce.setStatus(500);
        responce.setMessage("Registration Failed");
        responce.setData("Failed");
        return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<CounsellorDto>> loginUser(@RequestBody LogInDto loginDto) {

        CounsellorDto counsellorDto = counsellorService.loginCounsellor(loginDto);

        ApiResponse<CounsellorDto> response = new ApiResponse<>();

        if (counsellorDto != null) {
            response.setStatus(200);
            response.setMessage("Login Successful");
            response.setData(counsellorDto);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setStatus(401);
        response.setMessage("Invalid Email or Password");
        response.setData(null);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(
            @RequestBody ResetPasswordDto resetDto) {

        ApiResponse<String> response = new ApiResponse<>();

        if (counsellorService.resetPassword(resetDto)) {
            response.setStatus(200);
            response.setMessage("Password Reset Successful");
            response.setData("Success");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setStatus(400);
        response.setMessage("Password Reset Failed. Check email or password confirmation.");
        response.setData("Failed");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
