package xml.projekat.Service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import xml.projekat.Model.Message;
import xml.projekat.Model.Reservation;
import xml.projekat.Model.User;

import xml.projekat.Repository.MessageRepository;
import xml.projekat.Repository.ReservationRepository;
import xml.projekat.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	


	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(long id) {
		return userRepository.findById(id);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(Long id, User user) {
		User userToUpdate = userRepository.findById(id);
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setFirstName(user.getFirstName());
		userToUpdate.setLastName(user.getLastName());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setActivated(user.isActivated());
		userRepository.save(userToUpdate);
		return userToUpdate;
	}
	
	public List<User> deleteUser(Long id) {
		
		for(Reservation r : reservationRepository.findAll()) {
			if(r.getReservedBy().getId().equals(id)) {
				reservationRepository.delete(r.getId());
			}
		}
		
		for(Message m : messageRepository.findAll()) {
			if(m.getReceiver().getId().equals(id)) {
				messageRepository.delete(m.getId());
			}
			if(m.getSender().getId().equals(id)) {
				messageRepository.delete(m.getId());
			}
		}
	
		User deletedUser = userRepository.findById(id);
		userRepository.delete(deletedUser);
		
		return userRepository.findAll();
	}
	
	public List<User> blockUser(Long id){
		User blockedUser = userRepository.findById(id);
		blockedUser.setBlocked(true);
		userRepository.save(blockedUser);
		return userRepository.findAll();
	}
	
	public List<User> activateUser(Long id) {
		User activatedUser = userRepository.findById(id);
		activatedUser.setActivated(true);
		userRepository.save(activatedUser);
		return userRepository.findAll();
	}
	
	public List<User> unblockUser(Long id){
		User blockedUser = userRepository.findById(id);
		blockedUser.setBlocked(false);
		userRepository.save(blockedUser);
		return userRepository.findAll();
	}
	
	public List<User> deactivateUser(Long id) {
		User activatedUser = userRepository.findById(id);
		activatedUser.setActivated(false);
		userRepository.save(activatedUser);
		return userRepository.findAll();
	}
	
	public List<User> findActivatedUser(){
		return userRepository.findByActivated(true);
	}
	
	public List<User> findBlockedUser(){
		return userRepository.findByBlocked(true);
	}
}


