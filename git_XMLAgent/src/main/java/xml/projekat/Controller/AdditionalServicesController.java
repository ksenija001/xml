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

import xml.projekat.Model.AdditionalService;
import xml.projekat.Service.AdditionalServiceServices;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/additionalServices")
public class AdditionalServicesController {

	@Autowired
	private AdditionalServiceServices additionalServicesService;

	// Vraca dodatnu uslugu
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AdditionalService> getAdditionalService(@PathVariable Long id) {
		System.out.println("\n GET ADDITIONAL SERVICE:  " + id + "\n");
		AdditionalService additionalService = additionalServicesService.findById(id);

		if (additionalService != null) {
			return new ResponseEntity<AdditionalService>(additionalService, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca dodatne usluge
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<AdditionalService>> getAdditionalServices() {
		System.out.println("\n GET ADDITIONAL SERVICES TYPES " + "\n");
		ArrayList<AdditionalService> additionalServices = (ArrayList<AdditionalService>) additionalServicesService
				.findAll();

		if (additionalServices != null) {
			return new ResponseEntity<Collection<AdditionalService>>(additionalServices, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Kreira uslugu
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Collection<AdditionalService>> createAccommodationType(@RequestBody String type) {
		System.out.println("\n CREATE ACCOMMODATION TYPE " + "\n");
			
		ArrayList<AdditionalService> createdAdditionalService = (ArrayList<AdditionalService>) additionalServicesService.createAdditionalService(type);

		if (createdAdditionalService != null) {
			return new ResponseEntity<Collection<AdditionalService>>(createdAdditionalService, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// Brise uslugu
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Collection<AdditionalService>> deleteAdditionalService(@PathVariable Long id) {
		System.out.println("\n DELETE ADDITIONAL SERVICE:  " + id + "\n");
		ArrayList<AdditionalService>  deletedAdditionalService = (ArrayList<AdditionalService>) additionalServicesService.deleteAdditionalService(id);

		if (deletedAdditionalService != null) {
			return new ResponseEntity<Collection<AdditionalService>>(deletedAdditionalService, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Izmena usluge
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Collection<AdditionalService>> deleteAdditionalService(@PathVariable Long id, @RequestBody String service) {
		System.out.println("\n EDIT ADDITIONAL SERVICE:  " + id + "\n");
		ArrayList<AdditionalService>  editedAdditionalService = (ArrayList<AdditionalService>) additionalServicesService.editAdditionalService(id, service);

		if (editedAdditionalService != null) {
			return new ResponseEntity<Collection<AdditionalService>>(editedAdditionalService, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
