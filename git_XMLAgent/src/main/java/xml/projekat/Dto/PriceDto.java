package xml.projekat.Dto;

import java.util.Date;

import xml.projekat.Model.Price;

public class PriceDto {

	private float price;

	private Date startDate;

	private Date endDate;
	
	//private boolean busy;
	
	public Price createPrice(PriceDto source) {
		Price price = new Price();
		price.setEndDate(source.getEndDate());
		price.setStartDate(source.getStartDate());
		price.setPrice(source.getPrice());
		//price.setBusy(false);
		
		return price;
	}


	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

/*
	public boolean isBusy() {
		return busy;
	}


	public void setBusy(boolean busy) {
		this.busy = busy;
	}
*/
}
