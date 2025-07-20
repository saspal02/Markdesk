package com.saswat.bookmarker;

import com.saswat.bookmarker.domain.Bookmark;
import com.saswat.bookmarker.domain.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final BookmarkRepository repository;

    @Override
    public void run(String... args) {
            for (int i = 1; i <= 100; i++) {
                repository.save(new Bookmark(null, "Bookmark " + i, "Description for bookmark " + i, Instant.now()));
            }

        }
}

