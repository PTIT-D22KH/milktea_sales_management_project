/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.CustomerDao;
import dao.EmployeeDao;
import dao.OrderDao;
import dao.ShipmentDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.Customer;
import models.Employee;
import models.Order;
import models.Shipment;
import utils.ShipmentStatus;
import views.popup.SelectCustomerPopupView;
import views.popup.SelectEmployeePopupView;
import views.popup.ShipmentPopupView;
/**
 *
 * @author buiva
 */
public class ShipmentPopupControler{
    private final ShipmentDao shipmentDao; 
    private final CustomerDao customerDao;
    private final EmployeeDao employeeDao;
    private final OrderDao orderDao;
    private JFrame previousView;
    
    public ShipmentPopupControler(){ 
        this.shipmentDao = new ShipmentDao();
        this.customerDao = new CustomerDao();
        this.employeeDao = new EmployeeDao();
        this.orderDao = new OrderDao();
    }    
    public void add(ShipmentPopupView view, Order order, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        try {
            Shipment shipment = shipmentDao.getById(order.getOrderId());
            if (shipment != null) {
                edit(view, order, sc, ec);
                return;
            }
            shipment = new Shipment();
            shipment.setShipCost(0);
            shipment.setOrder(order);
            shipment.setCustomer(customerDao.getAll().get(0));
            shipment.setEmployee(employeeDao.getAll().get(0));
            shipment.setStatus(ShipmentStatus.TOPAY);
            shipmentDao.save(shipment);
            edit(view, order, sc, ec);
        } catch (Exception e) {
            ec.onError(e);
            view.dispose();
        }
    }

    public void edit(ShipmentPopupView view, Order order, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            if (view != previousView) {
                // Do something if needed
            }
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                view.dispose();
            }
        });
        for (ShipmentStatus value : ShipmentStatus.values()) {
            view.getCboStatus().addItem(value.getName());
        }
        try {
            Shipment shipment = shipmentDao.getById(order.getOrderId());
            if (order.getCustomer().getCustomerId()!= 0) {
                Customer customer = customerDao.getById(order.getCustomer().getCustomerId());
                shipment.setCustomer(customer);
                view.getLbCustomerName().setText(customer.getName());
            } else {
                view.getLbCustomerName().setText("<Chưa chọn>");
            }
            view.getLbEmployeeName().setText(employeeDao.getById(shipment.getEmployee().getEmployeeId()).getName());
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
                        editShipment(view, shipment, order);
                        view.dispose();
                        view.showMessage("Tạo / sửa đơn ship thành công!");
                        sc.onSuccess();
                    } catch (SQLException e) {
                        ec.onError(e);
                    }
                }
            });
        } catch (Exception e) {
            ec.onError(e);
            view.dispose();
            }
    }
    
    private void editShipment(ShipmentPopupView view, Shipment shipment, Order order) throws SQLException {
        shipment.setStatus(ShipmentStatus.getByName(view.getCboStatus().getSelectedItem().toString()));
        shipment.setShipCost((int) view.getSpnShipCost().getValue());
        if (shipment.getStatus() == ShipmentStatus.COMPLETED || shipment.getStatus() == ShipmentStatus.CANCELLED) {
            shipment.setEndDate(new Timestamp(System.currentTimeMillis()));
        } else {
            shipment.setEndDate(null);
        }
        shipmentDao.update(shipment);
        
        //update customer from order
        order.setCustomer(shipment.getCustomer());
        orderDao.update(order);
    }

}
