package in.varma.edutrack.repo;

import in.varma.edutrack.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
}
