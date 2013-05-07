/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import DBOperation.CategoryOp;
import DBOperation.CaurelationOp;
import DBOperation.UcrelationOp;
import DBOperation.UserOp;
import JSON.JSONObject;
import JSON.JSONTokener;
import Model.Category;
import Model.Caurelation;
import Model.Ucrelation;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author M500-S430
 */
@WebService(serviceName = "CategorySoap")
public class CategorySoap {
    CategoryOp categoryOp = new CategoryOp();
    CaurelationOp caurelationOp = new CaurelationOp();
    UcrelationOp ucrelataionOp = new UcrelationOp();
    UserOp userOp = new UserOp();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        JSONObject jcat = new JSONObject(new JSONTokener(txt));
        String categName = jcat.getString("name");
        String[] authUsers = jcat.getString("authUsers").split(",");
        String username = jcat.getString("username");
        
        categoryOp.InsertNewCategory(new Category("", categName, username));
        String newCategId = categoryOp.FetchIdByName(categName);
        caurelationOp.InsertCaurelation(new Caurelation("",newCategId,username));
        ucrelataionOp.InsertUcrelation(new Ucrelation("",username,newCategId));
        
        if (authUsers.length != 0)
        {
            for (String authUser : authUsers){
                if (userOp.ListAllUsernames().contains(authUser)){
                    ucrelataionOp.InsertUcrelation(new Ucrelation("",authUser.trim(), newCategId));
                    caurelationOp.InsertCaurelation(new Caurelation("", newCategId, authUser));
                }
            }
        }
        return "200";
    }
}
