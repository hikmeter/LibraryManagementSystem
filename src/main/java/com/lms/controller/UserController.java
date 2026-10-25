package com.lms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.pojo.User;
import com.lms.service.BookService;
import com.lms.service.UserService;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
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
		if ("showAddUser".equalsIgnoreCase(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addUser.jsp");
			dispatcher.forward(request, response);
		} else if ("addUser".equalsIgnoreCase(action)) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phoneNo = request.getParameter("phone");
			String address = request.getParameter("address");

			User user = new User();
			user.setAddress(address);
			user.setCreatedAt(new Date());
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setPhoneNo(phoneNo);
			user.setRole("USER");

			UserService userService = new UserServiceImpl();
			boolean isAdded = userService.addUser(user);
			if (isAdded) {
				List userList = new ArrayList<>();
				userList = userService.getAllUsers();
				if (userList != null && userList.size() > 0) {
					request.setAttribute("userList", userList);
					request.setAttribute("successMessage", "Kullanıcı başarıyla kaydedildi!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Bir şeyler ters gitti!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addUser.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage", "Bir şeyler ters gitti!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addUser.jsp");
				dispatcher.forward(request, response);
			}
		} else if ("allUsersList".equalsIgnoreCase(action)) {
			List userList = new ArrayList<>();
			UserService userService = new UserServiceImpl();
			userList = userService.getAllUsers();
			if (userList != null && userList.size() > 0) {
				request.setAttribute("userList", userList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/userList.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if("deleteUser".equalsIgnoreCase(action)) {
			long userId = Integer.parseInt(request.getParameter("userId"));
			List userList = new ArrayList<>();
			UserService userService = new UserServiceImpl();
			boolean isDeleted = userService.deleteUser(userId);
			if(isDeleted) {
				userList = userService.getAllUsers();
				if (userList != null && userList.size() > 0) {
					request.setAttribute("successMessage", "Kullanıcı başarıyla silindi!");
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/userList.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {
				request.setAttribute("errorMessage", "Bir şeyler ters gitti!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/userList.jsp");
				dispatcher.forward(request, response);
			}
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
