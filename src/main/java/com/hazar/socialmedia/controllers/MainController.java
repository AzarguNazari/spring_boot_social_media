package com.hazar.socialmedia.controllers;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hazar.socialmedia.models.Message;
import com.hazar.socialmedia.models.Role;
import com.hazar.socialmedia.models.User;
import com.hazar.socialmedia.services.MessageService;
import com.hazar.socialmedia.services.RoleService;
import com.hazar.socialmedia.services.UserService;
import com.hazar.socialmedia.validators.UserValidator;

@Controller
public class MainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);


	private final UserValidator uValidator;
	private final UserService uService;
	private final StatusService sService;
	private final MessageService mService;
	private final MessageReplyService messageReplyService;
	private final StatusReplyService statusReplyService;
	private final RoleService roleService;

	
	public MainController(UserValidator uValidator, UserService uService, StatusService sService, MessageService mService, MessageReplyService messageReplyService,StatusReplyService statusReplyService, RoleService roleService) {
		this.uValidator = uValidator;
		this.uService = uService;
		this.sService = sService;
		this.mService = mService;
		this.messageReplyService = messageReplyService;
		this.statusReplyService = statusReplyService;
		this.roleService = roleService;
	}
	
	@RequestMapping("/login")
	public String loginForm(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttribute, @RequestParam(value="error", required=false) String error,
    		@RequestParam(value="logout", required=false) String logout, Model model) {

		LOGGER.debug("made it back to login");
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
		redirectAttribute.addFlashAttribute("regSuc", "Thank you for registring, please log in to continue");
		return "loginreg.html";
	}
	@PostMapping("/fileUpload")
	public String handleFileUpload(Principal principal, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
    	String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
        Long logged_user_id = loggedUser.getId();
        LOGGER.debug("ID of person logged in is: " + loggedUser.getId());
        LOGGER.debug("Logged in User object is: " + loggedUser);
        //Making the Long to string to work in the URI
        String string_logged_user_id = logged_user_id.toString(); 
		if (!file.isEmpty()) {
             try {
                 String uploadsDir = "/profilepics/";
                 String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                 if(! new File(realPathtoUploads).exists())
                 {
                     new File(realPathtoUploads).mkdir();
                 }

                 LOGGER.debug("realPathtoUploads = {}" + realPathtoUploads);


                 String orgName = file.getOriginalFilename();
                 String filePath = realPathtoUploads + orgName;
                 File dest = new File(filePath);
                 file.transferTo(dest);
             } catch(IOException e) {
            	 LOGGER.debug("there was an IO error");
             }
         }
		 return "redirect:/users/".concat(string_logged_user_id);

	}
         
  
	@PostMapping("/registration")
	public String registerUser(@RequestParam("email") String user_email_input, @Valid @ModelAttribute("user") User user, BindingResult result,  RedirectAttributes redirectAttribute) {
		uValidator.validate(user, result);
		//Grabbing all emails to compare a .contains().
		List<String> all_user_emails = uService.findAllEmails();
		LOGGER.debug(user_email_input);
		///Checking to see if duplicate email exists and throwing error otherwise (there may be a better way to do it but i'm making this work right now)
		if(all_user_emails.contains(user_email_input)) {
			LOGGER.debug("That email is a duplicate");
			redirectAttribute.addFlashAttribute("emailDuplicateError", "That email already exists");
			return "redirect:/login";
		};
		if(result.hasErrors()) {
			LOGGER.debug("DID NOT PASS VALIDATIONS");
			return "loginreg.html";
		} else {
			//To make first created User (me) an Admin
			LOGGER.debug("PASSED VALIDATIONS ");
			if(uService.findAll().size() == 0) {
				uService.saveUserWithAdminRole(user);
				LOGGER.debug("Saved AS AN ADMIN ROLE");
			} else {
				
				redirectAttribute.addFlashAttribute("regSuc", "Thank you for registering, please log into to continue");
				uService.saveWithUserRole(user);
				LOGGER.debug("SAVED as USER ROLE");

			}
			
			
			LOGGER.debug("Saved USER");
			
			redirectAttribute.addFlashAttribute("regSuc", "Thank you for registering, please log into to continue");

			return "redirect:/login";
		}
		
	}
	//I believe this route must be gone through first because of Spring security
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model, @Valid @ModelAttribute("status") Status status, @ModelAttribute("message") Message message, @Valid @ModelAttribute("statusReply") StatusReply statusReply, @Valid @ModelAttribute("message_reply") MessageReply messageReply) {
    	//this route is primarily to redirect to our main route
    	//grabbing logged in user object starting with principal
    	String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
        Long logged_user_id = loggedUser.getId();
        LOGGER.debug("ID of person logged in is: " + loggedUser.getId());
        LOGGER.debug("Logged in User object is: " + loggedUser);
        //Making the Long to string to work in the URI
        String string_logged_user_id = logged_user_id.toString();
        return "redirect:/users/".concat(string_logged_user_id);
    }
    
    //A selected User's Profile
    @RequestMapping("/users/{user_id}")
    public String show(@PathVariable("user_id") Long id, Model model, @ModelAttribute("message") Message message, @ModelAttribute("status") Status status, @Valid @ModelAttribute("message_reply") MessageReply messageReply, @Valid @ModelAttribute("statusReply") StatusReply statusReply, Principal principal) {
        // 1
    	//grabbing logged in user object starting with principal
        String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
        //
        // Adding loggedUser object to be used in the JSP
        model.addAttribute("currentUser", loggedUser);
        //finding the person you clicked on
        User selected_user_object = uService.findOne(id);
        //Finding people who invited me
        List<User> inviting_users = selected_user_object.getInvitedUserFriends();
        
        // Adding them to be used in  the JSP
        model.addAttribute("users", inviting_users);
        
        //Getting people who I am connected with (the getUserFriends and getFriends is because the many to many relationship)
        List<User> list = new ArrayList<>();
        for(User u : selected_user_object.getUserFriends()) {
            
            list.add(u);
        }
        for(User u : selected_user_object.getFriends()) {
            
            list.add(u);
        }
        List<User> invited_me = selected_user_object.getInvitedUserFriends();
        LOGGER.debug("The list  of user objects who are your friend are: " + list);
        LOGGER.debug("amount of people who invited me: " + invited_me);
        LOGGER.debug("Roles for this USER are: " + loggedUser.getRoles());
        
        //Test Area  (The 2 is referring to the role id of 2 which is ADMIN)
        Long ADMIN_ROLE_ID = (long) 2;
        Role ADMIN_ROLE_OBJECT = roleService.findOne(ADMIN_ROLE_ID);
        if(loggedUser.getRoles().contains(ADMIN_ROLE_OBJECT)) {
        	LOGGER.debug("--TEST WORKED----------------------------------");
        }else {
        	LOGGER.debug("PRINT TEST DID NOT WORK------------------------------------");

        }
        List<Status> TEST_list_to_reverse = sService.findWallStatuses(selected_user_object.getId());
        
        Collections.reverse(TEST_list_to_reverse);
        
        //
        //User object of who we clicked on
        model.addAttribute("user_to_render", selected_user_object);
       
        model.addAttribute("currentUser", loggedUser);
        model.addAttribute("them", list);
        model.addAttribute("user_statuses", selected_user_object.getStatuses());
        model.addAttribute("wall_statuses", sService.findWallStatuses(selected_user_object.getId()));
        model.addAttribute("ADMIN_ROLE_OBJECT", ADMIN_ROLE_OBJECT);
        model.addAttribute("TESTING123", TEST_list_to_reverse);


    	model.addAttribute("wall_messages", mService.findWallMessages(selected_user_object.getId()));
    	
        return "profile.html";
    }
    
    @PostMapping("/search")
    public String searchUser(@RequestParam("name") String name) {
    	if(name == "") {
    		return "redirect:/users";
    	}
    	return "redirect:/search/".concat(name);
    }
    @RequestMapping("/search/{name}")
    public String searchRender(@PathVariable("name") String name, Model model, Principal principal, RedirectAttributes redirectAttrs) {
		LOGGER.debug("printing what user typed into search bar: " + name);
		//getting logged in user object
        String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
        //
		List<User>  users_searched = uService.searchByName(name);
		LOGGER.debug("User objects with related name: " + users_searched);
		users_searched.remove(loggedUser);
		if(name == null) {
			LOGGER.debug("Empty input");
		}
    	// if query returns empty list
		if(users_searched.isEmpty()) {
			redirectAttrs.addFlashAttribute("message", "Search users by name " + "'" + name + "'" + " did not match any results");
			return "redirect:/users";
		} else {
			model.addAttribute("users", uService.searchByName(name));
			model.addAttribute("currentUser", loggedUser);

			return "search.html";
		}
    }
    @PostMapping("/searchByState")
    public String search(@RequestParam("state") String state) {
    	if(state == "") {
    		return "redirect:/users";
    	}
    	return"redirect:/searchByState/".concat(state);
    }
    @RequestMapping("/searchByState/{state}")
    public String searchUserBystate(@PathVariable("state") String state, Model model, RedirectAttributes redirectAttrs, Principal principal) {
		LOGGER.debug("printing what user typed into search bar: " + state);
        String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
		List<User>  users_searched = uService.searchByState(state);
		users_searched.remove(loggedUser);
		
		LOGGER.debug("User objects with related name: " + users_searched);
    	// if query returns empty list
		if(users_searched.isEmpty()) {
			redirectAttrs.addFlashAttribute("message", "No users are registered in " + state + " right now.");
			return "redirect:/users";
		} else {
			model.addAttribute("users", users_searched);
			model.addAttribute("state_selected", state);
			model.addAttribute("currentUser", loggedUser);

			return "search.html";
		}
    }
    @PostMapping("/searchByCity")
    public String searchByCityPostRoute(@RequestParam("city") String city) {
    	//If user inputs nothing into search box
    	if(city == "") {
    		return "redirect:/users";
    	}
    	return"redirect:/searchByCity/".concat(city);
    }
    @RequestMapping("/searchByCity/{city}")
    public String searchUserBycity(@PathVariable("city") String city, Model model, RedirectAttributes redirectAttrs, Principal principal) {
		LOGGER.debug("printing what user typed into search bar: " + city);
        String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
		List<User>  users_searched = uService.searchByCity(city);
		LOGGER.debug("User objects with related city: " + users_searched);
		users_searched.remove(loggedUser);
    	// if query returns empty list
		if(users_searched.isEmpty()) {
			redirectAttrs.addFlashAttribute("message", "Search users by city " + "'" + city + "'" + " did not match any results");
			return "redirect:/users";
		} else {
			model.addAttribute("users", users_searched);
			model.addAttribute("state_selected", city);
			model.addAttribute("currentUser", loggedUser);

			return "search.html";
		}
    }
    @RequestMapping("/deleteinvite/{user_id}")
    public String deleteInvite(@PathVariable("user_id") Long id, Model model, Principal principal) {
        // 1
    	String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
    	User inviting_user = uService.findOne(id);
    	
    	List<User> inviters = loggedUser.getInvitedUserFriends();
    	inviters.remove(inviting_user);
    	loggedUser.setInvitedUserFriends(inviters);
    	uService.save(loggedUser);
        return "redirect:/";
    }
    @RequestMapping("/cancelinvite/{user_id}")
    public String cancelInvite(@PathVariable("user_id") Long id, Principal principal) {
    	//This is essentially the INVERSE of the delete invite route
    	String current_user_email = principal.getName();
        User loggedUser = uService.findByEmail(current_user_email);
    	User person_i_want_to_cancel_invite = uService.findOne(id);

    	List<User> i_invited = loggedUser.getInvitedFriends();
    	
    	i_invited.remove(person_i_want_to_cancel_invite);
    	loggedUser.setInvitedFriends(i_invited);
    	uService.save(loggedUser);

    	return "redirect:/users";
    }
    @RequestMapping("/users")
    public String users(Model model, Principal principal) {
    	String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
    	//Using all users as a place holder for now
        LOGGER.debug("Id of the loggedUser is: " + loggedUser.getId());
        List<User> people_i_invited = loggedUser.getInvitedFriends();
        
      //Taking ALL USERS and taking out those who are friends or already invited.
        List<User> all_users = uService.findAll();
        List<User> user_friends = loggedUser.getUserFriends();
        List<User> friends = loggedUser.getFriends();
        List<User> invited_friends = loggedUser.getInvitedFriends();
        List<User> invited_user_friends = loggedUser.getInvitedUserFriends();
        all_users.removeAll(user_friends);
        all_users.removeAll(friends);
        all_users.remove(loggedUser);
        all_users.removeAll(invited_user_friends);
        //I need to remove Admin from list
        
        LOGGER.debug("Current invited friends are " + invited_friends);
        LOGGER.debug("The people I have invited are: " + people_i_invited);
        LOGGER.debug("All friends are: " + friends);
        
        model.addAttribute("i_invited", people_i_invited);
        model.addAttribute("users", all_users);
        model.addAttribute("currentUser", loggedUser);

        //^^^^^^
        return "network.html";
    }
    
    @RequestMapping("/connect/{person_to_connect_id}")
    public String connectWith(@PathVariable("person_to_connect_id") Long id, Principal principal){
    	LOGGER.debug("In /connect post route");
    	String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
    	LOGGER.debug("Inside connect route and LOGGED USER IS: " + loggedUser);
    	User connect_to_person = uService.findOne(id);
    	LOGGER.debug("The person " + loggedUser.getName() + " is trying to connect with is " + uService.findOne(id).getName());
    	LOGGER.debug("The logged friends amount is: " + loggedUser.getUserFriends().size());
    	
    	if(loggedUser.getUserFriends().size() == 0) {
    		List<User> list = new ArrayList<>();
        	list.add(connect_to_person);
        	loggedUser.setUserFriends(list);
        	List<User> user_invitations = loggedUser.getInvitedUserFriends();
        	User inviting_user = uService.findOne(id);
        	user_invitations.remove(inviting_user);
        	loggedUser.setInvitedUserFriends(user_invitations);
    	} else {
    		List<User> list = loggedUser.getUserFriends();
    		list.add(connect_to_person);
    		loggedUser.setUserFriends(list);
        	List<User> user_invitations = loggedUser.getInvitedUserFriends();
        	User inviting_user = uService.findOne(id);
        	user_invitations.remove(inviting_user);
        	loggedUser.setInvitedUserFriends(user_invitations);
    	}
    	
    	uService.save(loggedUser);
    	return "redirect:/";
    }
    @RequestMapping("/invite/{person_to_connect_id}")
    public String inviteUser(@PathVariable("person_to_connect_id") Long id, Principal principal){
    	LOGGER.debug("In /INVITE route");
    	String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
    	LOGGER.debug("Inside INVITE route and LOGGED USER IS: " + loggedUser);
    	User connect_to_person = uService.findOne(id);
    	LOGGER.debug("The person " + loggedUser.getName() + " is trying to connect with is " + uService.findOne(id).getName());
    	LOGGER.debug("The logged friends amount is: " + loggedUser.getUserFriends().size());
    	
    	if(loggedUser.getInvitedFriends().size() == 0) {
    		List<User> list = new ArrayList<>();
        	list.add(connect_to_person);
        	loggedUser.setInvitedFriends(list);
    	} else {
    		List<User> list = loggedUser.getInvitedFriends();
    		list.add(connect_to_person);
    		loggedUser.setInvitedFriends(list);
    	}
    	
    	uService.save(loggedUser);
    	return "redirect:/users";
    }
    // I may be able to consolidate this route into the other invite route
    @RequestMapping("/profile/invite/{person_to_connect_id}")
    public String profileInviteUser(@PathVariable("person_to_connect_id") Long id, Principal principal){
    	LOGGER.debug("In /INVITE route");
    	String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
    	LOGGER.debug("Inside INVITE route and LOGGED USER IS: " + loggedUser);
    	User connect_to_person = uService.findOne(id);
    	LOGGER.debug("The person " + loggedUser.getName() + " is trying to connect with is " + uService.findOne(id).getName());
    	LOGGER.debug("The logged friends amount is: " + loggedUser.getUserFriends().size());
    	
    	if(loggedUser.getInvitedFriends().size() == 0) {
    		List<User> list = new ArrayList<>();
        	list.add(connect_to_person);
        	loggedUser.setInvitedFriends(list);
    	} else {
    		List<User> list = loggedUser.getInvitedFriends();
    		list.add(connect_to_person);
    		loggedUser.setInvitedFriends(list);
    	}

    	uService.save(loggedUser);
    	//Stringifying to mkae it work in URI
    	String string_person_to_connect_id = id.toString();
    	return "redirect:/users/".concat(string_person_to_connect_id);
    }
    @RequestMapping("/profile/cancelinvite/{user_id}")
    public String profileCancelInvite(@PathVariable("user_id") Long id, Principal principal) {
    	//This is essentially the INVERSE of the delete invite route
    	String current_user_email = principal.getName();
        User loggedUser = uService.findByEmail(current_user_email);
    	User person_i_want_to_cancel_invite = uService.findOne(id);

    	List<User> i_invited = loggedUser.getInvitedFriends();
    	
    	i_invited.remove(person_i_want_to_cancel_invite);
    	loggedUser.setInvitedFriends(i_invited);
    	uService.save(loggedUser);
    	//Stringifying so it works in URI
    	String string_person_to_connect_id = id.toString();
    	return "redirect:/users/".concat(string_person_to_connect_id);
    }
    // URI parameters to set the wall ID of the status
    @PostMapping("/status/{user_to_render_id}")
    public String statusPostRoute(@PathVariable("user_to_render_id") Long user_to_render_id, @Valid @ModelAttribute("status") Status status, BindingResult result,  RedirectAttributes redirectAttribute, Principal principal) {
		if(result.hasErrors()) {
			LOGGER.debug("DID NOT PASS STATUS VALIDATIONS: Status must be more than 2 characters");
			return "redirect:/";
		} else {
	    	String email = principal.getName();
	        User loggedUser = uService.findByEmail(email);
	        Long current_user_id = loggedUser.getId();
			LOGGER.debug("Passed status validations");
	    	LOGGER.debug("Trying to save status and logged user is: : " + loggedUser);
	    	
	    	//Getting and setting
	    	List<Status> user_statuses = loggedUser.getStatuses();
	    	status.setPoster(loggedUser);
	    	status.setWall_id(user_to_render_id);
	    	user_statuses.add(status);
	    	LOGGER.debug("The statuse_body: " + status.getStatus_body());
	    	LOGGER.debug("The statuses this user has is: " + user_statuses);
	    	loggedUser.setStatuses(user_statuses);
	    	////
	    	LOGGER.debug("After setting statuses for loggedUser the loggedUser.getStatuses() is: " + loggedUser.getStatuses());
	    	sService.saveTheStatus(status);
	    	//changing to string to work in URI
	    	String string_user_to_render_id = user_to_render_id.toString();
	    	return "redirect:/users/".concat(string_user_to_render_id);

		}
		
    }
    @PostMapping("/delete/status/{status_id}/{user_to_render_id}")
    public String deleteStatus(@PathVariable("status_id") Long status_to_delete_id, @PathVariable("user_to_render_id") Long user_to_render_id) {
    	//First i will have to delete the replies, IF they exist.
		LOGGER.debug("Inside of status delete route");

    	Status status_to_delete = sService.findOne(status_to_delete_id);
    	List<StatusReply> this_status_replies = status_to_delete.getRepliedStatusMessages();
    	
    	//if there are any replies, we will delete them before deleting the status
    	if(this_status_replies.size() > 0) {
        	//Iterating through and deleting the reply's
        	for(StatusReply this_status_reply : this_status_replies) {
        		LOGGER.debug("This status reply ID is: " + this_status_reply.id);
            	statusReplyService.delete(this_status_reply.id);
        	}
    	}
    	sService.remove(status_to_delete);
    	//Stringifying to work in the URI
    	String string_user_to_render_id = user_to_render_id.toString();
    	return "redirect:/users/".concat(string_user_to_render_id);
    }
    @PostMapping("/status/reply/{status_id}/{user_that_replied_id}")
    private String replyToStatus(@Valid @ModelAttribute("statusReply") StatusReply statusReply, BindingResult result, @PathVariable("status_id") Long id, @PathVariable("user_that_replied_id") Long user_that_replied_id, Principal principal) {
    	//Grabbing logged in User Object
    	String email = principal.getName();
        User loggedUser = uService.findByEmail(email);
        //Using path variable ID to get status to reply to Object
        Status status_to_reply_to = sService.findOne(id);
        //Setting the status we replied to
        statusReply.setStatusReplyingTo(status_to_reply_to);
    	//Setting who replied (object) loggedUser
        statusReply.setUserWhoRepliedToStatus(loggedUser);
        //Saving
        statusReplyService.saveStatusReply(statusReply);
        //Stringifying the submitting user (Long) id who replied
        String submitter_id = user_that_replied_id.toString();
    	return "redirect:/users/".concat(submitter_id);
    }
    @PostMapping("/status/reply/delete/{status_reply_id}/{user_selected_id}")
    private String deleteStatusReply(@PathVariable("status_reply_id") Long status_reply_id, @PathVariable("user_selected_id") Long user_selected_id) {
    	statusReplyService.delete(status_reply_id);
    	//Stringifying user_selected_id for URI parameters to bring us back to that selected user's page
    	String user_to_render_id = user_selected_id.toString();
    	return "redirect:/users/".concat(user_to_render_id);
    }
    
    /////
    ///// ******* NOT USING THESE MESSAGE ROUTES BELOW ANYMORE BUT I AM STILL SAVING THEM IN CASE
    /////
