package ca.gingell.mooc;

public class User {
	private String username;
	private String password;
	private Boolean enabled;
	private String details;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public static User create(String username, String password, Boolean enabled, String details) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setDetails(details);
        return user;
    }
}

	
