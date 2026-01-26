package org.serjlemast.dourssproducer.application.scheduler;

import lombok.RequiredArgsConstructor;
import org.serjlemast.dourssproducer.application.usecase.CollectJobsUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyJobCollector {

    private final CollectJobsUseCase useCase;

    @Scheduled(cron = "0 0 */6 * * *")
    public void run() {
        useCase.collect();
    }
}