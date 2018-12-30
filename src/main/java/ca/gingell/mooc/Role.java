package ca.gingell.mooc;

public class Role {
	private String username;
	private String authority;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
    @Override
    public String toString() {
        return "Role{" +
                "username=" + username +
                ", authority='" + authority +
                '}';
    }

    public static Role create(String username, String authority) {
        Role role = new Role();
        role.setUsername(username);
        role.setAuthority(authority);
        return role;
    }
}
