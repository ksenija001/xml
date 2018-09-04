package xml.projekat.Dto;

import java.util.Date;

import xml.projekat.Model.Reservation;

public class ReservationDto {
	private Long reservedById;

	private Long accommodationId;

	private Date startDate;

	private Date endDate;

	public Reservation createReservation(ReservationDto source) {
		Reservation reservation = new Reservation();
		reservation.setEndDate(source.getEndDate());
		reservation.setStartDate(source.getStartDate());
		return reservation;
	}
	public Long getReservedById() {
		return reservedById;
	}

	public void setReservedById(Long reservedById) {
		this.reservedById = reservedById;
	}

	public Long getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
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

}
