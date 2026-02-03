package org.serjlemast.dourssproducer.application.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.serjlemast.dourssproducer.application.usecase.CollectJobsUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyJobCollector {

    private final CollectJobsUseCase useCase;

    @Scheduled(cron = "${cron.job}")
    public void run() {
        log.info("start scheduling processing jobs");
        useCase.collect();
    }
}