package com.lms.dao;

import java.util.List;

import com.lms.pojo.Book;

public interface BookDao {
	public boolean addBook(Book book);
	
	public boolean updateBook(Book book);

	public List<Book> getAllBooks();
	
	public List<Book> getAllBooksByStatus(String status);
	
	public Book getBookById(long id);
	
	public boolean deleteBook(long id);
}
