package com.netjstech.entities; 
 
import java.util.Set; 
 
import javax.persistence.CascadeType; 
import javax.persistence.Column; 
import javax.persistence.Entities; 
import javax.persistence.FetchType; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.OneToMany; 
import javax.persistence.Table; 
 
@Entities 
@Table(name = "ROLE_TABL") 
public class Role { 
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITIES) 
@Column(name = "ROLE_ID") 
private int roleId; 
@Column(name = "ROLE_NAME") 
private String roleName; 
 
@OneToMany(targetEntities = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
private Set<User> users; 
 
public Set<User> getUsers() { 
return users; 
} 
 
public void setUsers(Set<User> users) { 
this.users = users; 
} 
 
public int getRoleId() { 
return roleId; 
} 
 
public void setRoleId(int roleId) { 
this.roleId = roleId; 
} 
 
public String getRoleName() { 
return roleName; 
} 
 
public void setRoleName(String roleName) { 
this.roleName = roleName; 
} 
} 
