package xml.projekat.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import xml.projekat.Model.Accommodation;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

	public Accommodation findById(Long id);
	
	public List<Accommodation> findByLocationCountryContainingIgnoreCaseAndLocationCityContainingIgnoreCaseAndLocationAdressContainingIgnoreCase(String country,String city,String adress);
	
	public List<Accommodation> findByLocationCountryContainingIgnoreCaseAndLocationCityContainingIgnoreCase(String country, String city);
	
	public List<Accommodation> findByLocationCityContainingIgnoreCaseAndLocationAdressContainingIgnoreCase(String city, String adress);
	
	public List<Accommodation> findByLocationCountryContainingIgnoreCaseAndLocationAdressContainingIgnoreCase(String country, String adress);
	
	public List<Accommodation> findByLocationAdressContainingIgnoreCase(String adress);
	
	public List<Accommodation> findByLocationCountryContainingIgnoreCase(String country);
	
	public List<Accommodation> findByLocationCityContainingIgnoreCase(String city);
}
