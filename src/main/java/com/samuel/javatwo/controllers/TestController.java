package com.samuel.javatwo.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samuel.javatwo.models.Message;
import com.samuel.javatwo.models.Status;
import com.samuel.javatwo.models.User;
import com.samuel.javatwo.services.MessageReplyService;
import com.samuel.javatwo.services.MessageService;
import com.samuel.javatwo.services.StatusService;
import com.samuel.javatwo.services.UserService;
import com.samuel.javatwo.validators.UserValidator;

@Controller
@RequestMapping("/test")
public class TestController {
	private final UserValidator uValidator;
	private final UserService uService;
	private final StatusService sService;
	private final MessageService mService;
	private final MessageReplyService messageReplyService;
	
	public TestController(UserValidator uValidator, UserService uService, StatusService sService, MessageService mService, MessageReplyService messageReplyService) {
		this.uValidator = uValidator;
		this.uService = uService;
		this.sService = sService;
		this.mService = mService;
		this.messageReplyService = messageReplyService;
	}
	//Trying to make show profile render with profile.jsp template
    @RequestMapping("/users/{user_id}")
    public String testingShow(@PathVariable("user_id") Long id, Model model, @ModelAttribute("message") Message message, Principal principal) {
        // 1
        String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
        model.addAttribute("currentUser", loggedUser);
        //finding searched user object
        User selected_user_object = uService.findOne(id);
//        System.out.println("ID of person logged in is: " + loggedUser.getId());
//        System.out.println("Logged in User object is: " + loggedUser);
        List<User> inviting_users = selected_user_object.getInvitedUserFriends();
        model.addAttribute("users", inviting_users);
        //
        List<User> list = new ArrayList<>();
        for(User u : selected_user_object.getUserFriends()) {
            
            list.add(u);
        }
        for(User u : selected_user_object.getFriends()) {
            
            list.add(u);
        }
        List<User> invited_me = selected_user_object.getInvitedUserFriends();
        System.out.println("The list is: " + list);
        System.out.println("amount of people who invited me: " + invited_me);
        model.addAttribute("them", list);
        model.addAttribute("invited_me", invited_me);
        model.addAttribute("user_statuses", selected_user_object.getStatuses());
    	model.addAttribute("wall_messages", mService.findWallMessages(selected_user_object.getId()));
    	
    	//Trying to type out a sort objects by date function
    	List<Status> all_user_statuses = selected_user_object.getStatuses();
    	ArrayList listTest = new ArrayList();
        for(Status status : all_user_statuses) {
            
        	listTest.add(status);
        }
        for(Message message1 : selected_user_object.getMessages()) {
            
        	listTest.add(message1);
        }
        System.out.println("The listTest of objects is: " + listTest);
    	//
    	//Getting message replies
        return "profile.jsp";
    }
}
