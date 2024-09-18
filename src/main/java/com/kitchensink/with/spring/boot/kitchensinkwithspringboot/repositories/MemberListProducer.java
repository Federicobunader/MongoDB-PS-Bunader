package com.kitchensink.with.spring.boot.kitchensinkwithspringboot.repositories;

import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.entities.Member;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class MemberListProducer {

    @Autowired
    private MemberRepository memberRepository;

    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void onMemberListChanged(Member member) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        members = memberRepository.findAllByOrderByNameAsc();
    }
}