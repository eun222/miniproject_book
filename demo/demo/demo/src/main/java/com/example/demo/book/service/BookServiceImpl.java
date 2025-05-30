package com.example.demo.book.service;

import com.example.demo.Author.domain.Author;
import com.example.demo.Author.service.AuthorService;
import com.example.demo.book.domain.Book;
import com.example.demo.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Override
    public Book insertBook(Book book) {
        String authorName = book.getAuthor().getName();
        String authorDesc = book.getAuthor().getDescription();

        Author author = authorService.findOrCreateAuthor(authorName, authorDesc);

        book.setAuthor(author);
//        author.getBooks().add(book);

        return bookRepository.save(book);
    }

    @Override
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("책을 찾을 수 없습니다.")
        );
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book b = findBook(id);
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        b.setPublisher(book.getPublisher());
        b.setPublishedDate(book.getPublishedDate());
        b.setPrice(book.getPrice());
        return bookRepository.save(b);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
