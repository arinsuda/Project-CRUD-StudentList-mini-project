package students.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import students.Entity.Students;

@Service
public interface StudentRepository extends JpaRepository<Students, Integer> {
}
