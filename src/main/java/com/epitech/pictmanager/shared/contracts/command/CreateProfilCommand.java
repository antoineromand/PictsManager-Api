package com.epitech.pictmanager.shared.contracts.command;

import java.util.UUID;

public record CreateProfilCommand(String description, String coverPicture, String picture, String publicId) {
}
