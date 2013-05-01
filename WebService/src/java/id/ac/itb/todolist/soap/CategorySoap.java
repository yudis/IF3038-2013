/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.soap;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import id.ac.itb.todolist.dao.CategoryDao;
/**
 *
 * @author kevin
 */
@WebService(serviceName = "CategorySoap")
public class CategorySoap {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "NewKategori")
    public int NewKategori(@WebParam(name = "nama") String txt1,@WebParam(name = "pembuat") String txt2) {
        CategoryDao categorydao= new CategoryDao();
        return (categorydao.NewKategori(txt1,txt2));
    }
    
    @WebMethod(operationName = "addCoordinator")
    public int addCoordinator(@WebParam(name = "id") int id,@WebParam(name = "pembuat") String txt) {
        CategoryDao categorydao= new CategoryDao();
        return (categorydao.addCoordinator(id,txt));
    }
    
    @WebMethod(operationName = "addNewestCoordinator")
    public int addNewestCoordinator(@WebParam(name = "pembuat") String txt) {
        CategoryDao categorydao= new CategoryDao();
        return (categorydao.addNewestCoordinator(txt));
    }
}
