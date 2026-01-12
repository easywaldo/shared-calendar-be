package com.sharedcalendar.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @field:NotBlank(message = "아이디는 필수입니다")
    @field:Size(min = 4, max = 50, message = "아이디는 4~50자 사이여야 합니다")
    val memberId: String,

    @field:NotBlank(message = "비밀번호는 필수입니다")
    @field:Size(min = 6, max = 100, message = "비밀번호는 6~100자 사이여야 합니다")
    val password: String,

    @field:NotBlank(message = "이름은 필수입니다")
    @field:Size(max = 100, message = "이름은 100자 이하여야 합니다")
    val name: String,

    @field:NotBlank(message = "이메일은 필수입니다")
    @field:Email(message = "유효한 이메일 형식이어야 합니다")
    val email: String
)

data class SignUpResponse(
    val id: Long,
    val memberId: String,
    val name: String,
    val email: String,
    val message: String = "회원가입이 완료되었습니다"
)

data class ErrorResponse(
    val code: String,
    val message: String
)
