package br.com.alura.forum.resources;

import br.com.alura.forum.config.security.TokenService;
import br.com.alura.forum.dto.TokenDTO;
import br.com.alura.forum.form.FormLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile(value = {"prod", "test"})
public class AuthenticationResource {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid FormLogin form) {
        UsernamePasswordAuthenticationToken credentials = form.converter();
        try {
            Authentication authenticate = authenticationManager.authenticate(credentials);
            String jwtToken = tokenService.createToken(authenticate);
            return ResponseEntity.ok(new TokenDTO(jwtToken, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
