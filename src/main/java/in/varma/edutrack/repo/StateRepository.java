package in.varma.edutrack.repo;

import in.varma.edutrack.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<StateEntity, Integer> {

    public List<StateEntity> findByCountryCountryId(Integer countryId);
}
