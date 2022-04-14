package th.ac.mahidol.ict.heroesbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.mahidol.ict.heroesbackend.model.Superhuman;

public interface SuperhumanRepository extends JpaRepository<Superhuman, Integer> {
}
