package com.saswat.bookmarker.domain;

import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public BookmarkDTO toDto(Bookmark bookmark) {
       return new BookmarkDTO(
                bookmark.getId(),
                bookmark.getTitle(),
                bookmark.getUrl(),
                bookmark.getCreatedAt()
        );
    }
}
