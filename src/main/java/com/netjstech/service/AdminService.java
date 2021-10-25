package com.netjstech.service; 
 
import java.util.List; 
 
import org.springframework.mail.javamail.JavaMailSender; 
 
import com.netjstech.entities.Admin; 
import com.netjstech.entities.CharityEvents; 
import com.netjstech.entities.Donation; 
import com.netjstech.entities.Donor; 
import com.netjstech.entities.Ngo; 
import com.netjstech.exception.NoDonationFoundException; 
import com.netjstech.exception.NoSuchEventFoundException; 
import com.netjstech.exception.NoSuchNgoFoundException; 
import com.netjstech.exception.NoSuchUserFoundException; 
import com.netjstech.exception.UniqueConstraintViolationException; 
 
public interface AdminService { 
 
public boolean addAdmin(Admin admin) throws UniqueConstraintViolationException; 
 
public Admin findAdminByUserName(String username) throws NoSuchUserFoundException; 
 
public Admin findAdminByEmail(String email) throws NoSuchUserFoundException; 
 
public List<Admin> findAdminByFirstName(String firstName) throws NoSuchUserFoundException; 
 
public List<Admin> findAdminByLastName(String lastName) throws NoSuchUserFoundException; 
 
public void removeAdminByEmail(String email) throws NoSuchUserFoundException; 
 
public boolean removeAdminByadminId(int adminId) throws NoSuchUserFoundException; 
 
public void updateAdminEmail(int adminId, String email) throws NoSuchUserFoundException; 
 
public void updateAdminPhoneNumber(int adminId, String phoneNumber) throws NoSuchUserFoundException; 
 
public List<Admin> findAdminDetails() throws NoSuchUserFoundException; 
 
// For Ngo 
 
public boolean addNgo(Ngo ngo) throws UniqueConstraintViolationException; 
 
public List<Ngo> findNgo() throws NoSuchNgoFoundException; 
 
public Ngo findNgoByName(String ngoName) throws NoSuchUserFoundException; 
 
public void removeNgoByName(String ngoName) throws NoSuchNgoFoundException; 
 
public List<Donor> findDonor() throws NoSuchUserFoundException; 
 
public List<Donation> findDonation() throws NoDonationFoundException; 
 
public boolean addEvent(CharityEvents event) throws NoSuchEventFoundException; 
 
public List<CharityEvents> findEvents() throws NoSuchEventFoundException; 
 
public Double totalDonations() throws NoDonationFoundException; 
 
// For Mail 
 
public void mailService(JavaMailSender javaMailSender); 
 
public void sendGreetingMail(Donor donor); 
 
} 
