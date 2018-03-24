package com.example.soc.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Radosław Osiński
 */
@Controller
public class LoginPageController {

    @GetMapping(value = "/")
    public ModelAndView getAnonPagex() {
        return new ModelAndView("anonView");
    }

    @GetMapping(value = "/login")
    public ModelAndView getLoginPagex() {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/user/main")
    public ModelAndView getHelloPage() {
        return new ModelAndView("helloView");
    }

    @GetMapping(value = "/admin/main")
    public ModelAndView getAdminPage() {
        return new ModelAndView("helloAdminView");
    }

    @RequestMapping(value = "/signup")
    public String signup() {
        return "login";
    }

}
