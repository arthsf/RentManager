package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {
	
	 @InjectMocks
	   private VehicleService vehicleService;

	   @Mock
	   private VehicleDao vehicleDao;

	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void findAll_should_fail_when_dao_throws_exception() throws DaoException {
	       when(this.vehicleDao.findAll()).thenThrow(DaoException.class);

	       assertThrows(ServiceException.class, () -> vehicleService.findAll());
	   }
	   
	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void count_dao_throws_exception() throws DaoException {
	       when(vehicleDao.count()).thenThrow(DaoException.class);
	 
	       assertThrows(ServiceException.class, () -> vehicleService.count());
	   }
	   
	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void delete_dao_throws_exception() throws DaoException {
		   
	        Vehicle CorrectVehicle = new Vehicle(1, "Citroen", 4);

	       doThrow(new DaoException("Exception occured")).when(vehicleDao).delete(CorrectVehicle);
	       assertThrows(ServiceException.class, () -> vehicleService.delete(CorrectVehicle));
	   }
	   
	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void create_dao_throws_exception() throws DaoException {
		   
	        Vehicle CorrectVehicle = new Vehicle(1, "Citroen", 4);
	       
	       when(vehicleDao.create(CorrectVehicle)).thenThrow(DaoException.class);
	       
	       assertThrows(ServiceException.class, () -> vehicleService.create(CorrectVehicle));
	   }
	   
	   /**
	    * @throws DaoException
	    */
	   @Test
	   public void modifier_dao_throws_exception() throws DaoException {
		   
	        Vehicle CorrectVehicle = new Vehicle(1, "Citroen", 4);
	       
	       when(vehicleDao.modifier(CorrectVehicle)).thenThrow(DaoException.class);
	       
	       assertThrows(ServiceException.class, () -> vehicleService.modifier(CorrectVehicle));
	   }

}
