package com.application.posapplication.controller;

import com.application.posapplication.model.HomepageAddProjectModel;
import com.application.posapplication.model.HomepagePageChangeModel;
import com.application.posapplication.model.UserResponse;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

@Controller
public class  HomepageController {
    @RequestMapping(value = "/homepage")
    public String homepage(HttpServletRequest req){

        HttpSession session =   req.getSession(false);
        String username = (String) session.getAttribute("username");

        if(session != null){
            return "homepage";
        }else{
            return "";
        }
    }

    @RequestMapping(value="/homepage/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserResponse homepageInput(HttpServletRequest req, @RequestBody
            HomepageAddProjectModel hapm
//                                                    , @RequestParam(value="id") String id
    ){
        ArrayList<HomepageAddProjectModel> hapmList = new ArrayList<>();

        ObjectMapper om = new ObjectMapper();
        String jstring = "";
        try{
            jstring = om.writeValueAsString(hapm.getCompanyName() + ", " + hapm.getProjectName() + ", " + hapm.getProjectDescription());
        }catch(JsonGenerationException e){
            e.printStackTrace();
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(Exception e){
            try{
                jstring = om.writeValueAsString(e.getMessage());
            }catch(JsonProcessingException ex){
                ex.printStackTrace();
            }
        }

        Connection conn = null;
        try{


            String dbURL = "jdbc:sqlserver://LAPTOP-J6HCJ4JQ\\SQLEXPRESS:1433;databaseName=DatabaseCapstone;user=sa;password=123456;";
            conn = DriverManager.getConnection(dbURL);

            String tableName = "bProjectTable";
            String columnName = "ProjectName";

            if(conn != null){
                homepageInsertFunction(req, hapm);
            }

            String searchId = "SELECT * FROM " +tableName+ " WHERE " +columnName+ " = '" +hapm.getProjectName()+ "'"; //The name of the project is new, of course it doesnt register

            PreparedStatement stmt = conn.prepareStatement(searchId);
            ResultSet result = stmt.executeQuery();
            hapmList.add(hapm);

            int idpage = 0;
            while(result.next()){
//                idpage = result.getString("ProjectID");
//                HomepagePageChangeModel hpcm = new HomepagePageChangeModel("a", idpage);
                idpage = result.getInt("ProjectID");
            }
//            hapmList.add(hapm);

            HomepageAddProjectModel hapm2 = new HomepageAddProjectModel(idpage, hapm.getProjectName(), hapm.getCompanyName(), hapm.getProjectDescription(), hapm.getProjectStatus());
            hapmList.add(hapm2);

        }catch(SQLException e){
            e.printStackTrace();
        }

        UserResponse ur = new UserResponse();
        ur.setData(hapmList);
        return ur;
    }

    private void homepageInsertFunction(HttpServletRequest req, @RequestBody HomepageAddProjectModel hapm)
            throws SQLException{
        String tableName = "bProjectTable"; //kemungkinan ganti
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-J6HCJ4JQ\\SQLEXPRESS:1433;databaseName=DatabaseCapstone;user=sa;password=123456;");
        Statement stmt = conn.createStatement();

        String insertValue = "INSERT INTO " + tableName + " VALUES ('" +hapm.getId()+ "', '" + hapm.getProjectName() + "', '" + hapm.getCompanyName() + "', '" + hapm.getProjectDescription() + "', '2021-06-10', 'Somewhere', 'Pending')";

        stmt.executeUpdate(insertValue);

        String columnProjectID = "ProjectName";
        String loadProjectID = "SELECT * FROM " + tableName + " WHERE " +columnProjectID+ " = '" +hapm.getProjectName()+ "'";

        ResultSet results = stmt.executeQuery(loadProjectID);

        String projectID = "";
        int temp = 0;
        while (results.next()){
            temp = results.getInt("ProjectID");
            projectID = Integer.toString(temp);
        }
//
//        return projectID;
    }

    @RequestMapping(value = "/getallproject", method = RequestMethod.POST)
    public @ResponseBody UserResponse getAllProject(@RequestParam(value="id")String userId){
        ArrayList<HomepageAddProjectModel> hapmList = new ArrayList<>();

        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-J6HCJ4JQ\\SQLEXPRESS:1433;" +
                    "databaseName=DatabaseCapstone;user=sa;password=123456;");
            String tableName = "bProjectTable";
            String columnName = "UserId";
//            String userId = "1";
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE " + columnName + " ='" + userId + "' ");
            ResultSet results = stmt.executeQuery();

            while(results.next()){
                int id = results.getInt("UserID");
                String projectName = results.getString("ProjectName");
                String companyName = results.getString("ProjectCompanyName");
                String projectDescription = results.getString("ProjectDesc");
                String projectStatus = results.getString("ProjectStatus");
                HomepageAddProjectModel hapmm = new HomepageAddProjectModel(id, projectName, companyName, projectDescription, projectStatus);

                hapmList.add(hapmm);
            }

            UserResponse userResponse = new UserResponse();
            userResponse.setStatus(true);
            userResponse.setMessage("Data is found");
            userResponse.setData(hapmList);
            return userResponse;

        }catch(SQLException e){
            e.printStackTrace();
            UserResponse uResponse = new UserResponse();
            uResponse.setStatus(false);
            uResponse.setMessage("Data not found");
            return uResponse;
        }
    }

//    @PostMapping("/posthomepage")
//    public @ResponseBody UserResponse postHomepage(@RequestBody HomepagePageChangeModel hpcm, @RequestBody HomepageAddProjectModel hapm) {
//        ArrayList<HomepagePageChangeModel> hpcmList = new ArrayList<>();
//        String homepageProjectName = "test";
//        String homepageProjectID = hpcm.getHomepageProjectID();
//
//        try{
//
//            String tableName = "bProjectTable";
//            String columnName = "ProjectName";
//            String projectName = hapm.getProjectName();
//            String sqlQuery = "SELECT * FROM " +tableName+ " WHERE " +columnName+ " = '" +projectName+ "'";
//
//            Connection conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-J6HCJ4JQ\\SQLEXPRESS:1433;databaseName=DatabaseCapstone;user=sa;password=123456;");
//            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
//            ResultSet results = stmt.executeQuery();
//
//            while(results.next()){
//                homepageProjectID = results.getString("ProjectID");
//            }
//
//
////        String homepageProjectName = hpcm.getHomepageProjectName();
//
//        HomepagePageChangeModel hpcmm = new HomepagePageChangeModel(homepageProjectName, homepageProjectID);
//        hpcmList.add(hpcmm);
//
//            UserResponse userResponse = new UserResponse();
//            userResponse.setStatus(true);
//            userResponse.setMessage("Data is found");
//            userResponse.setData(hpcmList);
//            return userResponse;
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//            UserResponse userResponse = new UserResponse();
//            userResponse.setStatus(false);
//            userResponse.setMessage("Data not found");
//            return userResponse;
//
//        }



//        catch (SQLException e){
//            e.printStackTrace();
//            UserResponse userResponse = new UserResponse();
//            userResponse.setStatus(false);
//            userResponse.setMessage("SQL Error");
//            return userResponse;
//        }
//    }
}
