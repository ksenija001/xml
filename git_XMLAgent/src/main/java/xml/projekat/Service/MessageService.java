package xml.projekat.Service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Dto.MessageDto;
import xml.projekat.Model.Message;
import xml.projekat.Repository.MessageRepository;
import xml.projekat.Repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Message> findByReceiver(Long id){
		return messageRepository.findByReceiverId(id);
	}
	
	public List<Message> findBySender(Long id){
		return messageRepository.findBySenderId(id);
	}
	
	public Message createMessage(MessageDto messageDto) {
		Message message = new Message();
		message.setContent(messageDto.getContent());
		message.setReceiver(userRepository.findById(messageDto.getReceiverId()));
		message.setSender(userRepository.findById(messageDto.getSenderId()));
		messageRepository.save(message);
		return message;
		
	}
}
