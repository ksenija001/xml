package xml.projekat.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.projekat.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findById(Long id);

	public User findByEmailAndPassword(String email, String password);

	public User findByEmail(String email);

	public List<User> findByActivated(boolean activated);

	public List<User> findByBlocked(boolean blocked);
}