//    @PostMapping("/message")
//    private String createMessage(@Valid @ModelAttribute("message") Message message, BindingResult result, @RequestParam("user_to_render_id") Long user_profile_id, Principal principal){
//    	LOGGER.debug("The user profile Id you submitted the message on is: " + user_profile_id);
//    	LOGGER.debug("The message is: " + message.getMessage_body());
//    	//Grabbing logged in User Object
//    	String email = principal.getName();
//        User loggedUser = uService.findByEmail(email);
//        //
//        //Getting loggedUser ID to run a query later
//        Long loggedUserID = loggedUser.getId();
//    	LOGGER.debug("The Logged in User ID is: " + loggedUserID);
//    	
//    	//Setting person who posted it
//    	message.setMessagePoster(loggedUser);
//    	// Setting user_id who's wall it was posted on
//    	message.setUser_wall_id(user_profile_id);
//    	//Using the saveMessage function I made in the Service
//    	mService.saveMessage(message);
//    	//Setting the Long ID back to a string so it works in the URL path variable
//    	String user_wall_id_string = user_profile_id.toString();
//		return "redirect:/message/".concat(user_wall_id_string); 	
//    }
//    @RequestMapping("/message/{user_id}")
//    private String postMessage(@PathVariable("user_id") String id){
//		return "redirect:/users/".concat(id);  	
//    }
//    @PostMapping("/delete/message/{message_id}/{user_to_render_id}")
//    public String deleteMessage(@PathVariable("message_id") Long message_id, @PathVariable("user_to_render_id") Long user_to_render_id) {
//    	Message message_to_delete = mService.findOne(message_id);
//    	mService.remove(message_to_delete);
//    	String string_user_to_render_id = user_to_render_id.toString();
//    	return "redirect:/users/".concat(string_user_to_render_id);
//    }
//    @PostMapping("/message/reply/{message_id}/{user_to_render_id}")
//    private String replyToMessage(@Valid @ModelAttribute("messageReply") MessageReply messageReply, BindingResult result, @PathVariable("message_id") Long id,  @PathVariable("user_to_render_id") Long user_to_render_id, Principal principal) {
//    	//Grabbing logged in User Object
//    	String email = principal.getName();
//        User loggedUser = uService.findByEmail(email);
//        //
//    	//This is the message object we are replying to
//    	Message message_to_reply = mService.findOne(id);
//    	//
//    	LOGGER.debug("The message to reply to is: " + mService.findOne(id));
//    	//Setting a reply for the message object
//    	messageReply.setMessageReplyingTo(message_to_reply);
//    	//Setting who replied (object) loggedUser
//    	messageReply.setUserWhoRepliedToMessage(loggedUser);
//    	messageReplyService.saveMessageReply(messageReply);
//    	String string_user_to_render_id = user_to_render_id.toString();
//    	return "redirect:/users/".concat(string_user_to_render_id);
//    }
    //Delete message REPLY
//    @PostMapping("/message/reply/delete/{reply_id}/{user_to_render_id}")
//    private String deleteReply(@PathVariable("reply_id") Long reply_ID_to_delete, @PathVariable("user_to_render_id") Long user_to_render_id) {
//    	messageReplyService.remove(reply_ID_to_delete);
//    	//Stringifying the user to render ID to work in the URI and get back to that user's page
//    	String string_user_to_render_id = user_to_render_id.toString();
//    	return "redirect:/users/".concat(string_user_to_render_id);
//    }

    /////
    ///// ^^^^^^^^^^^^NOT USING THE MESSAGE ROUTES ABOVE ANYMORE BUT I AM STILL SAVING THEM^^^^^^^^^^^^^^^^^^
    /////

}
