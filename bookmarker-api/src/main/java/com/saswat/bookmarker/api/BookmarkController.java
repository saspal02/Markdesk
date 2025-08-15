package com.saswat.bookmarker.api;

import com.saswat.bookmarker.domain.BookmarkDTO;
import com.saswat.bookmarker.domain.BookmarkService;
import com.saswat.bookmarker.domain.BookmarksDTO;
import com.saswat.bookmarker.domain.createBookmarkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                     @RequestParam(name = "query",defaultValue = "")String query) {
        if (query == null || query.trim().isEmpty()) {
            return bookmarkService.getBookmarks(page);
        }
        return bookmarkService.searchBookmarks(query,page);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkDTO createBookmark(@RequestBody @Valid createBookmarkRequest request) {
        return bookmarkService.createBookmark(request);



    }

}
