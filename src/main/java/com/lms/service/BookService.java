package com.lms.service;

import java.util.List;

import com.lms.pojo.Book;

public interface BookService {
	public boolean addBook(Book book);
	
	public boolean updateBook(Book book);

	public List<Book> getAllBooks();
	
	public List<Book> getAllBooksByStatus(String status);
	
	public Book getBookById(long id);
}
