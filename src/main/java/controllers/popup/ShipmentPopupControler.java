/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.CustomerDao;
<<<<<<< HEAD
import dao.EmployeeDao;
import dao.ShipmentDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JFrame;
import models.Customer;
import models.Employee;
=======
import dao.ShipmentDao;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JFrame;
>>>>>>> 65a0478 (done)
import models.Shipment;
import utils.ShipmentStatus;
import views.popup.SelectCustomerPopupView;
import views.popup.SelectEmployeePopupView;
import views.popup.ShipmentPopupView;
/**
 *
 * @author buiva
 */
<<<<<<< HEAD
public class ShipmentPopupControler{
    private ShipmentDao shipmentDao;
    private CustomerDao customerDao;
    private EmployeeDao employeeDao;
    private JFrame previousView;
    
    public ShipmentPopupControler() {
        shipmentDao = new ShipmentDao();
        customerDao = new CustomerDao();
        employeeDao = new EmployeeDao();
    }

    public ShipmentPopupControler(ShipmentDao shipmentDao, CustomerDao customerDao, EmployeeDao employeeDao) {
        this.shipmentDao = shipmentDao;
        this.customerDao = customerDao;
        this.employeeDao = employeeDao;
    }
    
    public void add(ShipmentPopupView view, int orderId, SuccessCallback sc, ErrorCallback ec) {
=======
public class ShipmentPopupControler {
    ShipmentDao shipmentDao = new ShipmentDao();
    CustomerDao customerDao = new CustomerDao();
    JFrame previousView;

    public void add(ShipmentPopupView view, int idOrder, SuccessCallback sc, ErrorCallback ec) {
>>>>>>> 65a0478 (done)
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
<<<<<<< HEAD
        try {
            Shipment shipment = shipmentDao.getById(orderId);
            if (shipment != null) {
                edit(view, orderId, sc, ec);
=======
//        previousView = view;
        try {
            Shipment shipment = shipmentDao.getById(idOrder);
            if (shipment != null) {
                edit(view, idOrder, sc, ec);
>>>>>>> 65a0478 (done)
                return;
            }
            shipment = new Shipment();
            shipment.setShipCost(0);
<<<<<<< HEAD
            shipment.setOrderId(orderId);
            shipment.setCustomer(customerDao.getAll().get(0));
            shipment.setEmployee(employeeDao.getAll().get(0));
            shipment.setStatus(ShipmentStatus.TOPAY);
            shipmentDao.save(shipment);
            edit(view, orderId, sc, ec);
=======
            shipment.setOrderId(idOrder);
            shipment.setCustomer(customerDao.getAll().get(0));
            shipment.setStatus(ShipmentStatus.TOPAY);
            //shipment.setShipperName("Nguyen Van B");
            //shipment.setShipperPhoneNumber("0981854673");
            shipmentDao.save(shipment);
            edit(view, idOrder, sc, ec);
>>>>>>> 65a0478 (done)
        } catch (Exception e) {
            ec.onError(e);
            view.dispose();
        }
<<<<<<< HEAD
    }

    public void edit(ShipmentPopupView view, int orderId, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            if (view != previousView) {
                // Do something if needed
=======

    }

    public void edit(ShipmentPopupView view, int idOrder, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            if (view != previousView) {

>>>>>>> 65a0478 (done)
            }
        }
        previousView = view;
        view.setVisible(true);
<<<<<<< HEAD
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                view.dispose();
            }
        });
=======
        view.getBtnCancel().addActionListener(evt -> view.dispose());
>>>>>>> 65a0478 (done)
        for (ShipmentStatus value : ShipmentStatus.values()) {
            view.getCboStatus().addItem(value.getName());
        }
        try {
<<<<<<< HEAD
            Shipment shipment = shipmentDao.getById(orderId);
=======
            Shipment shipment = shipmentDao.getById(idOrder);
>>>>>>> 65a0478 (done)
            if (shipment.getCustomer() != null) {
                view.getLbCustomerName().setText(shipment.getCustomer().getName());
            } else {
                view.getLbCustomerName().setText("<Chưa chọn>");
            }
<<<<<<< HEAD
            view.getBtnSelectEmployee().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    SelectEmployeePopupController selectEmployeePopupController = new SelectEmployeePopupController(new EmployeeDao());
                    selectEmployeePopupController.select(new SelectEmployeePopupView(), new SelectEmployeePopupController.Callback<Employee>() {
                        @Override
                        public void run(Employee employee) {
                            shipment.setEmployee(employee);
                            view.getLbEmployeeName().setText(employee.getName());
                        }
                    });
                }
            });

