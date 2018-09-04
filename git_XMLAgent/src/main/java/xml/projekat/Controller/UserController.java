package xml.projekat.Controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.projekat.Model.User;
import xml.projekat.Service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Vraca korisnika
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		System.out.println("\n GET USER:  " + id + "\n");
		User user = userService.findById(id);

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca korisnike
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<User>> getUsers() {
		System.out.println("\n GET USERS " + "\n");
		ArrayList<User> users = (ArrayList<User>) userService.findAll();

		if (users != null) {
			return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Izmena korisnika
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		System.out.println("\n UPDATE USER:  " + id + "\n");
		User updatedUser = userService.updateUser(id, user);

		if (updatedUser != null) {
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Brisanje korisnika
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Collection<User>> deleteUser(@PathVariable Long id) {
		System.out.println("\n DELETE USER:  " + id + "\n");
		ArrayList<User> deletedUser = (ArrayList<User>) userService.deleteUser(id);

		if (deletedUser != null) {
			return new ResponseEntity<Collection<User>>(deletedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Blokiranje korisnika
	@RequestMapping(value = "/block/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Collection<User>> blockUser(@PathVariable Long id) {
		System.out.println("\n BLOCK USER:  " + id + "\n");
		ArrayList<User> blockedUser = (ArrayList<User>) userService.blockUser(id);

		if (blockedUser != null) {
			return new ResponseEntity<Collection<User>>(blockedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

	// Odblokiranje korisnika
	@RequestMapping(value = "/unblock/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Collection<User>> unblockUser(@PathVariable Long id) {
		System.out.println("\n UNBLOCK USER:  " + id + "\n");
		ArrayList<User> unblockedUser = (ArrayList<User>) userService.unblockUser(id);

		if (unblockedUser != null) {
			return new ResponseEntity<Collection<User>>(unblockedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	// Aktiviranje korisnika
	@RequestMapping(value = "/activate/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Collection<User>> activateUser(@PathVariable Long id) {
		System.out.println("\n ACTIVATE USER:  " + id + "\n");
		ArrayList<User> activatedUser = (ArrayList<User>) userService.activateUser(id);

		if (activatedUser != null) {
			return new ResponseEntity<Collection<User>>(activatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Deaktiviranje korisnika
	@RequestMapping(value = "/deactivate/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Collection<User>> deactivateUser(@PathVariable Long id) {
		System.out.println("\n DEACTIVATE USER:  " + id + "\n");
		ArrayList<User> deactivatedUser = (ArrayList<User>) userService.deactivateUser(id);

		if (deactivatedUser != null) {
			return new ResponseEntity<Collection<User>>(deactivatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
