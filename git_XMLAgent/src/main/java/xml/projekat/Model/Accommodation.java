package xml.projekat.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Accommodation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Location location;

	private boolean busy;

	private String title;

	@ManyToOne
	private AccommodationType type;

	private String description;

	@ManyToOne
	private User owner;

	private Integer numberOfPerson;

	@ManyToMany
	private List<AdditionalService> additionalServices = new ArrayList<AdditionalService>() ;

	@OneToMany
	private List<Price> prices;

	@ManyToOne
	private CategoryType category;

	private Float ratingSum;

	public Accommodation() {
		super();
		this.busy = false;
	}

	public Accommodation(Long id, Location location, boolean busy, String title, AccommodationType type,
			String desciption, User owner, Integer numberOfPerson, List<AdditionalService> additionalServices,
			List<Price> prices, CategoryType category, Float ratingSum) {
		super();
		this.id = id;
		this.location = location;
		this.busy = busy;
		this.title = title;
		this.type = type;
		this.description = desciption;
		this.owner = owner;
		this.numberOfPerson = numberOfPerson;
		this.additionalServices = additionalServices;
		this.prices = prices;
		this.category = category;
		this.ratingSum = ratingSum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AccommodationType getType() {
		return type;
	}

	public void setType(AccommodationType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getNumberOfPerson() {
		return numberOfPerson;
	}

	public void setNumberOfPerson(Integer numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}

	public CategoryType getCategory() {
		return category;
	}

	public void setCategory(CategoryType category) {
		this.category = category;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public Float getRatingSum() {
		return ratingSum;
	}

	public void setRatingSum(Float ratingSum) {
		this.ratingSum = ratingSum;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

}
