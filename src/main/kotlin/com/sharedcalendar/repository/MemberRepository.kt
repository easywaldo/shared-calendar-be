package com.sharedcalendar.repository

import com.sharedcalendar.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    fun findByMemberId(memberId: String): Optional<Member>
    fun findByEmail(email: String): Optional<Member>
    fun existsByMemberId(memberId: String): Boolean
    fun existsByEmail(email: String): Boolean
}
