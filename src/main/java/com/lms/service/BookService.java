package com.lms.service;

import java.util.List;

import com.lms.pojo.Book;
import com.lms.pojo.BookIssued;

public interface BookService {
	public boolean addBook(Book book);
	
	public boolean updateBook(Book book);

	public List<Book> getAllBooks();
	
	public List<Book> getAllBooksByStatus(String status);
	
	public Book getBookById(long id);
	
	public boolean deleteBook(long id);
	
	public List<Book> getAllAvailableBookList();
	
	public boolean assignBook(BookIssued bookIssued);
	
	public boolean updateAvailableBook(long bookId, int availableCopies);
	
	public List<BookIssued> getAllIssuedBookList();
	
	public BookIssued getBookIssuedById(long id);
	
	public boolean updateBookReturn(BookIssued bookIssued);
	
	public List<BookIssued> getAllIssuedBookListForDashboard();
}
