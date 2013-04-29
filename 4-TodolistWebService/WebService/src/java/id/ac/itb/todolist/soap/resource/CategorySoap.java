/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.soap.resource;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import id.ac.itb.todolist.dao.CategoryDao;
/**
 *
 * @author Edward Samuel
 */
@WebService(serviceName = "CategorySoap")
public class CategorySoap {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "NewKategori")
    public int NewKategori(@WebParam(name = "nama") String nama,@WebParam(name = "pembuat") String pembuat) {
        CategoryDao categoryDao=new CategoryDao();
        return (categoryDao.NewKategori(nama, pembuat));
    }
    
    @WebMethod(operationName = "addCoordinator")
    public int addCoordinator(@WebParam(name = "id") int id,@WebParam(name = "pembuat") String pembuat) {
        CategoryDao categoryDao=new CategoryDao();
        return (categoryDao.addCoordinator(id, pembuat));
    }
    
    @WebMethod(operationName = "addNewestCoordinator")
    public int addNewestCoordinator(@WebParam(name = "pembuat") String pembuat) {
        CategoryDao categoryDao=new CategoryDao();
        return (categoryDao.addNewestCoordinator(pembuat));
    }
}
