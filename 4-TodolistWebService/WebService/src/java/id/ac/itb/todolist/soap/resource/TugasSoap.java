/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.soap.resource;

import id.ac.itb.todolist.dao.TugasDao;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Edward Samuel
 */
@WebService(serviceName = "TugasSoap")
public class TugasSoap {

    @WebMethod(operationName = "addAssignee")
    public int addAssignee(@WebParam(name = "idTugas") int idTugas, @WebParam(name = "username") String username) {
        TugasDao tugasDao = new TugasDao();
        return tugasDao.addAssignee(idTugas, username);
    }

    @WebMethod(operationName = "addAttachment")
    public int addAttachment(@WebParam(name = "idTugas") int idTugas, @WebParam(name = "name") String name, @WebParam(name = "filename") String filename, @WebParam(name = "type") String type) {
        TugasDao tugasDao = new TugasDao();
        return tugasDao.addAttachment(idTugas, name, filename, type);
    }

    @WebMethod(operationName = "addTag")
    public int addTag(@WebParam(name = "idTugas") int idTugas, @WebParam(name = "tag") String tag) {
        TugasDao tugasDao = new TugasDao();
        return tugasDao.addTag(idTugas, tag);
    }
    
    @WebMethod(operationName = "addTugas")
    public int addTugas(@WebParam(name = "nama") String nama, @WebParam(name = "deadline") String deadline, @WebParam(name = "pemilik") String pemilik, @WebParam(name = "idKategori") int idKategori) {
        TugasDao tugasDao = new TugasDao();
        return tugasDao.addTugas(nama, java.sql.Date.valueOf(deadline), pemilik, idKategori);
    }
}
