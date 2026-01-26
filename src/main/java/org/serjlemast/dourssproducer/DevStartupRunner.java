package org.serjlemast.dourssproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.serjlemast.dourssproducer.application.usecase.CollectJobsUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("dev")
@RequiredArgsConstructor
public class DevStartupRunner implements CommandLineRunner {

    private final CollectJobsUseCase useCase;

    @Override
    public void run(String... args) {
        log.info("Dev profile detected — running RSS collection on startup");
        useCase.collect();
    }
}