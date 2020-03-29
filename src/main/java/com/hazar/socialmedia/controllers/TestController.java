package com.hazar.socialmedia.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hazar.socialmedia.models.Message;
import com.hazar.socialmedia.models.User;
import com.hazar.socialmedia.services.MessageService;
import com.hazar.socialmedia.services.UserService;
import com.hazar.socialmedia.validators.UserValidator;

@Controller
@RequestMapping("/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);


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
	//Trying to make show profile render with profile.html template
    @RequestMapping("/users/{user_id}")
    public String testingShow(@PathVariable("user_id") Long id, Model model, @ModelAttribute("message") Message message, Principal principal) {
        // 1
        String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
        model.addAttribute("currentUser", loggedUser);
        //finding searched user object
        User selected_user_object = uService.findOne(id);
//        LOGGER.debug("ID of person logged in is: " + loggedUser.getId());
//        LOGGER.debug("Logged in User object is: " + loggedUser);
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
        LOGGER.debug("The list is: " + list);
        LOGGER.debug("amount of people who invited me: " + invited_me);
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
        LOGGER.debug("The listTest of objects is: " + listTest);
    	//
    	//Getting message replies
        return "profile.html";
    }
}
