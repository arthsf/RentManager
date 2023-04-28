package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/create")
public class VehicleCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_CREATE_CARS ="/WEB-INF/views/vehicles/create.jsp";
	
	@Autowired
	VehicleService vehicleService;
	
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	this.getServletContext().getRequestDispatcher(VUE_CREATE_CARS).forward(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String constructor = request.getParameter("constructeur");
		int nbPlaces = (byte)Integer.parseInt(request.getParameter("nb_places"));
		
		Vehicle vehicle = new Vehicle(constructor, nbPlaces);
		
		try {
			
			vehicleService.create(vehicle);
			response.sendRedirect("/rentmanager/cars");
			
		} catch (ServiceException e) {
			e.printStackTrace();
			request.getServletContext().getRequestDispatcher(VUE_CREATE_CARS).forward(request, response);
		}
	}
}
