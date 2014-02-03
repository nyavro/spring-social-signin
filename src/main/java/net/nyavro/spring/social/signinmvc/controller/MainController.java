package net.nyavro.spring.social.signinmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController extends CommonController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String createForm(Model model, Principal principal) throws IOException {
        return "/index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "login_error", required = false) Boolean loginError, Model model) {
        model.addAttribute("error", loginError);
        return "/index";
    }
}
