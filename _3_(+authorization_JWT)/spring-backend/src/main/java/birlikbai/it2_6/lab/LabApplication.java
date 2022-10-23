package birlikbai.it2_6.lab;

import birlikbai.it2_6.lab.SECURITY.entities.Authority;
import birlikbai.it2_6.lab.SECURITY.entities.UserRole;
import birlikbai.it2_6.lab.SECURITY.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LabApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }


    @PostConstruct
    protected void init() {

        // ADMIN
        List<Authority> authorityList_admin=new ArrayList<>();

        authorityList_admin.add(createAuthority("ADMIN","Admin role"));
        authorityList_admin.add(createAuthority("USER","User role"));

        UserRole admin=new UserRole();

        admin.setUserName("test_ADMIN_userName");
        admin.setFirstName("test_ADMIN_firstName");
        admin.setLastName("test_ADMIN_lastName");

        admin.setPassword(passwordEncoder.encode("test_ADMIN_password"));
        admin.setEnabled(true);
        admin.setAuthorities(authorityList_admin);

        userDetailsRepository.save(admin);



        // USER
        List<Authority> authorityList_user=new ArrayList<>();

        authorityList_user.add(createAuthority("USER","User role"));

        UserRole userRole =new UserRole();

        userRole.setUserName("test_USER_userName");
        userRole.setFirstName("test_USER_firstName");
        userRole.setLastName("test_USER_lastName");

        userRole.setPassword(passwordEncoder.encode("test_USER_password"));
        userRole.setEnabled(true);
        userRole.setAuthorities(authorityList_user);

        userDetailsRepository.save(userRole);



    }


    private Authority createAuthority(String roleCode,String roleDescription) {
        Authority authority=new Authority();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }

}
