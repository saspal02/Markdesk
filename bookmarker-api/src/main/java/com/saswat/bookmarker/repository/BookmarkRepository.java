package com.saswat.bookmarker.repository;

import com.saswat.bookmarker.domain.Bookmark;
import com.saswat.bookmarker.domain.BookmarkDTO;
import com.saswat.bookmarker.domain.BookmarkVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {

    @Query("select new com.saswat.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b")
    Page<BookmarkDTO> findBy(Pageable pageable);

    @Query(""" 
            select new com.saswat.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt)
            from Bookmark b
            where lower(b.title) like lower(concat('%', :query, '%'))
            """)
    Page<BookmarkDTO> searchBookmarks(String query, Pageable pageable);

    Page<BookmarkVM> findByTitleContainsIgnoreCase(String query, Pageable pageable);
}