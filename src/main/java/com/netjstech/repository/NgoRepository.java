package com.netjstech.repository; 
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
 
 
import com.netjstech.entities.Ngo; 
 
@Repository("ngoRepository") 
public interface NgoRepository extends JpaRepository<Ngo, Integer> { 
 
} 
