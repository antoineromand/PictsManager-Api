package com.epitech.pictmanager.modules.media_management.application.command;

import java.util.List;

public record HandlePostCommand(String userId, String caption, List<String> mediasId) {
}
