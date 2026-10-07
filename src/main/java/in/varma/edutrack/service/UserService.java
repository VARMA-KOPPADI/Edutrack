package in.varma.edutrack.service;

import in.varma.edutrack.dto.CityDto;
import in.varma.edutrack.dto.CountryDto;
import in.varma.edutrack.dto.StateDto;
import in.varma.edutrack.dto.UserRegisterDto;

import java.util.List;

public interface UserService {

    public List<CountryDto> getCountries();

    public List<StateDto> getStates(Integer countryId);

    public List<CityDto> getCities(Integer stateId);
    public boolean rigisterUser(UserRegisterDto urDto);
}
