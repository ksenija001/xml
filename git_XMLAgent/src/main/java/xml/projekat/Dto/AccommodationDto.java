package xml.projekat.Dto;

import java.util.ArrayList;

import xml.projekat.Model.Accommodation;

public class AccommodationDto {

	private String title;

	private String description;

	private Integer numberOfPerson;

	private ArrayList<Long> additionalServicesId;

	private Long locationId;

	private Long ownerId;

	private Long typeId;

	private Integer ratingSum;

	public Accommodation createAccommodation(AccommodationDto source) {
		Accommodation accommodation = new Accommodation();
		accommodation.setTitle(source.getTitle());
		accommodation.setDescription(source.getDescription());
		accommodation.setNumberOfPerson(source.getNumberOfPerson());
		return accommodation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfPerson() {
		return numberOfPerson;
	}

	public void setNumberOfPerson(Integer numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Integer getRatingSum() {
		return ratingSum;
	}

	public void setRatingSum(Integer ratingSum) {
		this.ratingSum = ratingSum;
	}

	public ArrayList<Long> getAdditionalServicesId() {
		return additionalServicesId;
	}

	public void setAdditionalServicesId(ArrayList<Long> additionalServicesId) {
		this.additionalServicesId = additionalServicesId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

}
