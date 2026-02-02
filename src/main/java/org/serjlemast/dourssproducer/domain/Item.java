package org.serjlemast.dourssproducer.domain;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Item(String guid, String title, String content, String link, Instant date) {}