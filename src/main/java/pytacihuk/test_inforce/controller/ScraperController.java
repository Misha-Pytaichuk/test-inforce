package pytacihuk.test_inforce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pytacihuk.test_inforce.dto.BookResponse;
import pytacihuk.test_inforce.service.PageScraperService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScraperController {

    private final PageScraperService scraperService;

    @GetMapping("/scrape")
    public List<BookResponse> scrapePage() {
        return scraperService.scrape(1);
    }

    @GetMapping("/scrape/{page}")
    public List<BookResponse> scrapeDefinedPage(@PathVariable Integer page) {
        return scraperService.scrape(page);
    }
}
