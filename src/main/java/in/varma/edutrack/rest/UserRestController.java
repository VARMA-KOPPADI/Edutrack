package in.varma.edutrack.rest;

import in.varma.edutrack.apiResponce.ApiResponse;
import in.varma.edutrack.dto.CityDto;
import in.varma.edutrack.dto.CountryDto;
import in.varma.edutrack.dto.StateDto;
import in.varma.edutrack.dto.UserRegisterDto;
import in.varma.edutrack.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class UserRestController {

    private UserService userService;

    @GetMapping("/countries")
    public ResponseEntity<ApiResponse<List<CountryDto>>> getCountries() {

        log.debug("execution started");

        ApiResponse<List<CountryDto>> response = new ApiResponse<>();
        List<CountryDto> countries = userService.getCountries();
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
        List<StateDto> states = userService.getStates(countryId);
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
        List<CityDto> cities = userService.getCities(stateId);
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


    @PostMapping("/UserRegister")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody UserRegisterDto dto) {
        ApiResponse<String> responce = new ApiResponse<>();
        if (userService.rigisterUser(dto)) {
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

}
