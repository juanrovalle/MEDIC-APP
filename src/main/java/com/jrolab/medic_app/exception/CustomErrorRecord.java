package com.jrolab.medic_app.exception;

import java.time.LocalDateTime;

public record CustomErrorRecord(LocalDateTime dateTime,
        String message,
        String Details) {

}
