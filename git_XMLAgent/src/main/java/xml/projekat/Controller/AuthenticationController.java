package xml.projekat.Controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.projekat.Dto.AlertMessageDto;
import xml.projekat.Dto.LoginDto;

import xml.projekat.Model.User;
import xml.projekat.Repository.UserRepository;
import xml.projekat.Service.AuthenticationService;
import xml.projekat.Service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/authentication")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserRepository userRepository;
	
	@Value("${server.port}")
	private String port;
	
	@Autowired
	private HttpSession httpSession;

	// Korisnika vraca 
	@RequestMapping(value = "check", method = RequestMethod.GET, produces = "application/json")
	public User checkRights() throws AuthenticationException {
		System.out.println("\n CHECK USER");
		try {
			User user = userService.findById(((User) httpSession.getAttribute("user")).getId());
			return user;
		} catch (Exception e) {
			throw new AuthenticationException("Forbidden.");
		}
	}
	
	// Logovanje
	@RequestMapping(value = "signin", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signin(@RequestBody LoginDto loginDto) {
		System.out.println("\n LOGOVANJE KORISNIKA " + loginDto.getEmail() + "," + loginDto.getPassword());

		User user = authenticationService.findUser(loginDto.transformLoginUser(loginDto));

		if (user != null) {
			if (user.isActivated()==false) {
				System.out.println(" Nije aktiviran");
				return new ResponseEntity<AlertMessageDto>(new AlertMessageDto("User is not activated!"), HttpStatus.UNAUTHORIZED);
			} else {
				System.out.println(" Aktiviran je");
				httpSession.setAttribute("user", user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}
		
		System.out.println(" Nije uspelo");
		return new ResponseEntity<AlertMessageDto>(new AlertMessageDto("User does not exist!"),HttpStatus.BAD_REQUEST);
	}

	// Odjavljivanje
	@RequestMapping(value = "signout", method = RequestMethod.GET)
	public ResponseEntity<String> signout() {
		System.out.println("\n SIGNOUT USER");
		httpSession.invalidate();
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	


}
