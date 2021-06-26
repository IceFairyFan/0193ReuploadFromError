package com.application.posapplication.controller;

import com.application.posapplication.model.UserResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Controller
public class RegisterController {
    @RequestMapping("/register")
    public String registerController(HttpServletRequest req){
        return "register";
    }

    @RequestMapping(value = "/register/post", method = RequestMethod.POST)
    public @ResponseBody UserResponse registerSubmit(HttpServletRequest req, @RequestParam(value="email")String email, @RequestParam(value="password")String password, @RequestParam(value="name")String name){

        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-J6HCJ4JQ\\SQLEXPRESS:1433;"+
                    "databaseName=DatabaseCapstone;user=sa;password=123456;");
            String tableName = "aUserTable";
            Statement stmt = conn.createStatement();

            String sqlQuery = "INSERT INTO " +tableName+ " VALUES ('" +email+ "', '"
                    +password+ "', '"+name+"', 'AuthProvider')";

            stmt.execute(sqlQuery);

            UserResponse userResponse = new UserResponse();
            userResponse.setStatus(true);

            return userResponse;

        }catch(SQLException e){
            e.printStackTrace();
            UserResponse userResponse = new UserResponse();
            userResponse.setStatus(false);
            userResponse.setMessage("Check your information again.");

            return userResponse;
        }
    }
}
