package com.netjstech.repository; 
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
 
import com.netjstech.entities.Donor; 
 
@Repository("donorRepository") 
public interface DonorRepository extends  JpaRepository<Donor, Integer> { 
 
} 
