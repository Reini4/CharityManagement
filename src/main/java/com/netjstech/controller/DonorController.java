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
 
import com.netjstech.entities.Donation; 
import com.netjstech.entities.Donor; 
import com.netjstech.exception.NoDonationFoundException; 
import com.netjstech.exception.UniqueConstraintViolationException; 
import com.netjstech.service.AdminService; 
import com.netjstech.service.DonationService; 
import com.netjstech.service.DonorService; 
 
@RestController 
@RequestMapping(path = "donors") 
 
public class DonorController { 
@Autowired 
DonorService donorService; 
@Autowired 
DonationService donationService; 
@Autowired 
private AdminService adminService; 
 
// http://localhost:9090/charity-api/donors/ - Post 
@PostMapping(path = "/") 
public ResponseEntities<String> saveDonor(@Valid @RequestBody Donor donor) throws UniqueConstraintViolationException { 
ResponseEntities<String> response = null; 
boolean result = donorService.addDonor(donor); 
donor.getUser().setUserPassword(new BCryptPasswordEncoder().encode(donor.getUser().getUserPassword())); 
 
if (result) 
response = new ResponseEntities<String>("Donor with username " + donor.getUser().getUsername() + "is added", 
HttpStatus.CREATED); 
try { 
adminService.sendGreetingMail(donor); 
} catch (Exception e) { 
System.out.println(e); 
} 
return response; 
 
} 
 
// http://localhost:9090/charity-api/donors/addDonation 
@PostMapping("/addDonation") 
public ResponseEntities<String> saveDonation(@Valid @RequestBody Donation donation) { 
ResponseEntities<String> response = null; 
boolean result = donorService.addDonation(donation); 
if (result) 
response = new ResponseEntities<String>("Donation with amount" + donation.getAmount() + "is added", 
HttpStatus.CREATED); 
return response; 
 
} 
 
// http://localhost:9090/charity-api/donors/addDonor 
@PostMapping("/addDonor") 
public boolean addDonor(@Valid @RequestBody Donor donor) { 
return donationService.addDonor(donor); 
} 
 
// http://localhost:9090/charity-api/donors/totalAmount- Get 
@GetMapping(path = "/totalAmount") 
public Double totalDonations() throws NoDonationFoundException { 
return donationService.totalDonations(); 
} 
 
// http://localhost:9090/charity-api/donors/viewDonation/ 
@GetMapping(path = "viewDonation/") 
public ResponseEntities<List<Donation>> getDonations() throws NoDonationFoundException { 
ResponseEntities<List<Donation>> response = null; 
List<Donation> donation = donorService.findDonation(); 
response = new ResponseEntities<List<Donation>>(donation, HttpStatus.OK); 
return response; 
 
} 
 
} 
