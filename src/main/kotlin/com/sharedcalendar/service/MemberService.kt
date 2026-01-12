package com.sharedcalendar.service

import com.sharedcalendar.dto.SignUpRequest
import com.sharedcalendar.dto.SignUpResponse
import com.sharedcalendar.entity.Member
import com.sharedcalendar.repository.MemberRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    private val passwordEncoder = BCryptPasswordEncoder()

    @Transactional
    fun signUp(request: SignUpRequest): SignUpResponse {
        // 아이디 중복 체크
        if (memberRepository.existsByMemberId(request.memberId)) {
            throw IllegalArgumentException("이미 사용 중인 아이디입니다")
        }

        // 이메일 중복 체크
        if (memberRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다")
        }

        // 비밀번호 암호화
        val encodedPassword = passwordEncoder.encode(request.password)

        // Member 엔터티 생성 및 저장
        val member = Member(
            memberId = request.memberId,
            password = encodedPassword,
            name = request.name,
            email = request.email
        )

        val savedMember = memberRepository.save(member)

        return SignUpResponse(
            id = savedMember.id,
            memberId = savedMember.memberId,
            name = savedMember.name,
            email = savedMember.email
        )
    }
}
