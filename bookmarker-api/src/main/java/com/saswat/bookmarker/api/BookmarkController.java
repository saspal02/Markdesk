package com.saswat.bookmarker.api;

import com.saswat.bookmarker.domain.Bookmark;
import com.saswat.bookmarker.domain.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public List<Bookmark> getBookmarks(@RequestParam(name = "page",defaultValue = "1")Integer page) {
        return bookmarkService.getBookmarks(page);
    }

}
