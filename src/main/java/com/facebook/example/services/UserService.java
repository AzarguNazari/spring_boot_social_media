package com.facebook.example.services;

import com.facebook.example.interfaces.UserServiceInterface;
import com.facebook.example.models.User;
import com.facebook.example.repositories.RoleRepository;
import com.facebook.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
     
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }    
    
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

	@Override
	public void deleteUser(Long userID) {
    	userRepository.deleteById(userID);
	}

	@Override
	public Optional<User> getUser(Long userID) {
		return userRepository.findById(userID);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getPerson(Long id) {
    	return userRepository.findById(id).get();
    }
    public List<User> findAll(){
    	return userRepository.findAll();
    }


	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	public void save(User loggedUser) {
		userRepository.save(loggedUser);
	}
	public void deleteUser(User user_to_delete) {
		// TODO Auto-generated method stub
		userRepository.delete(user_to_delete);
	}
}
