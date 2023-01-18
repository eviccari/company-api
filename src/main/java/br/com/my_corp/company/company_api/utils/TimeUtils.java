package br.com.my_corp.company.company_api.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeUtils {
    
    private TimeUtils(){}

    public static final Instant now() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
    }
}
