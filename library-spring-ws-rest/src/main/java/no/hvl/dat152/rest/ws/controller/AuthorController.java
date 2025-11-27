/**
 * 
 */
package no.hvl.dat152.rest.ws.controller;


import no.hvl.dat152.rest.ws.model.Author;
import no.hvl.dat152.rest.ws.repository.AuthorRepository;
import no.hvl.dat152.rest.ws.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 */
@RestController
@RequestMapping("/elibrary/api/v1")
public class AuthorController {

	// TODO
    @Autowired
    private AuthorService authorservice;

    //metode for å hente alle Authors:
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthors(){
        List<Author> alleAuthors = authorservice.findAll();

        if(alleAuthors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(alleAuthors, HttpStatus.OK);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, Author author){
        Author a = authorservice.updateAuthor(author, id);

        if(a == null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable int id){
        Author a = authorservice.findById(id);

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

}
