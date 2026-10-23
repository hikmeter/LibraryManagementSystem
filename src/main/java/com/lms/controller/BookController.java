package com.lms.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.pojo.Book;
import com.lms.service.BookService;
import com.lms.serviceImpl.BookServiceImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("showAddBook".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
			dispatcher.forward(request, response);
		}
		else if("addBook".equalsIgnoreCase(action)) {
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
			book.setStatus("Active");
			
			BookService bookService = new BookServiceImpl();
			Boolean isAdded = bookService.addBook(book);
			
			if(isAdded) {
				request.setAttribute("successMessage", "Kitap başarıyla kaydedildi!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("errorMessage", "Bir şeyler ters gitti!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
				dispatcher.forward(request, response);
			}
			}
		else {
			System.out.print("Herhangi bir method bulunamadı!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
