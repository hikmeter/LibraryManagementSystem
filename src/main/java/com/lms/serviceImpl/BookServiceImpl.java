package com.lms.serviceImpl;

import java.util.List;

import com.lms.dao.BookDao;
import com.lms.daoImpl.BookDaoImpl;
import com.lms.pojo.Book;
import com.lms.service.BookService;

public class BookServiceImpl implements BookService {
	BookDao bookDao = new BookDaoImpl();

	@Override
	public boolean addBook(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public boolean updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public List<Book> getAllBooksByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookById(long id) {
		return bookDao.getBookById(id);
	}

	@Override
	public boolean deleteBook(long id) {
		return bookDao.deleteBook(id);
	}

}
