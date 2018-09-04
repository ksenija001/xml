package xml.projekat.Service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.projekat.Dto.PriceDto;
import xml.projekat.Model.Price;
import xml.projekat.Repository.AccommodationRepository;
import xml.projekat.Repository.PriceRepository;

@Service
public class PriceService {

	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private AccommodationRepository accommodationRepository;

	public List<Price> findAll() {
		return priceRepository.findAll();
	}

	public Price findById(Long id) {
		return priceRepository.findById(id);
	}

	public List<Price> findPriceForAccommodation(Long id) {
		return accommodationRepository.findById(id).getPrices();
	}

	public Price deletePrice(Long id) {
		Price deletedPrice = priceRepository.findById(id);
		priceRepository.delete(deletedPrice);
		return deletedPrice;
	}

	public Price createPrice(PriceDto priceDto, Long id) {
		boolean exists = false;

		for (Price price : accommodationRepository.findById(id).getPrices()) {
			if (priceDto.getStartDate().after(price.getStartDate())
					|| priceDto.getEndDate().before(price.getEndDate())) {
				exists = true;
			}
		}

		if (!exists) {
			Price newPrice = priceDto.createPrice(priceDto);
			// Proveri da li mora ovo
			accommodationRepository.findById(id).getPrices().add(newPrice);
			priceRepository.save(newPrice);
		}
		return null;
	}
}
