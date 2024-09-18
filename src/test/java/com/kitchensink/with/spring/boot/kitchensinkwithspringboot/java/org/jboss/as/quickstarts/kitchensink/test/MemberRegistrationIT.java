package com.kitchensink.with.spring.boot.kitchensinkwithspringboot.java.org.jboss.as.quickstarts.kitchensink.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.entities.Member;
import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.services.MemberRegistration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRegistrationIT {

    @Autowired
    MemberRegistration memberRegistration;

    private static final Logger log = LoggerFactory.getLogger(MemberRegistration.class);

    @Test
    public void testRegister() throws Exception {
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");
        memberRegistration.register(newMember);
        assertNotNull(newMember.getId());
        log.info(newMember.getName() + " was persisted with id " + newMember.getId());
    }
}