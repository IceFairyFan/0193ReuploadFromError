package com.application.posapplication.controller;

import com.application.posapplication.model.LoginModel;
import com.application.posapplication.model.RegisterIntModel;
import com.application.posapplication.model.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
    //        model.addAttribute("title", "a");
        return "login";
    }

    @RequestMapping("/login/submit")
    public @ResponseBody UserResponse login(HttpServletRequest req, @RequestBody LoginModel loginModel, Model model)throws SQLException, JsonProcessingException {

        ArrayList<RegisterIntModel> rimList = new ArrayList<>();

//        String id = "";
        boolean isLogin = false;
        try{

//            Connection conn = DriverManager.getConnection("jdbc:sqlserver://databasecapstone.database.windows.net:1433;database=DatabaseCapstone;user=capstone@databasecapstone;password=P@ssw0rd;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
//

            Connection conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-J6HCJ4JQ\\SQLEXPRESS:1433;databaseName=DatabaseCapstone;user=sa;password=123456;");
            String tableName = "aUserTable";
             String email = "email";
            String password = "password";
            String inputEmail = loginModel.getEmail();
            String inputPassword = loginModel.getPassword();

            String stringSelect = "SELECT * FROM " + tableName + " WHERE " +email+ " = '" +inputEmail+ "' AND " +password+ " = '" +inputPassword+ "';";
            PreparedStatement statement = conn.prepareStatement(stringSelect);
            ResultSet results = statement.executeQuery();

            String columnName = "email";
            String stringExtractName = "SELECT * FROM " +tableName+ " WHERE " +columnName+ " = '"+inputEmail+"';";

            PreparedStatement statement2 = conn.prepareStatement(stringExtractName);
            ResultSet resultTwo = statement2.executeQuery();
            String name = "";
            int ids = 0;
            while (resultTwo.next()){
                name = resultTwo.getString("Name");
                ids = resultTwo.getInt("UserId");
                RegisterIntModel rim = new RegisterIntModel(ids);
                rimList.add(rim);
            }
//            ObjectMapper om = new ObjectMapper();
//            ModelAndView result = new ModelAndView();
//
//            result.addObject("rim", om.writeValueAsString(rim));

//            model.addAttribute("Number", ids);
//
//            JSONObject item = new JSONObject();
//            item.put("number", ids);


            while (results.next()){
                isLogin = true;
                HttpSession session = req.getSession();
                session.setAttribute("username", name);
                session.setMaxInactiveInterval(900);
            }

            UserResponse userResponse = new UserResponse();
            userResponse.setStatus(isLogin);
            userResponse.setData(rimList);

            if(isLogin){
                userResponse.setMessage("Data is found");
            }else{
                userResponse.setMessage("Data not found, input :" + inputEmail +" "+ inputPassword);
            }

            return userResponse;

        }catch (SQLException e){
            e.printStackTrace();
            UserResponse userResponse = new UserResponse();
            userResponse.setStatus(false);
            userResponse.setMessage("Error SQL");
            return userResponse;
        }
    }
}
