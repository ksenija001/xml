package xml.projekat.Dto;

public class AlertMessageDto {
	private String error;

	public AlertMessageDto() {
		super();
	}

	public AlertMessageDto(String error) {
		super();
		this.error = error;
	}

	public String getErr() {
		return error;
	}

	public void setErr(String error) {
		this.error = error;
	}
}
