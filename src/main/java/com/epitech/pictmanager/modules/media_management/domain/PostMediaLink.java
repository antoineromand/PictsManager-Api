package com.epitech.pictmanager.modules.media_management.domain;

public class PostMediaLink {

    private final String mediaId;
    private final int position;

    public PostMediaLink(String mediaId, int position) {
        this.mediaId = mediaId;
        this.position = position;
    }

    public String getMediaId() {
        return mediaId;
    }

    public int getPosition() {
        return position;
    }
}
