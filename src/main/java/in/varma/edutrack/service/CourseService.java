package in.varma.edutrack.service;


import in.varma.edutrack.dto.CourseDto;
import in.varma.edutrack.entity.CourseEntity;

import java.util.List;

public interface CourseService {

    public List<CourseDto> getCourses();

}