            view.getCboStatus().setSelectedItem(shipment.getStatus().getName());
            view.getSpnShipCost().setValue(shipment.getShipCost());

            view.getBtnSelectCustomer().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    SelectCustomerPopupController selectCustomerPopupController = new SelectCustomerPopupController(new CustomerDao());
                    selectCustomerPopupController.select(new SelectCustomerPopupView(), new SelectCustomerPopupController.Callback<Customer>() {
                        @Override
                        public void run(Customer customer) {
                            shipment.setCustomer(customer);
                            view.getLbCustomerName().setText(customer.getName());
                        }
                    });
                }
            });

            view.getBtnOK().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        editShipment(view, shipment);
                        view.dispose();
                        view.showMessage("Tạo / sửa đơn ship thành công!");
                        sc.onSuccess();
                    } catch (SQLException e) {
                        ec.onError(e);
                    }
                    
                }
=======
            //view.getTxtShipperName().setText(shipment.getShipperName());
            view.getBtnSelectEmployee().addActionListener(evt -> {
                SelectEmployeePopupController selectEmployeePopupController = new SelectEmployeePopupController();
                selectEmployeePopupController.select(new SelectEmployeePopupView(), (employee) -> {
                    shipment.setEmployee(employee);
                    view.getLbEmployeeName().setText(employee.getName());
                });
            });
            
            view.getCboStatus().setSelectedItem(shipment.getStatus().getName());
            view.getSpnShipCost().setValue(shipment.getShipCost());

            view.getBtnSelectCustomer().addActionListener(evt -> {
                SelectCustomerPopupController selectCustomerPopupController = new SelectCustomerPopupController();
                selectCustomerPopupController.select(new SelectCustomerPopupView(), (customer) -> {
                    shipment.setCustomer(customer);
                    view.getLbCustomerName().setText(customer.getName());
                });
            });
            
//            view.getBtnOK().addActionListener(evt -> {
//                try {
//                    //editShipment(view, shipment);
//                    view.dispose();
//                    view.showMessage("Tạo / sửa đơn ship thành công!");
//                    sc.onSuccess();
//                } catch (SQLException e) {
//                    ec.onError(e);
//                }
//            });

            view.getBtnOK().addActionListener(evt -> {
                //editShipment(view, shipment);
                view.dispose();
                view.showMessage("Tạo / sửa đơn ship thành công!");
                sc.onSuccess();
>>>>>>> 65a0478 (done)
            });
        } catch (Exception e) {
            ec.onError(e);
            view.dispose();
        }
<<<<<<< HEAD
    }
    
    public void editShipment(ShipmentPopupView view, Shipment shipment) throws SQLException {
=======

    }
/*
    public void editShipment(ShipmentPopupView view, Shipment shipment) throws SQLException {
        
        // chua biet sua ten kieu gi        
        //shipment.setShipperName(view.getTxtEmployeeName().getText());
        
        
>>>>>>> 65a0478 (done)
        shipment.setStatus(ShipmentStatus.getByName(view.getCboStatus().getSelectedItem().toString()));
        shipment.setShipCost((int) view.getSpnShipCost().getValue());
        if (shipment.getStatus() == ShipmentStatus.COMPLETED || shipment.getStatus() == ShipmentStatus.CANCELLED) {
            shipment.setEndDate(new Timestamp(System.currentTimeMillis()));
        } else {
            shipment.setEndDate(null);
        }
        shipmentDao.update(shipment);
    }
<<<<<<< HEAD

=======
*/
>>>>>>> 65a0478 (done)
}
