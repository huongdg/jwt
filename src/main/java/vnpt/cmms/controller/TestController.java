package vnpt.cmms.controller;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vnpt.cmms.controller.login.dto.LoginMessage;
import vnpt.cmms.controller.login.dto.LoginRequest;
import vnpt.cmms.controller.login.dto.LoginResponse;
import vnpt.cmms.serurity.CmmsUserDetails;
import vnpt.cmms.serurity.JwtTokenProvider;
import vnpt.cmms.serurity.Token;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api")
//@CrossOrigin("*")
public class TestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getCode(),
                        loginRequest.getSecret()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CmmsUserDetails) authentication.getPrincipal());
        return new ResponseEntity<LoginResponse>(LoginResponse.builder()
                .code(LoginMessage.SUCCESS.getCode())
                .message(LoginMessage.SUCCESS.getMessage())
                .data(new Token(jwt))
                .build(), HttpStatus.OK);
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public ResponseEntity<String> randomStuff(){
        return new ResponseEntity<String>("oke nek", HttpStatus.OK);
    }

}
