package org.serjlemast.dourssproducer.domain;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class JobVacancy {
    private final String guid;
    private final String title;
    private final String link;
    private final String description;
    private final Instant publishedAt;
}