package pytacihuk.test_inforce.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pytacihuk.test_inforce.dto.BookResponse;
import pytacihuk.test_inforce.model.Book;
import pytacihuk.test_inforce.repository.BookRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    //Перед збереженням перевіряє наявність книг у БД, щоб уникнути дублювання
    @Transactional
    public void save(List<BookResponse> bookResponsesList){

        List<Book> bookList = bookResponsesList.stream()
                .map(this::mapToPojo)
                .toList();

        List<String> bookNames = bookList.stream()
                .map(Book::getName)
                .toList();

        Set<String> existingNames = bookRepository.findBookByNameIn(bookNames).stream()
                    .map(Book::getName)
                    .collect(Collectors.toSet());

        List<Book> booksToSave = bookList.stream()
                .filter(book -> !existingNames.contains(book.getName()))
                .toList();

        bookRepository.saveAll(booksToSave);
    }

    private Book mapToPojo(BookResponse book){
        return Book.builder()
                .name(book.getName())
                .price(book.getPrice())
                .status(book.getStatus())
                .rating(book.getRating())
                .build();
    }
}
