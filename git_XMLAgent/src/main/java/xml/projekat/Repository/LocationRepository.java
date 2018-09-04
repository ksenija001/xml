package xml.projekat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.projekat.Model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
	public Location findById(Long id);
}
