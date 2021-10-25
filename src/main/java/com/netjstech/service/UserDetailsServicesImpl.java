package com.netjstech.service; 
 
import java.util.ArrayList; 
import java.util.Collection; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service; 
 
import com.netjstech.entities.User; 
import com.netjstech.repository.UserRepository; 
 
@Service 
public class UserDetailsServiceImpl implements UserDetailsService { 
 
@Autowired 
private UserRepository userRepository; 
 
@Override 
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
User user = userRepository.findByUsername(username); 
if (user == null) { 
throw new UsernameNotFoundException("Username " + username + " not found"); 
} 
return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserPassword(), 
getGrantedAuthority(user)); 
} 
 
private Collection<GrantedAuthority> getGrantedAuthority(User user) { 
Collection<GrantedAuthority> authorities = new ArrayList<>(); 
if (user.getRole().getRoleName().equalsIgnoreCase("admin")) { 
authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
} 
authorities.add(new SimpleGrantedAuthority("ROLE_USER")); 
return authorities; 
} 
 
} 
Application.properties 
spring.datasource.url=jdbc:postgresql://localhost:5432/ocm 
spring.datasource.username=postgres 
spring.datasource.password=wayne 
spring.datasource.driver-class-name=org.postgresql.Driver 
spring.jpa.show-sql=true 
spring.jpa.hibernate.ddl-auto= update 
spring.jpa.open-in-view=true 
spring.mail.host=vjvijay1999@gmail.com 
spring.mail.port=5052 
spring.mail.properties.mail.smtp.auth=false 
spring.mail.properties.mail.smtp.starttls.enable=false 
 
 
gradle.build: 
 
plugins { 
id 'org.springframework.boot' version '2.5.6' 
id 'io.spring.dependency-management' version '1.0.11.RELEASE' 
id 'java' 
} 
 
group = 'com.fms' 
version = '0.0.1-SNAPSHOT' 
sourceCompatibility = '1.8' 
 
configurations { 
compileOnly { 
extendsFrom annotationProcessor 
} 
} 
 
repositories { 
mavenCentral() 
} 
 
dependencies { 
implementation 'org.springframework.boot:spring-boot-starter-data-jpa' 
implementation 'org.springframework.boot:spring-boot-starter-web' 
compileOnly 'org.projectlombok:lombok' 
developmentOnly 'org.springframework.boot:spring-boot-devtools' 
annotationProcessor 'org.projectlombok:lombok' 
implementation 'org.springframework.boot:spring-boot-starter-security' 
testImplementation 'org.springframework.boot:spring-boot-starter-test' 
testImplementation 'org.springframework.security:spring-security-test' 
implementation group: 'io.springfox', name: 'springfox-boot-starter', version: '3.0.0' 
  implementation('org.springframework.boot:spring-boot-starter-validation') 
  	implementation 'org.springframework.boot:spring-boot-starter-mail' 
testImplementation 'org.springframework.boot:spring-boot-starter-test' 
implementation 'org.springframework.boot:spring-boot-starter-data-mongodb' 
testImplementation 'org.springframework.boot:spring-boot-starter-test' 
// Postgre driver 
implementation group: 'org.postgresql', name: 'postgresql', version: '42.2.23' 
testImplementation 'org.springframework.boot:spring-boot-starter-test' 
} 
 
test { 
useJUnitPlatform() 
} 
 
 
 
 

