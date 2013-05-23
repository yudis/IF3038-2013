/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.mysql.jdbc.log.Log;
import server.JSON.JSONArray;
import server.JSON.JSONObject;
import server.JSON.JSONValue;

/**
 *
 * @author M Reza MP
 */
public class Coba {
    /*
     public static void parseProfilesJson(String the_json){
     try {
     JSONObject myjson = new JSONObject(the_json);
     JSONArray data = myjson.names();
     JSONArray namatugasArray = myjson.toJSONArray(data);
     JSONObject catObject = myjson.getJSONObject("cat");
     JSONArray dataCat = catObject.names();
     JSONArray catArray = catObject.toJSONArray(dataCat);
            
           
     //JSONObject catNameObject = catObject.getJSONObject("nama_kategori");
     //JSONArray catArray = catNameObject.getJSONArray("");
     System.out.println(myjson.toString());
            
     JSONArray nameArray = myjson.names();
     JSONArray valArray = myjson.toJSONArray(nameArray);
     String nameArray2 = (String)nameArray.get(1);
            
     System.out.println(nameArray2);
     for(int i=1;i<valArray.length();i++)
     {
     String p = nameArray.getString(i) + "," + valArray.getString(i);
     System.out.println("result: " + p);
     //Log.i("p",p);
     }       
            
     } catch (JSONException e) {
     e.printStackTrace();
     }
     }
     */

    public static void main(String args[]) {
//{{"id_tugas":"1","id_kategori":"1","nama_tugas":"progin dewa","deadline":"2013-03-05 18:00:00","status":"0","tag":"","attachment":"","username":"asdasdasd","status_tugas":"0","cat":{{"nama_kategori":"progin"}}},{"id_tugas":"2","id_kategori":"1","nama_tugas":"tubes1","deadline":"2013-03-23 00:00:00","status":"1","tag":"html","attachment":"-","username":"jo","status_tugas":"0","cat":{{"nama_kategori":"progin"}}},{"id_tugas":"5","id_kategori":"2","nama_tugas":"tubes1","deadline":"2013-03-23 00:00:00","status":"0","tag":"gunbound","attachment":"-","username":"asdasdasd","status_tugas":"0","cat":{{"nama_kategori":"sister"}}}}

//          String json = "{\"id_tugas\":\"1\",\"id_kategori\":\"1\"},{\"id_tugas\":\"1\",\"id_kategori\":\"1\"}";
        //String json = "[{\"id_tugas\":\"1\",\"id_kategori\":\"1\",\"nama_tugas\":\"progin dewa\",\"deadline\":\"2013-03-05 18:00:00\",\"status\":\"0\",\"tag\":\"\",\"attachment\":\"\",\"username\":\"asdasdasd\",\"status_tugas\":\"0\",\"cat\":[{\"nama_kategori\":\"progin\"}]},{\"id_tugas\":\"2\",\"id_kategori\":\"1\",\"nama_tugas\":\"tubes1\",\"deadline\":\"2013-03-23 00:00:00\",\"status\":\"1\",\"tag\":\"html\",\"attachment\":\"-\",\"username\":\"jo\",\"status_tugas\":\"0\",\"cat\":[{\"nama_kategori\":\"progin\"}]},{\"id_tugas\":\"5\",\"id_kategori\":\"2\",\"nama_tugas\":\"tubes1\",\"deadline\":\"2013-03-23 00:00:00\",\"status\":\"0\",\"tag\":\"gunbound\",\"attachment\":\"-\",\"username\":\"asdasdasd\",\"status_tugas\":\"0\",\"cat\":[{\"nama_kategori\":\"sister\"}]}]";

        System.out.println("=======decode=======");

        String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
        Object obj = JSONValue.parse(s);
        JSONArray array = (JSONArray) obj;
        System.out.println("======the 2nd element of array======");
        System.out.println(array.get(1));
        System.out.println();

        JSONObject obj2 = (JSONObject) array.get(1);
        System.out.println("======field \"1\"==========");
        System.out.println(obj2.get("1"));


        s = "{}";
        obj = JSONValue.parse(s);
        System.out.println(obj);

        s = "[5,]";
        obj = JSONValue.parse(s);
        System.out.println(obj);

        s = "[5,,2]";
        obj = JSONValue.parse(s);
        System.out.println(obj);
        //String json2 = json.replace("},{", "}],[{");
        //String json3 = json2.replace("[", "").replace("]", "");

        //System.out.println(json2);
        //parseProfilesJson(json3);
        /*
         String[] row = json.replace("[", "").replace("]", "").split("}");
         String[] col = row[0].split(",");
         String[][] result = new String[row.length][col.length];
        
         String regex = "\\\\{\\\"[a-zA-Z0-9]+\\\":\\\\[[a-zA-Z0-9]*";
         System.out.println(json.split(regex).length);
        
         for (int i = 0; i < row.length; ++i)
         {
         String[] col2 = row[i].split(",");
         System.out.println("res: ");
         for (int j = 0; j < col.length; ++j)
         {
         System.out.println("");
         String s = (col2[j].split(":").length == 2) ? col2[j].split(":")[1] : ((col2[j].split(":").length < 2) ? "-" : col2[j].replace("deadline\":\"", ""));
         result[i][j] = s;
         System.out.print(result[i][j] + " ");
         }
         System.out.println("");
         }
         * */
        System.out.println();
    }
}
