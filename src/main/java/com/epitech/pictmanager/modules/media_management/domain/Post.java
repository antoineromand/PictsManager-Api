package com.epitech.pictmanager.modules.media_management.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Post {
    private final Long postId;
    private final Long userId;
    private String caption;
    private List<PostMediaLink> mediaLinks;

    public Post(Long postId, Long userId, String caption) {
        this.postId = postId;
        this.userId = userId;
        this.caption = caption;
    }

    public void attach(String mediaId) {
        if (containsMedia(mediaId)) {
            throw new IllegalStateException("Media already attached to post");
        }

        int nextPosition = mediaLinks.size();
        mediaLinks.add(new PostMediaLink(mediaId, nextPosition));
    }

    public void reorder(PostMediaLink postMediaLink) {
        PostMediaLink actualMediaLink = mediaLinks.stream().filter(mediaLink -> postMediaLink.getMediaId().equals(mediaLink.getMediaId())).findFirst().orElse(null);
        if (actualMediaLink != null) {
            this.mediaLinks.remove(actualMediaLink);
            this.mediaLinks.add(postMediaLink);
        }
    }

    public void detachMedia(String mediaId) {
        boolean removed = mediaLinks.removeIf(link -> link.getMediaId().equals(mediaId));
        if (removed) {
            normalizePositions();
        }
    }

    public void reorderMedia(List<String> orderedMediaIds) {
        if (orderedMediaIds.size() != mediaLinks.size()) {
            throw new IllegalArgumentException("Invalid media order");
        }

        Map<String, PostMediaLink> byId = mediaLinks.stream()
                .collect(HashMap::new,
                        (m, l) -> m.put(l.getMediaId(), l),
                        HashMap::putAll);

        mediaLinks.clear();
        for (int i = 0; i < orderedMediaIds.size(); i++) {
            String mediaId = orderedMediaIds.get(i);
            if (!byId.containsKey(mediaId)) {
                throw new IllegalArgumentException("Unknown mediaId: " + mediaId);
            }
            mediaLinks.add(new PostMediaLink(mediaId, i));
        }
    }

    public void updateCaption(String caption) {
        this.caption = caption;
    }


    private boolean containsMedia(String mediaId) {
        return mediaLinks.stream().anyMatch(l -> l.getMediaId().equals(mediaId));
    }

    private void normalizePositions() {
        for (int i = 0; i < mediaLinks.size(); i++) {
            mediaLinks.set(i, new PostMediaLink(mediaLinks.get(i).getMediaId(), i));
        }
    }

    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCaption() {
        return caption;
    }

    public List<PostMediaLink> getMediaLinks() {
        return Collections.unmodifiableList(mediaLinks);
    }
}
