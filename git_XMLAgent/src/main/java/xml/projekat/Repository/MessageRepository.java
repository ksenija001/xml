package xml.projekat.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.projekat.Model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	public List<Message> findBySenderId(long senderId);
	
	public Message deleteBySenderId(long senderId);
	
	public Message deleteByReceiverId(long receiverId);
	
	public List<Message> findByReceiverId(long receiverId);
}
