package com.netjstech.controller; 
 
import java.util.List; 
 
import javax.validation.Valid; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntities; 
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 
//protocol://machine-name:port-number/context-path/path-class/path-method 
 
import com.netjstech.entities.Admin; 
import com.netjstech.entities.CharityEvents; 
import com.netjstech.entities.Donation; 
import com.netjstech.entities.Donor; 
import com.netjstech.entities.Ngo; 
import com.netjstech.exception.InvalidCredentialsException; 
import com.netjstech.exception.NoDonationFoundException; 
import com.netjstech.exception.NoSuchEventFoundException; 
import com.netjstech.exception.NoSuchNgoFoundException; 
import com.netjstech.exception.NoSuchUserFoundException; 
import com.netjstech.exception.UniqueConstraintViolationException; 
import com.netjstech.service.AdminService; 
 
@RestController 
@RequestMapping(path = "admins") 
 
public class AdminController { 
@Autowired 
AdminService service; 
 
// http://localhost:9090/charity-api/admins/ - Post 
@PostMapping(path = "register") 
public ResponseEntities<String> saveAdmin(@Valid @RequestBody Admin admin) throws UniqueConstraintViolationException { 
ResponseEntities<String> response = null; 
boolean result = service.addAdmin(admin); 
if (result) 
response = new ResponseEntities<String>("Admin with username" + admin.getUser().getUsername() + "is added", 
HttpStatus.CREATED); 
return response; 
} 
 
// http://localhost:9090/charity-api/admins/searchByUsername/ -Get 
@GetMapping(path = "searchByUsername/{username}") 
public ResponseEntities<Admin> getAdminByUsername(@PathVariable("username") String username) 
throws NoSuchUserFoundException { 
ResponseEntities<Admin> response = null; 
Admin admin = service.findAdminByUserName(username); 
if (admin.getUser().getUsername().equals(username)) { 
response = new ResponseEntities<Admin>(admin, HttpStatus.OK); 
} 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/searchByEmail/ 
@GetMapping(path = "searchByEmail/{email}") 
public ResponseEntities<Admin> getAdminByEmail(@PathVariable("email") String email) 
throws NoSuchUserFoundException, InvalidCredentialsException { 
ResponseEntities<Admin> response = null; 
Admin admin = service.findAdminByEmail(email); 
response = new ResponseEntities<Admin>(admin, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/searchByFirstName/ 
@GetMapping(path = "searchByFirstName/{firstName}") 
public ResponseEntities<List<Admin>> getAdminByFirstName(@PathVariable("firstName") String firstName) 
throws NoSuchUserFoundException, InvalidCredentialsException { 
ResponseEntities<List<Admin>> response = null; 
List<Admin> admin = service.findAdminByFirstName(firstName); 
response = new ResponseEntities<List<Admin>>(admin, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/searchByLastName/ 
@GetMapping(path = "searchByLastName/{LastName}") 
public ResponseEntities<List<Admin>> getAdminByLastName(@PathVariable("LastName") String lastName) 
throws NoSuchUserFoundException, InvalidCredentialsException { 
ResponseEntities<List<Admin>> response = null; 
List<Admin> admin = service.findAdminByLastName(lastName); 
response = new ResponseEntities<List<Admin>>(admin, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/updateEmail/ 
@PutMapping(path = "updateEmail/{adminId}/{email}") 
public ResponseEntities<String> updateAdminEmail(@PathVariable("adminId") int adminId, 
@PathVariable("email") String email) throws NoSuchUserFoundException { 
ResponseEntities<String> response = null; 
service.updateAdminEmail(adminId, email); 
response = new ResponseEntities<String>("Admin with adminId " + adminId + " is updated to " + email + " ", 
HttpStatus.CREATED); 
return response; 
} 
 
// http://localhost:9090/charity-api/admins/updateByPhoneNumber/ 
@PutMapping(path = "updateByPhoneNumber/{adminId}/{phoneNumber}") 
public ResponseEntities<String> updateAdminPhoneNumber(@Valid @PathVariable("adminId") int adminId, 
@PathVariable("phoneNumber") String phoneNumber) throws NoSuchUserFoundException { 
ResponseEntities<String> response = null; 
service.updateAdminPhoneNumber(adminId, phoneNumber); 
response = new ResponseEntities<String>("Admin with adminId " + adminId + " is updated to " + phoneNumber + " ", 
HttpStatus.CREATED); 
return response; 
} 
 
// http://localhost:9090/charity-api/admins/deleteByEmail/ 
@DeleteMapping(path = "deleteByEmail/{email}") 
public ResponseEntities<String> deleteAdminByEmail(@Valid @PathVariable("email") String email) 
throws NoSuchUserFoundException { 
ResponseEntities<String> response = null; 
service.removeAdminByEmail(email); 
response = new ResponseEntities<String>("Selected Admin is removed ", HttpStatus.CREATED); 
return response; 
} 
 
// http://localhost:9090/charity-api/admins/deleteByadminId/ 
@DeleteMapping(path = "deleteByadminId/{adminId}") 
public ResponseEntities<String> deleteAdminByadminId(@Valid @PathVariable("adminId") int adminId) 
throws NoSuchUserFoundException { 
ResponseEntities<String> response = null; 
service.removeAdminByadminId(adminId); 
response = new ResponseEntities<String>("Selected Admin is removed ", HttpStatus.CREATED); 
return response; 
} 
 
// http://localhost:9090/charity-api/admins/searchNgoByName/ --------------done 
@GetMapping(path = "searchNgoByName//{ngoName}") 
public ResponseEntities<Ngo> getNgoByName(@PathVariable("ngoName") String ngoName) 
throws NoSuchUserFoundException, InvalidCredentialsException { 
ResponseEntities<Ngo> response = null; 
Ngo ngo = service.findNgoByName(ngoName); 
response = new ResponseEntities<Ngo>(ngo, HttpStatus.OK); 
 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/viewDonor/ 
@GetMapping(path = "viewDonor/") 
public ResponseEntities<List<Donor>> getDonors() throws NoSuchUserFoundException, InvalidCredentialsException { 
ResponseEntities<List<Donor>> response = null; 
List<Donor> donor = service.findDonor(); 
response = new ResponseEntities<List<Donor>>(donor, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/viewAdmin/ 
@GetMapping(path = "viewAdmin/") 
public ResponseEntities<List<Admin>> getAdmins() throws NoSuchUserFoundException, InvalidCredentialsException { 
ResponseEntities<List<Admin>> response = null; 
List<Admin> admin = service.findAdminDetails(); 
response = new ResponseEntities<List<Admin>>(admin, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/viewDonation/ 
@GetMapping(path = "viewDonation/") 
public ResponseEntities<List<Donation>> getDonations() throws NoDonationFoundException { 
ResponseEntities<List<Donation>> response = null; 
List<Donation> donation = service.findDonation(); 
response = new ResponseEntities<List<Donation>>(donation, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/viewNgo/ 
@GetMapping(path = "viewNgo/") 
public ResponseEntities<List<Ngo>> getNgos() throws NoSuchNgoFoundException { 
ResponseEntities<List<Ngo>> response = null; 
List<Ngo> ngo = service.findNgo(); 
response = new ResponseEntities<List<Ngo>>(ngo, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/addNgo/ - Post 
@PostMapping(path = "addNgo/") 
public ResponseEntities<String> saveNgo(@Valid @RequestBody Ngo ngo) 
throws UniqueConstraintViolationException, NoSuchUserFoundException { 
ResponseEntities<String> response = null; 
boolean result = service.addNgo(ngo); 
if (result) 
response = new ResponseEntities<String>("Admin with username" + ngo.getUser().getUsername() + "is added", 
HttpStatus.CREATED); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/deleteByNgoName/ 
@DeleteMapping(path = "deleteByNgoName/{ngoName}") 
public ResponseEntities<String> deleteAdminByNgoName(@PathVariable("ngoName") String ngoName) 
throws NoSuchNgoFoundException { 
ResponseEntities<String> response = null; 
service.removeNgoByName(ngoName); 
response = new ResponseEntities<String>("Selected Ngo is removed ", HttpStatus.CREATED); 
return response; 
} 
 
// http://localhost:9090/charity-api/admins/addEvent/ - Post 
@PostMapping(path = "addEvent/") 
public ResponseEntities<String> saveEvent(@Valid @RequestBody CharityEvents event) throws NoSuchEventFoundException { 
ResponseEntities<String> response = null; 
boolean result = service.addEvent(event); 
if (result) 
response = new ResponseEntities<String>(event.getEventName() + " is added Successfully", HttpStatus.CREATED); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/viewEvents/ 
@GetMapping(path = "viewEvents/") 
public ResponseEntities<List<CharityEvents>> getEvents() throws NoSuchEventFoundException { 
ResponseEntities<List<CharityEvents>> response = null; 
List<CharityEvents> events = service.findEvents(); 
response = new ResponseEntities<List<CharityEvents>>(events, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/admins/totalAmount- Get 
@GetMapping(path = "/totalAmount") 
public Double totalDonations() throws NoDonationFoundException { 
return service.totalDonations(); 
} 
 
} 
