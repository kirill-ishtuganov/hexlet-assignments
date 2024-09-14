package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = repository.findAll();
        var result = authors.stream()
                .map(authorMapper::map)
                .toList();
        return result;
    }

    public AuthorDTO create(AuthorCreateDTO aData) {
        var author = authorMapper.map(aData);
        repository.save(author);
        var authorDTO = authorMapper.map(author);
        return authorDTO;
    }

    public AuthorDTO findById(Long id) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var authorDTO = authorMapper.map(author);
        return authorDTO;
    }

    public AuthorDTO update(AuthorUpdateDTO aData, Long id) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        authorMapper.update(aData, author);
        repository.save(author);
        var authorDTO = authorMapper.map(author);
        return authorDTO;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
