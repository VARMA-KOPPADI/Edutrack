package in.varma.edutrack.repo;

import in.varma.edutrack.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    public boolean existsByEmail(String Email);
}
