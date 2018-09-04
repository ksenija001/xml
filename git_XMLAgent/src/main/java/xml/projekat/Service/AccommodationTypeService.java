package xml.projekat.Service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Model.Accommodation;
import xml.projekat.Model.AccommodationType;
import xml.projekat.Repository.AccommodationRepository;
import xml.projekat.Repository.AccommodationTypeRepository;

@Service
public class AccommodationTypeService {

	@Autowired
	private AccommodationTypeRepository accommodationTypeRepository;

	@Autowired
	private AccommodationRepository accommodationRepository;
	
	public List<AccommodationType> findAll() {
		return accommodationTypeRepository.findAll();
	}

	public AccommodationType findById(Long id) {
		return accommodationTypeRepository.findById(id);
	}

	public List<AccommodationType> createAccommodationType(String type) {
		AccommodationType accommodationType = new AccommodationType();
		accommodationType.setType(type);
		accommodationTypeRepository.save(accommodationType);
		return findAll();
	}

	public List<AccommodationType> deleteAccommodationType(Long id) {
		
		for(Accommodation a : accommodationRepository.findAll()) {
			if(a.getType().getId().equals(id)) {
				a.setType(null);
				accommodationRepository.save(a);
			}
		}
		
		AccommodationType deletedAccommodationType = accommodationTypeRepository.findById(id);
		accommodationTypeRepository.delete(deletedAccommodationType);

		return findAll();
	}
	
	public List<AccommodationType> editAccommodationType(Long id, String type) {
		AccommodationType editedType = accommodationTypeRepository.findById(id);
		editedType.setType(type);
		accommodationTypeRepository.save(editedType);
		return findAll();
	}

}
