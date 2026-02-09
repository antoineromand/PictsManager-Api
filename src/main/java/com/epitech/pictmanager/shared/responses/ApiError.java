package com.epitech.pictmanager.shared.responses;

import java.time.Instant;

public record ApiError(String code, String message, String url, Instant timestamp) {
}
