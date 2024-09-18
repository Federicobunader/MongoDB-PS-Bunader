package com.kitchensink.with.spring.boot.kitchensinkwithspringboot.services;

import org.springframework.stereotype.Service;
import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.repositories.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.entities.Member;

import java.util.Optional;

@Service
public class MemberRegistration {

    private static final Logger log = LoggerFactory.getLogger(MemberRegistration.class);

    private final MemberRepository memberRepository;

    public MemberRegistration(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());

        // Validate the member
        validateMember(member);

        memberRepository.save(member);
    }

    private void validateMember(Member member) throws Exception {
        if (member.getName() == null || member.getName().length() < 1 || member.getName().length() > 25 || member.getName().matches(".*\\d.*")) {
            throw new Exception("Invalid name");
        }

        if (member.getEmail() == null || member.getEmail().isEmpty() || !member.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new Exception("Invalid email");
        }

        if (member.getPhoneNumber() == null || member.getPhoneNumber().length() < 10 || member.getPhoneNumber().length() > 12 || !member.getPhoneNumber().matches("\\d+")) {
            throw new Exception("Invalid phone number");
        }

        // Check if the email already exists in the database
        Optional<Member> existingMemberWithEmail = memberRepository.findByEmail(member.getEmail());
        if (existingMemberWithEmail.isPresent()) {
            throw new Exception("Email already exists");
        }

        // Check if the phone number already exists in the database
        Optional<Member> existingMemberWithPhoneNumber = memberRepository.findByPhoneNumber(member.getPhoneNumber());
        if (existingMemberWithPhoneNumber.isPresent()) {
            throw new Exception("Phone number already exists");
        }
    }
}