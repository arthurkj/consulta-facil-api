package br.com.akj.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.akj.api.dto.medico.CadastroMedicoRequest;
import br.com.akj.api.service.medico.CadastroMedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/medico")
public class MedicoController {

    private final CadastroMedicoService cadastroMedicoService;

    @PostMapping
    public ResponseEntity<Long> cadastrar(final @Valid @RequestBody CadastroMedicoRequest request) {
        return ResponseEntity.ok(cadastroMedicoService.realizar(request));
    }
}
