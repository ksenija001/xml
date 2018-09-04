package xml.projekat.Controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.projekat.Dto.PriceDto;
import xml.projekat.Model.Price;
import xml.projekat.Service.PriceService;

@RestController
@CrossOrigin(origins = {"http://localhost:9030"})
@RequestMapping(value = "/prices")
public class PriceController {

	@Autowired
	private PriceService priceService;

	// Vraca cenu
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Price> getPrice(@PathVariable Long id) {
		System.out.println("\n GET PRICE:  " + id + "\n");
		Price price = priceService.findById(id);

		if (price != null) {
			return new ResponseEntity<Price>(price, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca cene
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Price>> getPrices() {
		System.out.println("\n GET PRICEs:  " + "\n");
		ArrayList<Price> prices = (ArrayList<Price>) priceService.findAll();

		if (prices != null) {
			return new ResponseEntity<Collection<Price>>(prices, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Vraca cene odredjenog smestaja
	@RequestMapping(value = "/priceForAccommodation/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Price>> getPricesForAccommodation(@PathVariable Long id) {
		System.out.println("\n GET PRICES FOR ACCOMMODATION:  " + "\n");
		Collection<Price> prices =  priceService.findPriceForAccommodation(id);

		if (prices != null) {
			return new ResponseEntity<Collection<Price>>(prices, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Kreiranje cene za odredjeni smesataj
	@RequestMapping(value = "/createPrice/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Price> sendMessage(@RequestBody PriceDto priceDto, @PathVariable Long id) {
		System.out.println("\n CREATE PRICE FOR ACCOMMODATION" + id + "\n");

		Price price = priceService.createPrice(priceDto, id);

		if (price != null) {
			return new ResponseEntity<Price>(price, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// Brisanje cene
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Price> deletePrice(@PathVariable Long id) {
		System.out.println("\n DELETE PRICE FOR ACCOMMODATION" + id + "\n");
		Price deletedPrice = priceService.deletePrice(id);

		if (deletedPrice != null) {
			return new ResponseEntity<Price>(deletedPrice, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}
