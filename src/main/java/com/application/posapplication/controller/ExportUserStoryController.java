package com.application.posapplication.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

@Controller
public class ExportUserStoryController {
    @RequestMapping("/exportuserstory")
    public String exportUserStory(@RequestParam(value="id")String id) {

        export(id);
        return "exportuserstory";
    }

    @RequestMapping("/exportuserstory/export")
    public void export(@RequestParam(value="id")String id) {

        String excelFilePath = "MeetingExport.xlsx";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-J6HCJ4JQ\\SQLEXPRESS:1433;"+
                "databaseName=DatabaseCapstone;user=sa;password=123456;");) {

            String tableName = "dMeetingDetailsModalTable";
            String columnName = "MeetingID";
            String sql = "SELECT * FROM " + tableName + " WHERE " +columnName+ "='" +id+ "';";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Meeting");

            writeHeaderLine(sheet);

            writeDataLines(result, workbook, sheet);

            try {
                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();
            }catch(IOException e){
                e.printStackTrace();
            }

            statement.close();

        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("ProcessID");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("MeetingID");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Noun");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Premodal");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Postmodal");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Output");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("TaskType");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("Priority");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            String processID = result.getString("ProcessID");
            String noun = result.getString("Noun");
            String meetingID = result.getString("MeetingID");
            String preModal = result.getString("PreModal");
            String postModal = result.getString("PostModal");
            String output = result.getString("Output");
            String tasktype = result.getString("TaskType");
            String priority = result.getString("Priority");


            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(processID);

            cell = row.createCell(columnCount++);
            cell.setCellValue(meetingID);

            cell = row.createCell(columnCount++);
            cell.setCellValue(noun);

            cell = row.createCell(columnCount++);
            cell.setCellValue(preModal);

//            CellStyle cellStyle = workbook.createCellStyle();
//            CreationHelper creationHelper = workbook.getCreationHelper();
//            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//            cell.setCellStyle(cellStyle);

            cell = row.createCell(columnCount++);
            cell.setCellValue(postModal);

            cell = row.createCell(columnCount++);
            cell.setCellValue(output);

            cell = row.createCell(columnCount);
            cell.setCellValue(tasktype);

            cell = row.createCell(columnCount);
            cell.setCellValue(priority);

        }
    }
}
