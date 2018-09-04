package xml.projekat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.projekat.Model.AccommodationType;

@Repository
public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {
	
	public AccommodationType findById(Long id);
}
