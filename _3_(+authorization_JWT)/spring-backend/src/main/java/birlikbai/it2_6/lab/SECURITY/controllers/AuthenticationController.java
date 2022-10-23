package birlikbai.it2_6.lab.SECURITY.controllers;

import birlikbai.it2_6.lab.SECURITY.config.JWTTokenHelper;
import birlikbai.it2_6.lab.SECURITY.entities.UserRole;
import birlikbai.it2_6.lab.SECURITY.requests.AuthenticationRequest;
import birlikbai.it2_6.lab.SECURITY.responses.LoginResponse;
import birlikbai.it2_6.lab.SECURITY.responses.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserRole userRole =(UserRole)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(userRole.getUsername());
		
		LoginResponse response=new LoginResponse();
		response.setToken(jwtToken);
		

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user){
		UserRole userRoleObj =(UserRole) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo=new UserInfo();
		userInfo.setUserName(userRoleObj.getUserName());
		userInfo.setFirstName(userRoleObj.getFirstName());
		userInfo.setLastName(userRoleObj.getLastName());
		userInfo.setRoles(userRoleObj.getAuthorities().toArray());
		
		
		return ResponseEntity.ok(userInfo);
		
		
		
	}
}
