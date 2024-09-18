package com.kitchensink.with.spring.boot.kitchensinkwithspringboot.controllers;

import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.entities.Member;
import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.services.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberRegistration memberRegistration;

    @ModelAttribute("member")
    public Member newMember() {
        return new Member();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showForm() {
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        try {
            memberRegistration.register(member);
            redirectAttributes.addFlashAttribute("message", "Registration successful");
            return "redirect:/member";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration unsuccessful: " + e.getMessage());
            return "redirect:/member";
        }
    }
}