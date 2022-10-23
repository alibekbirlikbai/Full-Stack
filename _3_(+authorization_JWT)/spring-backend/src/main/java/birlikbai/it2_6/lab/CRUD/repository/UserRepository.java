package birlikbai.it2_6.lab.CRUD.repository;

import birlikbai.it2_6.lab.CRUD.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
