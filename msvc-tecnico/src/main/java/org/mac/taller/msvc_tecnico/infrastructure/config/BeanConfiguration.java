package org.mac.taller.msvc_tecnico.infrastructure.config;

import org.mac.taller.msvc_tecnico.application.usecase.TecnicoUseCaseImpl;
import org.mac.taller.msvc_tecnico.domain.ports.inbound.ITecnicoUseCase;
import org.mac.taller.msvc_tecnico.domain.ports.outbound.ITecnicoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ITecnicoUseCase tecnicoUseCase(ITecnicoRepositoryPort tecnicoRepositoryPort) {
        return new TecnicoUseCaseImpl(tecnicoRepositoryPort);
    }
}
