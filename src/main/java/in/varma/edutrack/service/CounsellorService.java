package in.varma.edutrack.service;

import in.varma.edutrack.dto.*;

import java.util.List;

public interface CounsellorService {

    public List<CountryDto> getCountries();
    public List<StateDto> getStates(Integer countryId);
    public List<CityDto> getCities(Integer stateId);
    public boolean registerCounsellor(CounsellorRegisterDto userDto);
    public CounsellorDto loginCounsellor(LogInDto login);

    public boolean resetPassword(ResetPasswordDto resetDto);

    public DashboardResponceDto getDashboardInfo(Integer counsellorId);
}
