package com.epitech.pictmanager.shared.contracts.command;

import java.util.UUID;

public record CreateProfileCommand(String description, String coverPicture, String picture, String publicId) {
}
