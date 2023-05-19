package br.com.akj.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.akj.api.dto.seguranca.BuscarUsuarioLogadoResponse;
import br.com.akj.api.dto.seguranca.LoginRequest;
import br.com.akj.api.dto.seguranca.LoginResponse;
import br.com.akj.api.service.login.BuscaUsuarioLogadoService;
import br.com.akj.api.service.login.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final BuscaUsuarioLogadoService buscaUsuarioLogadoService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> realizarLogin(@RequestBody @Valid final LoginRequest request) {
        return ResponseEntity.ok(loginService.realizar(request));
    }

    @GetMapping
    public ResponseEntity<BuscarUsuarioLogadoResponse> buscarUsuarioLogado() {
        return ResponseEntity.ok(buscaUsuarioLogadoService.buscar());
    }
}
