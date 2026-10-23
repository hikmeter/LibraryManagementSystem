package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.lms.dao.BookDao;
import com.lms.pojo.Book;
import com.lms.util.DbUtil;

public class BookDaoImpl implements BookDao {

	@Override
	public boolean addBook(Book book) {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			String sql = "Insert into books(title, author, category, isbn, publisher, total_copies," +
					" available_copies, status, created_at) values(?,?,?,?,?,?,?,?,?)";
			connection = DbUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setString(4, book.getIsbn());
			ps.setString(5, book.getPublisher());
			ps.setInt(6, book.getTotalCopies());
			ps.setInt(7, book.getAvailableCopies());
			ps.setString(8, book.getStatus());
			Date sqlDate = new Date(book.getCreatedAt().getTime());
			ps.setDate(9, sqlDate);
			int i = ps.executeUpdate();
			if(i > 0) {
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllBooksByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
