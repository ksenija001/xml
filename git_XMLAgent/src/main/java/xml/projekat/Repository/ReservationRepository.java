package xml.projekat.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.projekat.Model.Reservation;
import xml.projekat.Model.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	public Reservation findById(Long id);
	
	public List<Reservation> findByAccommodationId(Long id);
	
	public List<Reservation> findByReservedById(Long id);
}
