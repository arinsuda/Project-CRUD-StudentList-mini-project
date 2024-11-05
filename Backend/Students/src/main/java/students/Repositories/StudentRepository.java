package students.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import students.Entity.Students;

public interface StudentRepository extends JpaRepository<Students, Integer> {

}
