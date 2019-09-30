package com.example.react.controller;

import com.example.react.model.User;
import com.example.react.service.CustomUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User user1 = customUserDetailsService.findUserByEmail(user.getEmail());

        if (user1 != null) {
            bindingResult
                    .rejectValue("email", "user.error",
                            "Username already exists");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            customUserDetailsService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been successfully registered");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/dashborad", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = customUserDetailsService.findUserByEmail(( authentication).getName());
        modelAndView.addObject("currentuser", user);
        modelAndView.addObject("welcome", "Welcome " + user.getFullname());
        modelAndView.addObject("adminMessage", "Content only available for admin user");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
