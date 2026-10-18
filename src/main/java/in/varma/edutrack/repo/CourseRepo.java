package in.varma.edutrack.repo;

import in.varma.edutrack.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<CourseEntity,Integer> {
}
