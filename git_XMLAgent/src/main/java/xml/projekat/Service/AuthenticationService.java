package xml.projekat.Service;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Model.User;
import xml.projekat.Repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	public User findUser(User user) {
		return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

}
