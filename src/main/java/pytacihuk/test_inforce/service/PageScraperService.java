package pytacihuk.test_inforce.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import pytacihuk.test_inforce.dto.BookResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageScraperService {

    private final BookService bookService;

    public List<BookResponse> scrape(Integer page){

        String uri = "https://books.toscrape.com/catalogue/page-" + page + ".html";
        List<BookResponse> bookList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(uri).get();
            Elements books = document.select(".product_pod");

            for (Element book: books) {
                var name = book.select("h3 > a").text();
                var price = book.select(".price_color").text();
                var status = book.select(".instock.availability").text();
                var ratingElement = book.select("[class^=star-rating]");
                var rating = ratingElement.attr("class").replace("star-rating ", "");

                bookList.add(mapper(name, price, status, rating));
            }

            bookService.save(bookList);

            return bookList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BookResponse mapper(String name, String price, String status, String rating){

        BigDecimal bigDecimalPrice = new BigDecimal(price.substring(1));
        int integerRating;

        switch (rating){
            case "One":
                integerRating = 1;
                break;
            case "Two":
                integerRating = 2;
                break;
            case "Three":
                integerRating = 3;
                break;
            case "Four":
                integerRating = 4;
                break;
            case "Five":
                integerRating = 5;
                break;
            default:
                integerRating = 0;
        }

        return BookResponse.builder()
                .name(name)
                .price(bigDecimalPrice)
                .status(status)
                .rating(integerRating)
                .build();
    }
}
