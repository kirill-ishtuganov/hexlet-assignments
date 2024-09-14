package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import exercise.repository.AuthorRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper postMapper;

    public List<BookDTO> getAll() {
        var books = repository.findAll();
        var result = books.stream()
                .map(postMapper::map)
                .toList();
        return result;
    }

    public BookDTO create(BookCreateDTO bData) {
        var book = postMapper.map(bData);
        var authorID = bData.getAuthorId();
        var author = authorRepository.findById(authorID)
                .orElseThrow(() -> new ConstraintViolationException(new HashSet<>()));
        book.setAuthor(author);
        repository.save(book);
        var bookDTO = postMapper.map(book);
        return bookDTO;
    }

    public BookDTO findById(Long id) {
        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var bookDTO = postMapper.map(book);
        return bookDTO;
    }

    public BookDTO update(BookUpdateDTO bData, Long id) {
        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        postMapper.update(bData, book);
        repository.save(book);
        var bookDTO = postMapper.map(book);
        return bookDTO;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
