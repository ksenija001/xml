package xml.projekat.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Dto.AccommodationDto;

import xml.projekat.Model.Accommodation;
import xml.projekat.Model.Reservation;
import xml.projekat.Repository.AccommodationRepository;
import xml.projekat.Repository.AccommodationTypeRepository;
import xml.projekat.Repository.AdditionalServiceRepository;
import xml.projekat.Repository.LocationRepository;
import xml.projekat.Repository.ReservationRepository;
import xml.projekat.Repository.UserRepository;

@Service
public class AccommodationService {
	@Autowired
	private AccommodationRepository accommodationRepository;

	@Autowired
	private AccommodationTypeRepository accommodationTypeRepository;

	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;

	@Autowired
	private LocationRepository locationServiceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	public List<Accommodation> findAll() {
		return accommodationRepository.findAll();
	}

	public Accommodation findById(Long id) {
		return accommodationRepository.findById(id);
	}

	public Accommodation createAccommodation(AccommodationDto accommodationDto) {
		Accommodation accommodation = accommodationDto.createAccommodation(accommodationDto);
		accommodation.setType(accommodationTypeRepository.findById(accommodationDto.getTypeId()));
		accommodation.setOwner(userRepository.findById(accommodationDto.getOwnerId()));
		accommodation.setLocation(locationServiceRepository.findById(accommodationDto.getLocationId()));

		/*for (Long id : accommodationDto.getAdditionalServicesId()) {
			if (id.equals(additionalServiceRepository.findById(id).getId())) {
				accommodation.getAdditionalServices().add(additionalServiceRepository.findById(id));
			}
		}*/
	
		return accommodationRepository.save(accommodation);
		
	}

	public Accommodation deleteAccommodation(Long id) {
		Accommodation deletedAccommodation = accommodationRepository.findById(id);
		accommodationRepository.delete(deletedAccommodation);
		return deletedAccommodation;
	}

	public Accommodation busyAccommodation(Long id) {
		Accommodation busyAccommodation = accommodationRepository.findById(id);
		busyAccommodation.setBusy(true);
		accommodationRepository.save(busyAccommodation);
		return busyAccommodation;
	}

	
}
