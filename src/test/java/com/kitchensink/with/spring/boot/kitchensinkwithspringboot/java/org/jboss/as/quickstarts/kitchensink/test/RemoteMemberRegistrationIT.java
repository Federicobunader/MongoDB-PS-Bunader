package com.kitchensink.with.spring.boot.kitchensinkwithspringboot.java.org.jboss.as.quickstarts.kitchensink.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.entities.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RemoteMemberRegistrationIT {

    private static final Logger log = Logger.getLogger(RemoteMemberRegistrationIT.class.getName());

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    protected String getHTTPEndpoint() {
        return "http://localhost:" + port + "/rest/members";
    }

    @Test
    public void testRegister() throws Exception {
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newMember);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        String response = restTemplate.postForObject(getHTTPEndpoint(), entity, String.class);

        assertEquals(null, response);
    }
}