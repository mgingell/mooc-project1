package ca.gingell.mooc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	private UserDao userdata;
	@Autowired
	private RoleDao roledata;
    @Autowired 
    JdbcTemplate jdbcTemplate;
    
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@PostMapping("/userview")
	public String handlePostRequest() {
		return "redirect:/userview";
	}

	@GetMapping("/userview")
	public String list(Model model) {

		List<User> users = userdata.loadAll();
		model.addAttribute("users", users);
// select * from users, authorities where users.username = authorities.username and authorities.authority = 'ROLE_ADMIN'        
		return "userview";
	}

	@RequestMapping(value = "/saveuser", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveUser(@RequestParam(required=true) String username, 
			@RequestParam(required=true) String password, 
			@RequestParam(required=false) String enabled,
			@RequestParam(defaultValue="") String details, 
			@RequestParam(required=false) String role) {

		
// Use of jdbcTemplate could lead to sql injection, so use dao instead		
		List<User> users = userdata.loadAll();
		Boolean exist = false;
        for (User testUser:users) {
        	if (testUser.getUsername().contains(username)) {
        		exist = true;
        		break;
        	}
        }
		// Check to see if user already there
		if (!exist) {
			User user = new User();
			user.setUsername(username);
			// Check password for security (# chars, upper/lower, etc.) and encrypt it 
			user.setPassword(encoder.encode(password));
			if (enabled != null)
			    user.setEnabled(true);
			else
				user.setEnabled(false);
			user.setDetails(details);

			userdata.save(user);

			// Set roles....
			Role auth = new Role();
			auth.setUsername(username);
			if (role != null)
				auth.setAuthority("ROLE_ADMIN");
			else
				auth.setAuthority("ROLE_USER");
			roledata.save(auth);

			return "success";
		} else {
			return "userexists";
		}
	}

	@PostMapping("/adduser")
	public String handlePost() {
		return "adduser";
	}

	@GetMapping("/adduser")
	public String handleUserRequest() {
		return "adduser";
	}

}