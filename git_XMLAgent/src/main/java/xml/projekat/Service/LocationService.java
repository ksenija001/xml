package xml.projekat.Service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Model.Location;
import xml.projekat.Repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	public Location findById(Long id) {
		return locationRepository.findById(id);
	}

	public Location createLocation(Location location) {
		return locationRepository.save(location);
	}
	
	public Location updateLocation(Long id, Location location) {
	
		Location locationToUpdate = locationRepository.findById(id);
		locationToUpdate.setAdress(location.getAdress());
		locationToUpdate.setCity(location.getCity());
		locationToUpdate.setCountry(location.getCountry());
		locationRepository.save(locationToUpdate);
		return locationToUpdate;
	}
	
	public Location deleteLocation(Long id) {
		Location deletedLocation = locationRepository.findById(id);
		locationRepository.delete(deletedLocation);
		return deletedLocation;
	}
	
}
