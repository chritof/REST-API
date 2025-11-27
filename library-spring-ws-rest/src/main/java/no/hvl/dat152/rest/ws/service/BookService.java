/**
 * 
 */
package no.hvl.dat152.rest.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat152.rest.ws.exceptions.BookNotFoundException;
import no.hvl.dat152.rest.ws.model.Book;
import no.hvl.dat152.rest.ws.repository.BookRepository;

/**
 * 
 */
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	
	public Book saveBook(Book book) {
		
		return bookRepository.save(book);
		
	}
	
	public List<Book> findAll(){
		
		return (List<Book>) bookRepository.findAll();
		
	}
	
	public Book findByISBN(String isbn) throws BookNotFoundException {
		
		Book book = null;
		try {
			book = bookRepository.findBookByISBN(isbn);
		}catch(Exception e) {
			throw new BookNotFoundException("Book with isbn = "+isbn+" not found!");
		}
		
		if (book == null)
			throw new BookNotFoundException("Book with isbn = "+isbn+" not found!");
		else
			return book;
	}

	//skal lage en metode for å oppdatere en bok:
	public Book updateBook(String isbn, Book updatedBook) throws BookNotFoundException {

		Book existingBook = findByISBN(isbn);

		existingBook.setTitle(updatedBook.getTitle());
		existingBook.setAuthors(updatedBook.getAuthors());

		return bookRepository.save(existingBook);
	}

	//skal lage en metode for å slette en bok:
	public void deleteBook(String isbn) throws BookNotFoundException {
		Book existingBook = findByISBN(isbn);
		bookRepository.delete(existingBook);
	}



}
