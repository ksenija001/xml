package xml.projekat.Service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Dto.ReservationDto;
import xml.projekat.Model.Accommodation;
import xml.projekat.Model.Reservation;
import xml.projekat.Model.User;
import xml.projekat.Repository.AccommodationRepository;
import xml.projekat.Repository.ReservationRepository;
import xml.projekat.Repository.UserRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public AccommodationRepository accommodationRepository;

	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public Reservation findById(Long id) {
		return reservationRepository.findById(id);
	}
	
	public List<Reservation> findByAccommodationId(Long id) {
		return reservationRepository.findByAccommodationId(id);
	}

	public Reservation createReservation(ReservationDto reservationDto) {
		System.out.println("POCINJE DA PRAVI REZERVACIJU ");
		boolean busy = false;
		for (Reservation reservation : reservationRepository.findAll()) {
			if (reservation.getAccommodation().getId().equals(reservationDto.getAccommodationId())) {
				// Ako je termin ovaj stavim na false
				System.out.println("NASLI SMESTAJ");
				if (reservation.getEndDate().compareTo(reservationDto.getEndDate()) == 0) {
					if (reservation.getStartDate().compareTo(reservationDto.getStartDate()) == 0) {
						busy = true;
						System.out.println("Postoji vec rezervacija");
					}
				}

			}

		}

		if (busy == false) {
			Reservation createdReservation = reservationDto.createReservation(reservationDto);
			createdReservation.setReservedBy(userRepository.findById(reservationDto.getReservedById()));
			createdReservation.setAccommodation(accommodationRepository.findById(reservationDto.getAccommodationId()));
			reservationRepository.save(createdReservation);
			// Treba da se izracuna cena da dane prebrojimo - da bi znali koliko je cena
			return createdReservation;
		}

		return null;

	}
	
	public List<Reservation> findReservationForUser(Long id) {
		return reservationRepository.findByReservedById(id);
	}
	
	public List<Reservation> findReservationForAccommodation(Long id) {
		return reservationRepository.findByReservedById(id);
	}
	
	public List<Accommodation> findConfirmedReservationAccommodationsForUser(Long id) {
		
		ArrayList<Long>	accommodationIds = new ArrayList<Long>();
		
		ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
		
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepository.findByReservedById(id);
		

		for(Reservation r : reservations) {
			boolean exists = false;
			if(accommodationIds!=null) {
				for(int i=0; i<accommodationIds.size(); i++) {
					if(accommodationIds.get(i).equals(r.getAccommodation().getId())) {
						exists = true;
					}
				}
				if(exists == false) {
					accommodationIds.add(r.getAccommodation().getId());
				}
			}
		}
		
		for(int i=0; i<accommodationIds.size(); i++) {
			for(Accommodation a : accommodationRepository.findAll()) {
				if(a.getId().equals(accommodationIds.get(i))) {
					accommodations.add(a);
				}
			}
		}
		return accommodations;
	}

	public List<Reservation> cancelReservation(Long id) {
		Reservation canceledReservation = reservationRepository.findById(id);
		reservationRepository.delete(canceledReservation);
		return reservationRepository.findByReservedById(canceledReservation.getReservedBy().getId());
	}
	
	public Reservation confirmReservation(User username, Long Id) {
		Reservation confirmedReservation = reservationRepository.findById(Id);
		confirmedReservation.setConfirmed(true);
		reservationRepository.save(confirmedReservation);
		return 
				(Reservation) reservationRepository.findByReservedById(confirmedReservation.getReservedBy().getId());
	}

	public Reservation ratingReservation(Long id, Integer rating) {
		Reservation ratedReservation = reservationRepository.findById(id);
		//ratedReservation.setRating(rating);
		return reservationRepository.save(ratedReservation);
	}

}
