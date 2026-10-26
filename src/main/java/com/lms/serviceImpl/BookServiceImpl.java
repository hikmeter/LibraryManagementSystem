package com.lms.serviceImpl;

import java.util.Date;
import java.util.List;

import com.lms.dao.BookDao;
import com.lms.daoImpl.BookDaoImpl;
import com.lms.pojo.Book;
import com.lms.pojo.BookIssued;
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

	@Override
	public List<Book> getAllAvailableBookList() {
		return bookDao.getAllAvailableBookList();
	}

	@Override
	public boolean assignBook(BookIssued bookIssued) {
		bookIssued.setIssueDate(new Date());
		bookIssued.setStatus("ISSUED");
		
		boolean isAssigned = false;
		Book book = bookDao.getBookById(bookIssued.getBook().getBookId());
		if(book != null) {
			int availableCopies = book.getAvailableCopies() - 1;
			boolean isUpdated = bookDao.updateAvailableBook(bookIssued.getBook().getBookId(), availableCopies);
			if(isUpdated) {
				isAssigned = bookDao.assignBook(bookIssued);
				if(!isAssigned) {
					Book book1 = bookDao.getBookById(bookIssued.getBook().getBookId());
					int newAvailableCopies = book1.getAvailableCopies() + 1;
					bookDao.updateAvailableBook(bookIssued.getBook().getBookId(), newAvailableCopies);
				}
			}
			
		}
		return isAssigned;
	}

	@Override
	public boolean updateAvailableBook(long bookId, int availableCopies) {
		return bookDao.updateAvailableBook(bookId, availableCopies);
	}

	@Override
	public List<BookIssued> getAllIssuedBookList() {
		return bookDao.getAllIssuedBookList();
	}

	@Override
	public BookIssued getBookIssuedById(long id) {
		return bookDao.getBookIssuedById(id);
	}

	@Override
	public boolean updateBookReturn(BookIssued bookIssued) {
		bookIssued.setStatus("RETURNED");
		
		boolean isReturned = false;
		BookIssued bookIssued2 = bookDao.getBookIssuedById(bookIssued.getIssueID());
		if(bookIssued2 != null) {
			int availableCopies = bookIssued2.getBook().getAvailableCopies() + 1;
			boolean isUpdated = bookDao.updateAvailableBook(bookIssued2.getBook().getBookId(), availableCopies);
			if(isUpdated) {
				isReturned = bookDao.updateBookReturn(bookIssued);
				if(!isReturned) {
					BookIssued bookIssued3 = bookDao.getBookIssuedById(bookIssued.getIssueID());
					int newAvailableCopies = bookIssued3.getBook().getAvailableCopies() + 1;
					bookDao.updateAvailableBook(bookIssued3.getBook().getBookId(), newAvailableCopies);
				}
			}
			
		}
		return isReturned;
	}

}
