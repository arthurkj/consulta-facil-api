package br.com.akj.api.service.seguranca;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeracaoSenhaService {

    private final PasswordEncoder passwordEncoder;

    public String gerar(@NotBlank final String senha) {
        log.debug("Gerando senha codificada");

        return passwordEncoder.encode(senha);
    }
}
