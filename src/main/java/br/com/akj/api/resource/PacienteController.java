package br.com.akj.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.akj.api.dto.paciente.CadastroPacienteRequest;
import br.com.akj.api.service.paciente.CadastroPacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pacientes")
public class PacienteController {

    private final CadastroPacienteService cadastroPacienteService;

    @PostMapping
    public ResponseEntity<Object> cadastrar(final @Valid @RequestBody CadastroPacienteRequest request) {
        return ResponseEntity.ok(cadastroPacienteService.realizar(request));
    }
}
