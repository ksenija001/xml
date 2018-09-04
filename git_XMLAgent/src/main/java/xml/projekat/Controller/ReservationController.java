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

import com.fasterxml.jackson.databind.ObjectMapper;

import xml.projekat.Dto.AlertMessageDto;
import xml.projekat.Dto.ReservationDto;
import xml.projekat.Model.Accommodation;
import xml.projekat.Model.Reservation;
import xml.projekat.Repository.ReservationRepository;
import xml.projekat.Service.ReservationService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationRepository reservationRepository;

	// Vraca rezervaciju
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
		System.out.println("\n GET RESSERVATION:  " + id + "\n");
		Reservation reservation = reservationService.findById(id);

		if (reservation != null) {
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca rezervacije
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Reservation>> getReservations() {
		System.out.println("\n GET RESERVATIONS " + "\n");
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationService.findAll();

		if (reservations != null) {
			return new ResponseEntity<Collection<Reservation>>(reservations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Kreiranje rezervacije
	@RequestMapping(value="/createReservation", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto) {
		System.out.println("\n CREATE RESERVATION " + "\n");
			
		Reservation createdReservation = reservationService.createReservation(reservationDto);

		if (createdReservation != null) {
			return new ResponseEntity<Reservation>(createdReservation, HttpStatus.OK);
		}
		return new ResponseEntity<AlertMessageDto>(new AlertMessageDto("The place has already been reserved!"),HttpStatus.NOT_FOUND);
	}
	
	// Vracanje rezervacija korisnika
	@RequestMapping(value = "/findReservationForUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Reservation>> findReservationForUser(@PathVariable Long id) {
		System.out.println("\n GET RESERVATION FOR USER:  " + id + "\n");
		ArrayList<Reservation> reservations = (ArrayList<Reservation>)reservationService.findReservationForUser(id);

		if (reservations != null) {
			return new ResponseEntity<Collection<Reservation>>(reservations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/getReservationsOfMyUnits/{username}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String getReservationsOfMyUnits(@PathVariable String username) throws Exception {
		
		ArrayList<Reservation> ress = new ArrayList<>();
		
		for(Reservation r : reservationService.findAll()){
			
			if(r.getAccommodation()!=null){
				System.out.println("username:" + r.getAccommodation().getOwner().getUsername());
				if(r.getAccommodation().getOwner().getUsername().equals(username)){
					if(!(r.isConfirmed()))
					ress.add(r);
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		   	   
	    String u = mapper.writeValueAsString(ress);
	    System.out.println(username+"'s units reservations:");
	    System.out.println(u);
	    return u;	
		
	}
	
	// Ocena rezervacije
	@RequestMapping(value = "/setRatingReservation/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Reservation> serRatingReservation(@PathVariable Long id, @RequestBody Integer rating) {
		System.out.println("\n SET RATING RESSERVATION:  " + id + "\n");
		Reservation reservation = reservationService.ratingReservation(id, rating);

		if (reservation != null) {
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Otkazivanje rezervacije
	@RequestMapping(value = "/cancelReservationById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Collection<Reservation>> cancelReservation(@PathVariable Long id) {
		System.out.println("\n CANCEL RESSERVATION:  " + id + "\n");
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationService.cancelReservation(id);

		if (reservations != null) {
			return new ResponseEntity<Collection<Reservation>>(reservations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
	@RequestMapping(path = "/confirmReservation/{username}/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String confirmReservation(@PathVariable String username, @PathVariable Long id) throws Exception {		
		for(Reservation r : reservationService.findAll()){
			if(r.getId().equals(id)){
				if(r.isConfirmed()==false){
					r.setConfirmed(true);
					reservationRepository.save(r);
				}
				
			}
		}
						
		ObjectMapper mapper = new ObjectMapper();
	    String u = mapper.writeValueAsString(reservationService.findByAccommodationId(id));
	    System.out.println(u);

	    return u;
	}

	@RequestMapping(value = "/findConfirmedReservationAccommodationsForUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Accommodation>> findConfirmedReservationAccommodationsForUser(@PathVariable Long id) {
		System.out.println("\n GET ACCOMMODATION FOR CONFIRMED RESSERVATION FOR USER:  " + id + "\n");
		ArrayList<Accommodation> reservationsAccommodations = (ArrayList<Accommodation>)reservationService.findConfirmedReservationAccommodationsForUser(id);

		if (reservationsAccommodations != null) {
			return new ResponseEntity<Collection<Accommodation>>(reservationsAccommodations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

