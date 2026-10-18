package in.varma.edutrack.service;

import in.varma.edutrack.dto.CourseDto;
import in.varma.edutrack.entity.CourseEntity;
import in.varma.edutrack.repo.CourseRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepo courseRepo;

    private ModelMapper modelmapper;

    @Override
    public List<CourseDto> getCourses() {
        List<CourseEntity> courseEntityList = courseRepo.findAll();
        List<CourseDto> dtoList = courseEntityList.stream()
                .map(course -> modelmapper.map(course, CourseDto.class))
                .toList();
        return dtoList;
    }

}