package xml.projekat.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Jedan korisnik vise rezervacija moze da napravi
	@ManyToOne
	private User reservedBy;

	@OneToOne
	private Accommodation accommodation;

	private Date startDate;

	private Date endDate;

	private float bill;

	private boolean confirmed;

	//private Integer rating;

	public Reservation() {
		super();
		this.confirmed = false;
	}

	public Reservation(Long id, User reservedBy, Accommodation accommodation, Date startDate, Date endDate, float bill,
			boolean confirmed/*, Integer rating*/) {
		super();
		this.id = id;
		this.reservedBy = reservedBy;
		this.accommodation = accommodation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bill = bill;
		this.confirmed = confirmed;
		//this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(User reservedBy) {
		this.reservedBy = reservedBy;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
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

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	/*public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}*/

}
