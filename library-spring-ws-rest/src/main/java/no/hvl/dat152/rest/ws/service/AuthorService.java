/**
 * 
 */
package no.hvl.dat152.rest.ws.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat152.rest.ws.model.Author;
import no.hvl.dat152.rest.ws.repository.AuthorRepository;

import java.util.List;

/**
 * 
 */
@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	public Author findById(int id) {
		
		return authorRepository.findById(id).get();
	}

	//meetode for å finne alle Authors med deres bøker:
	public List<Author> findAll(){
		return (List<Author>) authorRepository.findAll();
	}

	//oppdatere en Author:
	public Author updateAuthor(Author author, int id) {
		Author existingAuthor = authorRepository.findById(id).get();
		existingAuthor.setFirstname(author.getFirstname());
		existingAuthor.setLastname(author.getLastname());
		existingAuthor.setBooks(author.getBooks());
		return authorRepository.save(existingAuthor);
	}

	//slette en Author:
	public void deleteAuthor(int id) {
		Author existingAuthor = findById(id);
		authorRepository.delete(existingAuthor);
	}
}
