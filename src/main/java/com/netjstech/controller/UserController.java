package com.netjstech.controller; 
 
import javax.validation.Valid; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 
 
import com.netjstech.entities.User; 
import com.netjstech.repository.Status; 
import com.netjstech.repository.UserRepository; 
 
@RestController 
@RequestMapping(path = "users") 
public class UserController { 
@Autowired 
UserRepository userRepository; 
 
// http://localhost:9090/charity-api/users/login/ 
@GetMapping(path = "login/{username}/{userPassword}") 
public Status loginUser(@Valid @PathVariable("username") String username, 
@PathVariable("userPassword") String userPassword) { 
User user = userRepository.readByUsernameAndpassword(username, userPassword); 
if (user == null) { 
return Status.FAILURE_INVALID_CREDENTIALS; 
} 
 
return Status.LOGGED_IN_SUCCESSFULLY_WELCOME; 
} 
 
} 
