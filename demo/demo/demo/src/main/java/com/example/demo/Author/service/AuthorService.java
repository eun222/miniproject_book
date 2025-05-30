package com.example.demo.Author.service;

import com.example.demo.Author.domain.Author;
import com.example.demo.Author.dto.AuthorDto;
import com.example.demo.Author.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setDescription(authorDto.getDescription());

        return authorRepository.save(author);
    }

    public Author findOrCreateAuthor(String name, String description) {
        return authorRepository.findByName(name)
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(name);
                    newAuthor.setDescription(description);
                    return authorRepository.save(newAuthor);
                });
    }
}
