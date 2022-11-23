package com.swapp.swapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.swapp.swapp.GoogleBooksAPI.GeolocationAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.swapp.swapp.model.Users;
import com.swapp.swapp.service.UsersService;

@Controller
public class PerfilController {

    @Autowired
    UsersService usersService;

    @GetMapping("/perfil")
    public String getRegisterPage(Model model) {
        model.addAttribute("userDetails", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "perfil";
    }

    @GetMapping("/user")
    public String getUserProfile(Model model,  HttpServletRequest request){
        String name = request.getUserPrincipal().getName();
        Users u = usersService.findUser(name);
        model.addAttribute("books", u.getBookslist());
        model.addAttribute("userDetails", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("booksquantity", u.getBookNumbers());
        return "user";
    }

    @GetMapping("/geo")
    public String getGeoPage(Model model){
        return "geolocation";
    }

    @PostMapping("/geo")
    public String getLocation(Model model){
        GeolocationAPI g = new GeolocationAPI();
        JsonNode j = g.getBookDetails("59612205");
        String s = j.get("results").get(0).get("address_components").get(4).get("long_name").toString();
        model.addAttribute("oi", s);

        return "geolocation";
    }

}
