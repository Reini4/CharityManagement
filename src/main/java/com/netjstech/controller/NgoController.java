package com.netjstech.controller; 
 
import java.util.List; 
 
import javax.validation.Valid; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntities; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 
 
import com.netjstech.entities.CharityEvents; 
import com.netjstech.entities.Donation; 
import com.netjstech.entities.Ngo; 
import com.netjstech.exception.NoDonationFoundException; 
import com.netjstech.exception.NoSuchEventFoundException; 
import com.netjstech.exception.NoSuchNgoFoundException; 
import com.netjstech.service.AdminService; 
import com.netjstech.service.NgoService; 
 
@RestController 
@RequestMapping(path = "ngos") 
public class NgoController { 
@Autowired 
NgoService ngoService; 
 
@Autowired 
private AdminService adminService; 
 
// http://localhost:9090/charity-api/ngos/ - Post 
@PostMapping(path = "/") 
public ResponseEntities<String> saveNgo(@Valid @RequestBody Ngo ngo) { 
ResponseEntities<String> response = null; 
boolean result = ngoService.addNgo(ngo); 
ngo.getUser().setUserPassword(new BCryptPasswordEncoder().encode(ngo.getUser().getUserPassword())); 
; 
if (result) 
response = new ResponseEntities<String>(ngo.getNgoName() + " is added Successfully", HttpStatus.CREATED); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/ngos/viewNgo/ 
@GetMapping(path = "viewNgo/") 
public ResponseEntities<List<Ngo>> getNgos() throws NoSuchNgoFoundException { 
ResponseEntities<List<Ngo>> response = null; 
List<Ngo> ngo = ngoService.findNgo(); 
response = new ResponseEntities<List<Ngo>>(ngo, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/ngos/viewDonation/ 
@GetMapping(path = "viewDonation/") 
public ResponseEntities<List<Donation>> getDonations() throws NoDonationFoundException { 
ResponseEntities<List<Donation>> response = null; 
List<Donation> donation = ngoService.findDonation(); 
response = new ResponseEntities<List<Donation>>(donation, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/ngos/viewEvent/ 
@GetMapping(path = "viewEvent/") 
public ResponseEntities<List<CharityEvents>> getEvents() throws NoSuchEventFoundException { 
ResponseEntities<List<CharityEvents>> response = null; 
List<CharityEvents> event = ngoService.findEvent(); 
response = new ResponseEntities<List<CharityEvents>>(event, HttpStatus.OK); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/ngos/totalAmount- Get 
@GetMapping(path = "/totalAmount") 
public Double totalDonations() throws NoDonationFoundException { 
return ngoService.totalDonations(); 
} 
 
// http://localhost:9090/charity-api/ngos/addEvent/ - Post 
@PostMapping(path = "addEvent/") 
public ResponseEntities<String> saveEvent(@Valid @RequestBody CharityEvents event) throws NoSuchEventFoundException { 
ResponseEntities<String> response = null; 
boolean result = adminService.addEvent(event); 
if (result) 
response = new ResponseEntities<String>(event.getEventName() + " is added Successfully", HttpStatus.CREATED); 
try { 
ngoService.sendEventMail(event); 
} catch (Exception e) { 
System.out.println(e); 
} 
 
return response; 
} 
 
} 
