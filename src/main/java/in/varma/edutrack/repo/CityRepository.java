package in.varma.edutrack.repo;

import in.varma.edutrack.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    public List<CityEntity> findByStateStateId(Integer stateId);
}
