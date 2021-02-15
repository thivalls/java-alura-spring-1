package br.com.alura.forum.resources;

import br.com.alura.forum.form.FormLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {
    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid FormLogin form) {
        System.out.println(form.getEmail());
        System.out.println(form.getPassword());

        return ResponseEntity.ok().build();
    }
}
