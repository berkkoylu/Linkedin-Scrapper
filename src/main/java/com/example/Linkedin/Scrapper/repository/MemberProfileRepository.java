package com.example.Linkedin.Scrapper.repository;

import com.example.Linkedin.Scrapper.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {

    MemberProfile findByName(String name);
    MemberProfile findTopByProfileUrlEquals(String profileUrl);
}
