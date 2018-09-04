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


import xml.projekat.Model.AccommodationType;
import xml.projekat.Service.AccommodationTypeService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/accommodationTypes")
public class AccommodationTypeController {

	@Autowired
	private AccommodationTypeService accommodationTypeService;

	// Vraca tip smestaja
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AccommodationType> getAccommodationType(@PathVariable Long id) {
		System.out.println("\n GET ACCOMMODATION TYPE:  " + id + "\n");
		AccommodationType accommodationType = accommodationTypeService.findById(id);

		if (accommodationType != null) {
			return new ResponseEntity<AccommodationType>(accommodationType, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca tipove smestaja
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<AccommodationType>> getAccommodationTypes() {
		System.out.println("\n GET ACCOMMODATION TYPES " + "\n");
		ArrayList<AccommodationType> accommodationTypes = (ArrayList<AccommodationType>) accommodationTypeService.findAll();

		if (accommodationTypes != null) {
			return new ResponseEntity<Collection<AccommodationType>>(accommodationTypes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Kreira tip smestaja
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Collection<AccommodationType>> createAccommodationType(@RequestBody String type) {
		System.out.println("\n CREATE ACCOMMODATION TYPE " + "\n");
			
		ArrayList<AccommodationType> accommodationType = (ArrayList<AccommodationType>) accommodationTypeService.createAccommodationType(type);

		if (accommodationType != null) {
			return new ResponseEntity<Collection<AccommodationType>>(accommodationType, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// Brise tip smestaja
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Collection<AccommodationType>> deleteAccommodationType(@PathVariable Long id) {
		System.out.println("\n DELETE ACCOMMODATION TYPE:  " + id + "\n");
		ArrayList<AccommodationType> deletedAccommodationType = (ArrayList<AccommodationType>) accommodationTypeService.deleteAccommodationType(id);

		if (deletedAccommodationType != null) {
			return new ResponseEntity<Collection<AccommodationType>>(deletedAccommodationType, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

	// Izmena tip smestaja
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Collection<AccommodationType>> editAccommodationType(@PathVariable Long id, @RequestBody String type) {
		System.out.println("\n EDIT ACCOMMODATION TYPE:  " + id + "\n");
		ArrayList<AccommodationType> deletedAccommodationType = (ArrayList<AccommodationType>) accommodationTypeService.editAccommodationType(id, type);

		if (deletedAccommodationType != null) {
			return new ResponseEntity<Collection<AccommodationType>>(deletedAccommodationType, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
