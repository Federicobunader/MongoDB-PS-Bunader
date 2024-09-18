package com.kitchensink.with.spring.boot.kitchensinkwithspringboot.rest;

import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.entities.Member;
import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.repositories.MemberRepository;
import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.services.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberResourceRESTService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    MemberRegistration registration;

    @GetMapping
    public List<Member> listAllMembers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> lookupMemberById(@PathVariable("id") Long id) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody Member member, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        try {
            registration.register(member);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}