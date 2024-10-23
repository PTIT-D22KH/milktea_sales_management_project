/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import utils.ConfigLoader;
import dao.OrderDao;
import dao.OrderItemDao;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.FoodItem;
import models.Order;
import models.OrderItem;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
/**
 *
 * @author P51
 */
public class OrderPrintController {

    XWPFDocument document;
    File orderFile;
    String fileName;
    String orderFilePath;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public OrderPrintController() {
        document = new XWPFDocument();
        loadConfig();
        orderFile = new File(orderFilePath + "order.docx");
    }

    private void loadConfig() {
        ConfigLoader cfgLoader = ConfigLoader.getInstance();
        try {
            orderFilePath = cfgLoader.getProperty("orderFilePath");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print(int id) throws Exception {
        orderFile = new File(orderFilePath + "order-" + id + ".docx");
        OrderDao orderDao = new OrderDao();
        OrderItemDao orderItemDao = new OrderItemDao();
        Order order = orderDao.getById(id);
        ArrayList<OrderItem> orderItems = orderItemDao.getByOrderId(id);
        print(order, orderItems);
    }

    public File getOrderFile() {
        return orderFile;
    }

    public void setOrderFile(File orderFile) {
        this.orderFile = orderFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void createHeader() {
        XWPFParagraph paragraph;
        XWPFRun run;
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run = paragraph.createRun();
        run.setText("Hóa Đơn");
        run.setBold(true);
        run.setColor("FF0000");
        run.setFontSize(30);
    }

    public void createHeaderInfo(Order order) {
        XWPFParagraph paragraph;
        XWPFRun run;
        int fontSize = 12;
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setText("Tên nhân viên: ");
        run.setFontSize(fontSize);
        run = paragraph.createRun();
        run.setBold(true);
        run.setText(order.getEmployee().getName());
        run.setFontSize(fontSize);
        run.setColor("FF0000");
        run.addBreak();

        run = paragraph.createRun();
        run.setText("Thời gian: ");
        run.setFontSize(fontSize);
        run = paragraph.createRun();
        run.setBold(true);
        run.setText(dateFormat.format(new Date(order.getOrderDate().getTime())));
        run.setFontSize(fontSize);
        run.setColor("FF0000");
        run.addBreak();
        
        // Add customer name
        run = paragraph.createRun();
        run.setText("Tên khách hàng: ");
        run.setFontSize(fontSize);
        run = paragraph.createRun();
        run.setBold(true);
        run.setText(order.getCustomer().getName());
        run.setFontSize(fontSize);
        run.setColor("FF0000");
        run.addBreak();

        // Add customer phone number
        run = paragraph.createRun();
        run.setText("Số điện thoại khách hàng: ");
        run.setFontSize(fontSize);
        run = paragraph.createRun();
        run.setBold(true);
        run.setText(order.getCustomer().getPhoneNumber());
        run.setFontSize(fontSize);
        run.setColor("FF0000");
        run.addBreak();
    }

    public void createOrderInfo(ArrayList<OrderItem> orderItems) {
        XWPFParagraph paragraph;
        XWPFRun run;
        int fontSize = 14;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Create a table for order items
        XWPFTable table = document.createTable();
        table.setWidth("100%");

        // Create header row
        XWPFTableRow headerRow = table.getRow(0);
        createTableCell(headerRow.getCell(0), "Tên món", fontSize, true);
        createTableCell(headerRow.addNewTableCell(), "Topping", fontSize, true);
        createTableCell(headerRow.addNewTableCell(), "Số lượng", fontSize, true);
        createTableCell(headerRow.addNewTableCell(), "Đơn vị", fontSize, true);
        createTableCell(headerRow.addNewTableCell(), "Thành tiền (VNĐ)", fontSize, true);

        // Add order items to the table
        for (OrderItem orderItem : orderItems) {
            FoodItem food = orderItem.getFoodItem();
            FoodItem topping = orderItem.getToppingItem();
            XWPFTableRow row = table.createRow();
            createTableCell(row.getCell(0), food.getName(), fontSize, false);
            createTableCell(row.getCell(1), topping.getName(), fontSize, false);
            createTableCell(row.getCell(2), String.valueOf(orderItem.getQuantity()), fontSize, false);
            createTableCell(row.getCell(3), food.getUnitName(), fontSize, false);
            createTableCell(row.getCell(4), decimalFormat.format(orderItem.getAmount()), fontSize, false);
        }
    }

    private void createTableCell(XWPFTableCell cell, String text, int fontSize, boolean isBold) {
        XWPFRun run = cell.getParagraphs().get(0).createRun();
        run.setText(text);
        run.setFontSize(fontSize);
        run.setBold(isBold);
    }

    public void createPaidInfo(Order order) {
        int fontSize = 12;
        XWPFTable table = document.createTable();
        table.setWidth("100%");

        // Add rows and cells to the table
        addTableRow(table, "Tổng tiền:", formatter.format(order.getTotalAmount()), fontSize);
        addTableRow(table, "Giảm giá:", order.getDiscount() + "%", fontSize);
        addTableRow(table, "Phải thanh toán:", formatter.format(order.getFinalAmount()), fontSize);
        addTableRow(table, "Đã thanh toán:", formatter.format(order.getPaidAmount()), fontSize);
        addTableRow(table, "Tiền thừa:", formatter.format(order.getPaidAmount()  - order.getFinalAmount()), fontSize);
        addTableRow(table, "Ngày thanh toán:", dateFormat.format(new Date(order.getPayDate().getTime())), fontSize);
    }
    
    
    private void addTableRow(XWPFTable table, String label, String value, int fontSize) {
        XWPFTableRow row = table.createRow();
        XWPFTableCell cell1 = row.getCell(0);
        XWPFRun run1 = cell1.getParagraphs().get(0).createRun();
        run1.setText(label);
        run1.setFontSize(fontSize);

        XWPFTableCell cell2 = row.addNewTableCell();
        XWPFRun run2 = cell2.getParagraphs().get(0).createRun();
        run2.setText(value);
        run2.setFontSize(fontSize);
        run2.setColor("FF0000");
    }

    public void createFooter() {
        XWPFParagraph paragraph;
        XWPFRun run;
        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.RIGHT);
        run = paragraph.createRun();
        run.setText("Cảm ơn quý khách! Hẹn gặp lại!");
        run.setItalic(true);
        run.setFontSize(10);
    }

    public void print(Order order, ArrayList<OrderItem> orderItems) throws Exception {
        // Initialize a new document for each print operation
        document = new XWPFDocument();

        FileOutputStream out = new FileOutputStream(orderFile, false);
        createHeader();
        createHeaderInfo(order);
        createOrderInfo(orderItems);
        createPaidInfo(order);
        createFooter();
        document.write(out);
        out.close();
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(orderFile);
        }
    }
}
