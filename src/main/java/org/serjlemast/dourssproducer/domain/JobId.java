package org.serjlemast.dourssproducer.domain;

public record JobId(String value) {
    public static JobId fromGuid(String guid) {
        return new JobId(guid);
    }
}