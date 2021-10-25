package com.netjstech.service; 
 
import com.netjstech.entities.Donor; 
import com.netjstech.exception.NoDonationFoundException; 
 
public interface DonationService { 
 
public boolean addDonor(Donor donor); 
 
public Double totalDonations() throws NoDonationFoundException; 
} 
 
