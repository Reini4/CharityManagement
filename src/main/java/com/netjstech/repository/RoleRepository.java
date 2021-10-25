package com.netjstech.repository; 
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
 
import com.netjstech.entities.Role; 
 
@Repository("roleRepository") 
public interface RoleRepository extends JpaRepository<Role, Integer>{ 
 
} 
