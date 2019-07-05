package com.salt.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>{
    List<Member> findByStatusEquals(MemberStatus memberStatus);
    List<Member> findByStatusNot(MemberStatus memberStatus);
}
