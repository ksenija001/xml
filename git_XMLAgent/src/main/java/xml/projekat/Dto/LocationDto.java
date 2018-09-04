package xml.projekat.Dto;

import xml.projekat.Model.Location;

public class LocationDto {

	private String adress;

	private String city;

	private String country;

	
	public Location createLocation(LocationDto source) {
		Location location = new Location();
		location.setAdress(source.getAdress());
		location.setCity(source.getCity());
		location.setCountry(source.getCountry());
		return location;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	
}
