package com.kitchensink.with.spring.boot.kitchensinkwithspringboot.repositories;

import com.kitchensink.with.spring.boot.kitchensinkwithspringboot.entities.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByPhoneNumber(String phoneNumber);
    List<Member> findAllByOrderByNameAsc();
}