package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/edit")
public class UserEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_EDIT_USERS = "/WEB-INF/views/users/edit.jsp";

	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			long id = Long.parseLong(request.getParameter("id"));
			Optional<Client> client = clientService.findById(id);
			request.setAttribute("user", client.get());
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		request.getServletContext().getRequestDispatcher(VUE_EDIT_USERS).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String email = request.getParameter("email");
		LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));

		Client client = new Client(Integer.parseInt(request.getParameter("id")), lastname, firstname, email, birthdate);

		try {

			clientService.updateClient(client);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/rentmanager/users");
	}
}
