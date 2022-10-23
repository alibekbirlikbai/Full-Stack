package birlikbai.it2_6.lab.SECURITY.services;

import birlikbai.it2_6.lab.SECURITY.entities.UserRole;
import birlikbai.it2_6.lab.SECURITY.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserRole userRole =userDetailsRepository.findByUserName(username);
		
		if(null== userRole) {
			throw new UsernameNotFoundException("User Not Found with userName "+username);
		}
		return userRole;
	}

}
