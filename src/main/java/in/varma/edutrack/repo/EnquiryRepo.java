package in.varma.edutrack.repo;

import in.varma.edutrack.entity.EnquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryRepo extends JpaRepository<EnquiryEntity, Integer> {

    public List<EnquiryEntity> findByCounsellorEntityCounsellorId(Integer counsellorId);
}
