<%@page import="com.se4f7.SWP.entities.CouponEntity"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" %>
<%@page import="org.apache.poi.xssf.usermodel.XSSFWorkbook" %>
<%@page import="org.apache.poi.ss.usermodel.*" %>
<%@ page import="com.se4f7.SWP.entities.CouponEntity" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset="utf-8">
        <title>Report Page</title>
    </head>
    <body>
        <%
            response.setHeader("Content-Disposition", "inline; filename=Receipts/Payments.xlsx");
            List<CouponEntity> list = (List<CouponEntity>) request.getAttribute("lists");

            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Coupon List");

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] columns = {"ID", "Name", "Description", "Type", "Created By", "Updated By",
                    "Created Date", "Updated Date", "Amount", "Due", "Status"};
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                }

                // Create data rows
                int rowNum = 1;
                for (CouponEntity toDo : list) {
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(toDo.getId());
                    row.createCell(1).setCellValue(toDo.getName());
                    row.createCell(2).setCellValue(toDo.getDescription());
                    row.createCell(3).setCellValue(toDo.getType());
                    row.createCell(4).setCellValue(toDo.getCreatedBy());
                    row.createCell(5).setCellValue(toDo.getUpdatedBy());
                    row.createCell(6).setCellValue(toDo.getCreatedDate());
                    row.createCell(7).setCellValue(toDo.getUpdatedDate());
                    row.createCell(8).setCellValue(toDo.getAmount());
                    row.createCell(9).setCellValue(toDo.getDue());
                    row.createCell(10).setCellValue(toDo.getStatus());
                }

                // Auto-size columns (optional)
                for (int i = 0; i < columns.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                // Write the workbook to the response output stream
                try (OutputStream outputStream = response.getOutputStream()) {
                    workbook.write(outputStream);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        %>
    </body>
</html>
