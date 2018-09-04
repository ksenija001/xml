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

import xml.projekat.Dto.MessageDto;
import xml.projekat.Model.Message;
import xml.projekat.Service.MessageService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	// Vraca poslate poruke
	@RequestMapping(value="/sender/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Message>> getMessagesBySender(@PathVariable Long id) {
		System.out.println("\n GET MESSAGES BY SENDER " + id + "\n");
		ArrayList<Message> messages = (ArrayList<Message>) messageService.findBySender(id);

		if (messages != null) {
			return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Vraca primljene poruke
	@RequestMapping(value="/receiver/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Message>> getMessagesByReceiver(@PathVariable Long id) {
		System.out.println("\n GET MESSAGES BY RECEIVER " + id + "\n");
		ArrayList<Message> messages = (ArrayList<Message>) messageService.findByReceiver(id); 

		if (messages != null) {
			return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Kreira novu poruku
	@RequestMapping(value="/sendMessageTo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Message> sendMessage(@RequestBody MessageDto messageDto) {
		System.out.println("\n SEND MESSAGE FROM " + messageDto.getSenderId() + " TO " + messageDto.getReceiverId()  + "\n");
			
		Message message = messageService.createMessage(messageDto);

		if (message != null) {
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
