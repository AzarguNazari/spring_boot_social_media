package com.samuel.javatwo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuel.javatwo.models.Status;
import com.samuel.javatwo.models.StatusReply;
import com.samuel.javatwo.models.User;
import com.samuel.javatwo.services.StatusReplyService;
import com.samuel.javatwo.services.StatusService;
import com.samuel.javatwo.services.UserService;
import com.samuel.javatwo.validators.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final UserValidator uValidator;
	private final UserService uService;
	private final StatusService sService;
	private final StatusReplyService statusReplyService;

	
	public AdminController(UserValidator uValidator, UserService uService, StatusService sService,StatusReplyService statusReplyService) {
		this.uValidator = uValidator;
		this.uService = uService;
		this.sService = sService;
		this.statusReplyService = statusReplyService;
	}
	
    @RequestMapping("/home")
    public String adminPage(Model model, Principal principal) {
    	//grabbing Logged in Admin user Object
    	String email = principal.getName();
        User loggedAdmin = uService.findByEmail(email);
    	//getting all users
    	List<User> all_users = uService.findAll();
    	//removing the admin from list so it can't delete itself
    	all_users.remove(loggedAdmin);
    	model.addAttribute("all_users", all_users);
    	
    	return "admin.jsp";
    }
    @PostMapping("/deleteuser/{user_id}")
    public String adminDeleteUser(@PathVariable("user_id") Long selected_user_id, Model model) {
    	//finding user to delete
    	
    	//THIS IS A WHOLE NEW CAN OF WORMS
    	
    	//THIS WILL ONLY WORK FOR USERS THAT HAVE NOTHING POSTED, OTHERWISE, POSTINGS/REPLIES WILL TRY TO RENDER BASED OFF DELETED(NULL) VALUES.
    	
    	//TO FIX THIS I WOULD HAVE TO DELETE EVERY REPLY, THEN EVERY STATUS, THEN THE USER.
    	User user_to_delete = uService.findOne(selected_user_id);
    	//ONCE AGAIN, THIS DELETE FUNCTION ONLY WORKS FOR USERS WHO HAVE POSTED NOTHING.
    	uService.deleteUser(user_to_delete);
    	return "redirect:/admin/home";
    }
    @PostMapping("/searchUserById")
    public String adminSearchById(@RequestParam("id") Long user_id, Model model) {
		System.out.println("printing what user typed into search bar: " + user_id);
		if(user_id == null) {
			System.out.println("Empty input,  redirecting back to admin home");
			return "redirect:/admin/home";
		}
		User user_searched = uService.findOne(user_id);
		System.out.println("User object with that ID is: " + user_searched);

		model.addAttribute("single_user", user_searched);
		return "adminSearchUser.jsp";			

    }
    
    @PostMapping("/searchByName")
    public String adminSearchByName(@RequestParam("name") String name, Model model) {
		System.out.println("printing what user typed into search bar: " + name);
		if(name == null) {
			System.out.println("Empty input,  redirecting back to admin home");
			return "redirect:/admin/home";
		}
		List<User> users_searched = uService.searchByName(name);
		System.out.println("User object with that Name containing is is: " + users_searched);

		model.addAttribute("users", users_searched);
		return "adminSearchUser.jsp";			

    }
}
