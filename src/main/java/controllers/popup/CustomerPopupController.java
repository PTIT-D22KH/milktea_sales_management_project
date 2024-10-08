/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.CustomerDao;
import javax.swing.JFrame;
import models.Customer;
import views.popup.CustomerPopupView;

/**
 *
 * @author P51
 */
public class CustomerPopupController {
    CustomerDao customerDao = new CustomerDao();
    JFrame previousView;
    
    
    
    public void add(CustomerPopupView view, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(event -> view.dispose());
        view.getBtnOK().addActionListener(event -> {
            try {
                addCustomer(view);
                view.dispose();
                view.showMessage("Thêm khách hàng thành công");
                sc.onSuccess();
                
            } catch (Exception exception) {
                ec.onError(exception);
//                System.out.println("That bai");
            }
        });
    }
    
    public boolean addCustomer(CustomerPopupView view) throws Exception {
        String name = view.getNameText().getText();
        String address = view.getAddressText().getText();
        String phoneNumber = view.getPhoneNumberText().getText();
        if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customerDao.save(customer);
        return true;
    }
    
    public void edit(CustomerPopupView view, Customer customer, SuccessCallback sc, ErrorCallback ec) {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.getBtnCancel().addActionListener(event -> view.dispose());
        view.getLbTitle().setText("Sửa khách hàng - " + customer.getCustomerId());
        view.getNameText().setText(customer.getName());
        view.getPhoneNumberText().setText(customer.getPhoneNumber());
        view.getAddressText().setText(customer.getAddress());
        
        view.getBtnOK().setText("Cập nhật");
        view.getBtnOK().addActionListener(event -> {
            try {
                editCustomer(view, customer);
                view.dispose();
                view.showMessage("Sửa thông tin khách hàng thành công");
                sc.onSuccess();
            } catch (Exception ex){
                ec.onError(ex);
            }
                    
        });
        
    }
    
    public boolean editCustomer(CustomerPopupView view, Customer customer) throws Exception {
        String name = view.getNameText().getText();
        String address = view.getAddressText().getText();
        String phoneNumber = view.getPhoneNumberText().getText();
        if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
        customer.setAddress(address);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customerDao.update(customer);
        return true;
    }
    
    
}
