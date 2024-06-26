package com.songthematic.songthemes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    @GetMapping("/")
    public String welcome() {
        return """
                <!doctype html>
                <html lang=en>
                <head>
                <meta charset="UTF-8">
                <title>Song Themes</title>
                </head>
                <body>
                <p>Coming soon!</p>
                </body>
                </html>
                """;
    }
}
