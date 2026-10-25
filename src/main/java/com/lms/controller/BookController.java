package com.lms.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.pojo.Book;
import com.lms.pojo.BookIssued;
import com.lms.pojo.User;
import com.lms.service.BookService;
import com.lms.service.UserService;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("showAddBook".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
			dispatcher.forward(request, response);
		} else if ("addBook".equalsIgnoreCase(action)) {
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			String category = request.getParameter("category");
			String publisher = request.getParameter("publisher");
			String availableCopies = request.getParameter("availableCopies");
			String totalCopies = request.getParameter("totalCopies");

			Book book = new Book();
			book.setAuthor(author);
			book.setAvailableCopies(Integer.parseInt(availableCopies));
			book.setCategory(category);
			book.setIsbn(isbn);
			book.setPublisher(publisher);
			book.setTitle(title);
			book.setTotalCopies(Integer.parseInt(totalCopies));
			book.setCreatedAt(new Date());
			book.setStatus("Uygun");

			BookService bookService = new BookServiceImpl();
			Boolean isAdded = bookService.addBook(book);

			if (isAdded) {
				List bookList = new ArrayList<>();
				bookList = bookService.getAllBooks();
				if (bookList != null && bookList.size() > 0) {
					request.setAttribute("bookList", bookList);
					request.setAttribute("successMessage", "Kitap başarıyla kaydedildi!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Bir şeyler ters gitti!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage", "Bir şeyler ters gitti!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
				dispatcher.forward(request, response);
			}
		} else if ("allBooksList".equalsIgnoreCase(action)) {
			List bookList = new ArrayList<>();
			BookService bookService = new BookServiceImpl();
			bookList = bookService.getAllBooks();
			if (bookList != null && bookList.size() > 0) {
				request.setAttribute("bookList", bookList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
				dispatcher.forward(request, response);
			}
		} else if ("viewBook".equalsIgnoreCase(action)) {
			long BookId = Integer.parseInt(request.getParameter("bookId"));
			List bookList = new ArrayList<>();
			BookService bookService = new BookServiceImpl();
			Book book = bookService.getBookById(BookId);
			if (book != null) {
				request.setAttribute("book", book);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editBook.jsp");
				dispatcher.forward(request, response);
			} else {
				bookList = bookService.getAllBooks();
				if (bookList != null && bookList.size() > 0) {
					request.setAttribute("errorMessage", "Kitap bilgisi bulunamadı!");
					request.setAttribute("bookList", bookList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
					dispatcher.forward(request, response);
				}
			}
		} else if ("updateBook".equalsIgnoreCase(action)) {
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			String category = request.getParameter("category");
			String publisher = request.getParameter("publisher");
			String totalCopies = request.getParameter("totalCopies");
			long bookId = Integer.parseInt(request.getParameter("bookId"));

			Book book = new Book();
			book.setAuthor(author);
			book.setCategory(category);
			book.setIsbn(isbn);
			book.setPublisher(publisher);
			book.setTitle(title);
			book.setTotalCopies(Integer.parseInt(totalCopies));
			book.setBookId(bookId);

			BookService bookService = new BookServiceImpl();
			Boolean isUpdated = bookService.updateBook(book);
			if (isUpdated) {
				List bookList = new ArrayList<>();
				bookList = bookService.getAllBooks();
				if (bookList != null && bookList.size() > 0) {
					request.setAttribute("bookList", bookList);
					request.setAttribute("successMessage", "Kitap başarıyla güncellendi!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage", "Bir şeyler ters gitti!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/editBook.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if("deleteBook".equalsIgnoreCase(action)) {
			long bookId = Integer.parseInt(request.getParameter("bookId"));
			List bookList = new ArrayList<>();
			BookService bookService = new BookServiceImpl();
			boolean isDeleted = bookService.deleteBook(bookId);
			if(isDeleted) {
				bookList = bookService.getAllBooks();
				if (bookList != null && bookList.size() > 0) {
					request.setAttribute("bookList", bookList);
					request.setAttribute("successMessage", "Kitap başarıyla silindi!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {
				request.setAttribute("errorMessage", "Bir şeyler ters gitti!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookList.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if("showAssignBook".equalsIgnoreCase(action)) {
			UserService userService = new UserServiceImpl();
			BookService bookService = new BookServiceImpl();
			
			List<Book> bookList = new ArrayList<>();
			bookList = bookService.getAllAvailableBookList();
			
			List<User> userList = new ArrayList<>();
			userList = userService.getAllUsers();
			
			if(bookList != null && bookList.size() > 0 && userList != null && userList.size() > 0) {
				request.setAttribute("bookList", bookList);
				request.setAttribute("userList", userList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/assignBook.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "Kitap ya da kullanıcı verileri çekilemedi!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/assignBook.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if("assignBook".equalsIgnoreCase(action)) {
			long bookId = Integer.parseInt(request.getParameter("bookId"));
			long userId = Integer.parseInt(request.getParameter("userId"));
			String dueDateParam = request.getParameter("dueDate");
			LocalDate dueDate = LocalDate.parse(dueDateParam);
			String assignmentNotes = request.getParameter("assignmentNotes");
			
			Book book = new Book();
			book.setBookId(bookId);
			
			User user = new User();
			user.setUserId(userId);
			
			BookIssued bookIssued = new BookIssued();
			bookIssued.setAssignmentNotes(assignmentNotes);
			bookIssued.setBook(book);
			bookIssued.setUser(user);
			bookIssued.setDueDate(dueDate);
			
			BookService bookService = new BookServiceImpl();
			boolean isAssigned = bookService.assignBook(bookIssued);
			if(isAssigned) {
				HttpSession session = request.getSession();
			    session.setAttribute("successMessage", "Kitap verme işlemi başarılı!");
			    response.sendRedirect("BookController?action=showAssignBook");
			}
			else {
				request.setAttribute("errorMessage", "Kitap verme işlemi başarısız!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/assignBook.jsp");
				dispatcher.forward(request, response);
			}
		}
		else {
			System.out.print("Herhangi bir method bulunamadı!");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
