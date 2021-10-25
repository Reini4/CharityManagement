package com.netjstech.repository; 
 
import javax.transaction.Transactional; 
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository; 
 
import com.netjstech.entities.Donation; 
 
@Repository("donationRepository") 
public interface DonationRepository extends JpaRepository<Donation, Integer> { 
    @Transactional  
@Query(value = "SELECT SUM(d.amount) FROM DONATION_TABLE d", nativeQuery = true) 
public Double sumDonations(); 
 
     
 
} 
