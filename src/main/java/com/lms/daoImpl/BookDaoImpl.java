
package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.BookDao;
import com.lms.pojo.Book;
import com.lms.pojo.BookIssued;
import com.lms.pojo.User;
import com.lms.util.DbUtil;

public class BookDaoImpl implements BookDao {

    @Override
    public boolean addBook(Book book) {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO books(title, author, category, isbn, publisher, total_copies, "
                    + "available_copies, status, created_at) VALUES(?,?,?,?,?,?,?,?,?)";

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

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean updateBook(Book book) {
    	ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE books SET title = ?, author = ?, category = ?, isbn = ?, publisher = ?, total_copies = ? "
                    + "WHERE book_id = ?";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getPublisher());
            ps.setInt(6, book.getTotalCopies());
            ps.setLong(7, book.getBookId());

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<Book> bookList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM books WHERE is_deleted = FALSE ORDER BY book_id DESC";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bookList.add(mapBookRecord(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
    }

    @Override
    public List<Book> getAllBooksByStatus(String status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Book getBookById(long id) {
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "SELECT * FROM books WHERE book_id = ?";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                return mapBookRecord(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

	@Override
	public boolean deleteBook(long id) {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE books SET is_deleted = TRUE "
                    + "WHERE book_id = ?";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setLong(1, id);

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
	}

	@Override
	public List<Book> getAllAvailableBookList() {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<Book> bookList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM books WHERE is_deleted = FALSE AND available_copies > 0 ORDER BY book_id DESC";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bookList.add(mapBookRecord(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
	}

	@Override
	public boolean assignBook(BookIssued bookIssued) {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO book_issued(book_id, user_id, issue_date, due_date, status,"
                    + "assignment_notes) VALUES(?,?,?,?,?,?)";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setLong(1, bookIssued.getBook().getBookId());
            ps.setLong(2, bookIssued.getUser().getUserId());
            Date issuedDate = new Date(bookIssued.getIssueDate().getTime());
            ps.setDate(3, issuedDate);
            ps.setDate(4, Date.valueOf(bookIssued.getDueDate()));
            ps.setString(5, bookIssued.getStatus());
            ps.setString(6, bookIssued.getAssignmentNotes());

            

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
	}

	@Override
	public boolean updateAvailableBook(long bookId, int availableCopies) {
    	ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE books SET available_copies = ? "
                    + "WHERE book_id = ?";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, availableCopies);
            ps.setLong(2, bookId);

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

	@Override
	public List<BookIssued> getAllIssuedBookList() {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<BookIssued> bookList = new ArrayList<>();

        try {
            String sql = "SELECT bi.*, b.*,u.* FROM book_issued bi inner join"
            		+ " books b on bi.book_id = b.book_id inner join users u on u.user_id = bi.user_id WHERE bi.status = 'ISSUED'";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bookList.add(mapIssuedRecord(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
	}

	@Override
	public BookIssued getBookIssuedById(long id) {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "SELECT bi.*, b.*,u.* FROM book_issued bi inner join"
            		+ " books b on bi.book_id = b.book_id inner join users u on u.user_id = bi.user_id WHERE bi.issue_id = ?";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                return mapIssuedRecord(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
	}

	@Override
	public boolean updateBookReturn(BookIssued bookIssued) {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE book_issued SET return_date = ?, book_condition = ?, return_notes = ?, status = ? "
                    + "WHERE issue_id = ?";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(bookIssued.getReturnDate()));
            ps.setString(2, bookIssued.getBookCondition());
            ps.setString(3, bookIssued.getReturnNotes());
            ps.setString(4, bookIssued.getStatus());
            ps.setLong(5, bookIssued.getIssueID());

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
	
	
	public Book mapBookRecord(ResultSet rs) throws SQLException {
	    Book book = new Book();

	    book.setBookId(rs.getLong("book_id"));
	    book.setAuthor(rs.getString("author"));
	    book.setAvailableCopies(rs.getInt("available_copies"));
	    book.setCategory(rs.getString("category"));
	    book.setCreatedAt(rs.getDate("created_at"));
	    book.setIsbn(rs.getString("isbn"));
	    book.setPublisher(rs.getString("publisher"));
	    book.setStatus(rs.getString("status"));
	    book.setTitle(rs.getString("title"));
	    book.setTotalCopies(rs.getInt("total_copies"));

	    return book;
	}
	
	public BookIssued mapIssuedRecord(ResultSet rs) throws SQLException {
	    BookIssued bookIssued = new BookIssued();

	    // Ödünç alma bilgileri
	    bookIssued.setIssueID(rs.getLong("issue_id"));
	    bookIssued.setIssueDate(rs.getDate("issue_date"));
	    bookIssued.setDueDate(rs.getDate("due_date").toLocalDate());
	    bookIssued.setStatus(rs.getString("status"));
	    bookIssued.setBookCondition(rs.getString("book_condition"));
	    bookIssued.setAssignmentNotes(rs.getString("assignment_notes"));
	    bookIssued.setReturnNotes(rs.getString("return_notes"));

	    // Kitap bilgileri
	    Book book = mapBookRecord(rs);
	    bookIssued.setBook(book);

	    // Kullanıcı bilgileri
	    User user = new User();
	    user.setUserId(rs.getLong("user_id"));
	    user.setFirstName(rs.getString("first_name"));
	    user.setLastName(rs.getString("last_name"));
	    user.setEmail(rs.getString("email"));

	    bookIssued.setUser(user);

	    return bookIssued;
	}

	@Override
	public List<BookIssued> getAllIssuedBookListForDashboard() {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<BookIssued> bookList = new ArrayList<>();

        try {
            String sql = "SELECT bi.*, b.*,u.* FROM book_issued bi inner join"
            		+ " books b on bi.book_id = b.book_id inner join users u on u.user_id = bi.user_id WHERE bi.status = 'ISSUED'"
            		+ " ORDER BY bi.issue_id DESC LIMIT 5";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bookList.add(mapIssuedRecord(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
	}
	}
