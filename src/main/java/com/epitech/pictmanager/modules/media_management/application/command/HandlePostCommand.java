package com.epitech.pictmanager.modules.media_management.application.command;

import java.util.Map;

public record HandlePostCommand(String userId, String description, Map<String, Integer> medias) {
}
