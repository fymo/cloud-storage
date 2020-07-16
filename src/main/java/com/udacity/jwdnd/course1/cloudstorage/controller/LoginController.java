package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.SessionDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private SessionDTO sessionDTO;

    public LoginController(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    @GetMapping
    public String getLoginView(Model model){
        String signupSuccess = sessionDTO.getValue("signupSuccess");

        if(signupSuccess != null){
            model.addAttribute("signupSuccess", signupSuccess);
            System.out.println(signupSuccess);
        }
        sessionDTO.setAttribute("signupSuccess", null);

        return "login";
    }

}
