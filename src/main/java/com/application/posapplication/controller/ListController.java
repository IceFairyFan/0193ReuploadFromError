package com.application.posapplication.controller;

import com.application.posapplication.model.ListUserStoriesModel;
import com.application.posapplication.model.UserResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

@Controller
public class ListController {
    @RequestMapping("/listuserstories")
    public String list(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        String username = (String) session.getAttribute("username");

        if(session != null){
            return "listuserstories";
        }else{
            return "";
        }

    }

    @RequestMapping("/getalluserstories")
    public @ResponseBody UserResponse getAllUserStories(@RequestParam(value="id")String id){
        ArrayList<ListUserStoriesModel> lusmList = new ArrayList<>();

        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-J6HCJ4JQ\\SQLEXPRESS:1433;databaseName=DatabaseCapstone;user=sa;password=123456;");
            String tableName = "dMeetingDetailsModalTable"; //"dMeetingDetailsTable";
            String columnName = "MeetingID";
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE " +columnName+ " = " + id);
            ResultSet results = stmt.executeQuery();

            while(results.next()){
                String output = results.getString("Output");
                ListUserStoriesModel lusmm = new ListUserStoriesModel(output);

                lusmList.add(lusmm);
            }

            UserResponse userResponse = new UserResponse();
            userResponse.setStatus(true);
            userResponse.setMessage("Data is found");
            userResponse.setData(lusmList);
            return userResponse;

        }catch(SQLException e){
            e.printStackTrace();
            UserResponse uResponse = new UserResponse();
            uResponse.setStatus(false);
            uResponse.setMessage("Data not found");
            return uResponse;
        }
    }
}
