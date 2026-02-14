package com.epitech.pictmanager.shared.contracts.repositories;

public interface UserLookUpRepositoryPort {
    Long getUserIdWithPublicId(String publicId);
}
