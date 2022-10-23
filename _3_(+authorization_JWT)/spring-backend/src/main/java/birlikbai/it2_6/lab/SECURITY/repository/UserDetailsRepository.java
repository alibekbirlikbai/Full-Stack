package birlikbai.it2_6.lab.SECURITY.repository;

import birlikbai.it2_6.lab.SECURITY.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserRole, Long> {

	UserRole findByUserName(String userName);
	
}
