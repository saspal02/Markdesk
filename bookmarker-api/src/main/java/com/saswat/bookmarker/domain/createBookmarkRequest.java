package com.saswat.bookmarker.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class createBookmarkRequest {
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "URL should not be empty")
    private String url;

}
