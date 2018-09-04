package xml.projekat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xml.projekat.Model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	public Price findById(Long id);
}
