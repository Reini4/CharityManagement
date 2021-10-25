package com.netjstech.repository; 
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
 
import com.netjstech.entities.CharityEvents; 
@Repository("eventRepository") 
public interface CharityEventsRepository extends JpaRepository<CharityEvents, Integer>  { 
 
} 
