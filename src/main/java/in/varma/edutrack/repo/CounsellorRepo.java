package in.varma.edutrack.repo;

import in.varma.edutrack.entity.CounsellorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounsellorRepo extends JpaRepository<CounsellorEntity, Integer> {
    public boolean existsByEmail(String email);
    public CounsellorEntity findByEmailAndPwd(String email, String password);

   public CounsellorEntity findByEmail(String email);
}
