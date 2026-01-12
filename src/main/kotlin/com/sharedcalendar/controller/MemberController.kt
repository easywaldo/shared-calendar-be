package com.sharedcalendar.controller

import com.sharedcalendar.dto.ErrorResponse
import com.sharedcalendar.dto.SignUpRequest
import com.sharedcalendar.dto.SignUpResponse
import com.sharedcalendar.service.MemberService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody request: SignUpRequest): ResponseEntity<SignUpResponse> {
        val response = memberService.signUp(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(
                code = "BAD_REQUEST",
                message = e.message ?: "잘못된 요청입니다"
            )
        )
    }
}
