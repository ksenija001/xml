package xml.projekat.Service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Model.Accommodation;
import xml.projekat.Model.AdditionalService;
import xml.projekat.Repository.AccommodationRepository;
import xml.projekat.Repository.AdditionalServiceRepository;

@Service
public class AdditionalServiceServices {

	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;

	@Autowired
	private AccommodationRepository accommodationRepository;

	public List<AdditionalService> findAll() {
		return additionalServiceRepository.findAll();
	}

	public AdditionalService findById(Long id) {
		return additionalServiceRepository.findById(id);
	}

	public List<AdditionalService> deleteAdditionalService(Long id) {

		for (Accommodation a : accommodationRepository.findAll()) {
			if (a.getAdditionalServices() != null) {
				for (int i = 0; i < a.getAdditionalServices().size(); i++) {

					if (a.getAdditionalServices().get(i).getId().equals(id)) {
						a.getAdditionalServices().remove(i);
						accommodationRepository.save(a);
					}

				}
			}
		}

		AdditionalService deletedAdditionalServices = additionalServiceRepository.findById(id);
		additionalServiceRepository.delete(deletedAdditionalServices);

		return findAll();
	}

	public List<AdditionalService> createAdditionalService(String type) {
		AdditionalService createdAdditionalServices = new AdditionalService();
		createdAdditionalServices.setService(type);
		additionalServiceRepository.save(createdAdditionalServices);
		return findAll();
	}
	

	public List<AdditionalService> editAdditionalService(Long id, String service) {
		AdditionalService editedAddition = additionalServiceRepository.findById(id);
		editedAddition.setService(service);
		additionalServiceRepository.save(editedAddition);
		return findAll();
	}
}
