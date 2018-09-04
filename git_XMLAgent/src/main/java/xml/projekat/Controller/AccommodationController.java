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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//import com.google.gson.JsonSyntaxException;

import xml.projekat.Dto.AccommodationDto;
import xml.projekat.Model.Accommodation;
import xml.projekat.Service.AccommodationService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/accommodations")
public class AccommodationController {

	@Autowired
	private AccommodationService accommodationService;

	// Vraca smestaj
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Accommodation> getAccommodation(@PathVariable Long id) {
		System.out.println("\n GET ACCOMMODATION:  " + id + "\n");
		Accommodation accommodation = accommodationService.findById(id);

		if (accommodation != null) {
			return new ResponseEntity<Accommodation>(accommodation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca smestaje
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Accommodation>> getAccommodations() {
		System.out.println("\n GET ACCOMMODATIONS " + "\n");
		ArrayList<Accommodation> accommodations = (ArrayList<Accommodation>) accommodationService.findAll();

		if (accommodations != null) {
			return new ResponseEntity<Collection<Accommodation>>(accommodations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Vraca smestaje za agenta
	@RequestMapping(path = "/getMyUnits/{username}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String getMyUnits(@PathVariable String username) throws Exception {
		ArrayList<Accommodation> units = new ArrayList<>();
		
		for(Accommodation a : accommodationService.findAll()){
			
			if(a.getOwner()!=null){
				
				if(a.getOwner().getUsername().equals(username)){
					System.out.println(a.getId());
					units.add(a);
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		   	   
	    String u = mapper.writeValueAsString(units);
	    //System.out.println(username+"'s units:");
	    //System.out.println(u);
	    return u;
	}

	
	// Kreiranje smestaja
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Accommodation> createAccommodation(@RequestBody AccommodationDto accommodationDto) {
		System.out.println("\n CREATE ACCOMMODATION" + "\n");

		Accommodation accommodation = accommodationService.createAccommodation(accommodationDto);

		if (accommodation != null) {
			return new ResponseEntity<Accommodation>(accommodation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	
		
		
	
		
		
	}

	// Brisanje smestaja
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable Long id) {
		System.out.println("\n DELETE ACCOMMODATION" + id + "\n");
		Accommodation detedAccommodation = accommodationService.deleteAccommodation(id);

		if (detedAccommodation != null) {
			return new ResponseEntity<Accommodation>(detedAccommodation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
	
	// Zauzimanje smestaja - agent
	@RequestMapping(value = "busy/{id}", method = RequestMethod.POST)
	public ResponseEntity<Accommodation> busyAccommodation(@PathVariable Long id) {
		System.out.println("\n SET BUSY ACCOMMODATION" + id + "\n");
		Accommodation busyAccommodation = accommodationService.busyAccommodation(id);

		if (busyAccommodation != null) {
			return new ResponseEntity<Accommodation>(busyAccommodation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}
