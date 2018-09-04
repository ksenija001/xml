package xml.projekat.Dto;

import xml.projekat.Model.User;

public class LoginDto {

	private String email;

	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User transformLoginUser(LoginDto source) {
		User user = new User();
		user.setPassword(source.getPassword());
		user.setEmail(source.getEmail());
		return user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
