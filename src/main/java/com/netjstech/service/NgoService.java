package com.netjstech.service; 
 
import java.util.List; 
 
import org.springframework.mail.javamail.JavaMailSender; 
 
import com.netjstech.entities.CharityEvents; 
import com.netjstech.entities.Donation; 
import com.netjstech.entities.Ngo; 
import com.netjstech.exception.NoDonationFoundException; 
import com.netjstech.exception.NoSuchEventFoundException; 
import com.netjstech.exception.NoSuchNgoFoundException; 
 
public interface NgoService { 
public boolean addNgo(Ngo ngo);  
public List<Ngo> findNgo() throws NoSuchNgoFoundException; 
public List<Donation> findDonation() throws NoDonationFoundException; 
public List<CharityEvents> findEvent() throws NoSuchEventFoundException; 
public Double totalDonations() throws NoDonationFoundException; 
 
void sendEventMail(CharityEvents event); 
public void mailService(JavaMailSender javaMailSender); 
 
} 
 
