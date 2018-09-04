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

import xml.projekat.Dto.LocationDto;
import xml.projekat.Model.Location;
import xml.projekat.Service.LocationService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/locations")
public class LocationController {

	@Autowired
	private LocationService locationService;

	// Vraca lokaciju
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Location> getLocation(@PathVariable Long id) {
		System.out.println("\n GET LOCATION:  " + id + "\n");
		Location location = locationService.findById(id);

		if (location != null) {
			return new ResponseEntity<Location>(location, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca lokacije
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Location>> getLocations() {
		System.out.println("\n GET LOCATIONS " + "\n");
		ArrayList<Location> locations = (ArrayList<Location>) locationService.findAll();

		if (locations != null) {
			return new ResponseEntity<Collection<Location>>(locations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Izmena lokacije
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
		System.out.println("\n UPDATE LOCATION:  " + id + "\n");
		Location updatedLocation = locationService.updateLocation(id, location);

		if ( updatedLocation!= null) {
			return new ResponseEntity<Location>(updatedLocation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Kreiranje lokacije
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Location> createLocation(@RequestBody LocationDto locationDto) {
		System.out.println("\n CREATE LOCATION:  " +"\n");
		Location createdLocation = locationService.createLocation(locationDto.createLocation(locationDto));

		if (createdLocation!= null) {
			return new ResponseEntity<Location>(createdLocation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	// Brisanje lokacije - mora da se napravi i accommodation ja mislim - postman ne pokazuje dobro
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Location> deleteLocation(@PathVariable Long id) {
		System.out.println("\n DELETE LOCATION:  " + id + "\n");
		Location deletedLocation = locationService.deleteLocation(id);

		if (deletedLocation != null) {
			return new ResponseEntity<Location>(deletedLocation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
