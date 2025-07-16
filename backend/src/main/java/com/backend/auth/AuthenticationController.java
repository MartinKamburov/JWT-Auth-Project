package com.backend.auth;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody RegisterRequest request
//    ){
//        return ResponseEntity.ok(authService.register(request));
//    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticateRequest(
//            @RequestBody AuthenticationRequest request
//    ){
//        return ResponseEntity.ok(authService.authenticate(request));
//    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request,
            HttpServletResponse response
    ) {
        // 1) call your existing service
        AuthenticationResponse authResp = authService.register(request);

        // 2) pack the JWT into an HttpOnly cookie
        ResponseCookie cookie = ResponseCookie.from("jwt", authResp.getToken())
                .httpOnly(true)
                .secure(true)               // in prod, only over HTTPS
                .path("/")                  // sent on all paths
                .maxAge(60 * 60)            // match your token’s expiry (in seconds)
                .sameSite("Strict")         // CSRF protection
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        // 3) still return the body (so existing clients won’t break)
        return ResponseEntity.ok(authResp);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response
    ) {
        AuthenticationResponse authResp = authService.authenticate(request);

        ResponseCookie cookie = ResponseCookie.from("jwt", authResp.getToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60 * 60)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(authResp);
    }


}
