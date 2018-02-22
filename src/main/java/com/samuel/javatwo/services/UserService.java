package com.samuel.javatwo.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.samuel.javatwo.models.User;
import com.samuel.javatwo.repositories.RoleRepository;
import com.samuel.javatwo.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)     {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        
    }
    
 // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
    // 3
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public void updateUser(User user) {
    	
    	userRepository.save(user);
    }
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public User getPerson(Long id) {
    	return userRepository.findOne(id);
    }
    public List<User> findAll(){
    	return userRepository.findAll();
    }


	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	public void save(User loggedUser) {
		// TODO Auto-generated method stub
		userRepository.save(loggedUser);
	}

	public List<User> selectNotFriends(Long loggedUser) {
		// TODO Auto-generated method stub
		return userRepository.selectNotFriends(loggedUser);
	}

	public List<User> findByFriendsNotLike(User loggedUser) {
		// TODO Auto-generated method stub
		return userRepository.findByFriendsNotLike(loggedUser);
	}

	public List<User> selectAllFriendships() {
		// TODO Auto-generated method stub
		return userRepository.selectAllFriendships();
	}

	public List<User> searchByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByNameContaining(name);
	}

	public List<User> searchByState(String state) {
		// TODO Auto-generated method stub
		return userRepository.findByStateContaining(state);
	}

	public List<User> searchByCity(String city) {
		// TODO Auto-generated method stub
		return userRepository.findByCityContaining(city);
	}

	public void deleteUser(User user_to_delete) {
		// TODO Auto-generated method stub
		userRepository.delete(user_to_delete);
	}
	//To find all emails to do an if .contains to prevent duplicate emails.
	public List<String> findAllEmails() {
		// TODO Auto-generated method stub
		return userRepository.findAllEmails();
	}


}
