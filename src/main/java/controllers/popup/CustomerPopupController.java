/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.popup;

import dao.CustomerDao;
import models.Customer;
import views.popup.CustomerPopupView;

/**
 *
 * @author P51
 */
public class CustomerPopupController extends PopupController<CustomerPopupView, Customer>{
    private final CustomerDao customerDao;
    
    public CustomerPopupController() {
        this.customerDao = new CustomerDao();
    }

    public CustomerPopupController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    @Override
    protected void addEntity(CustomerPopupView view) throws Exception {
        String name = view.getNameText().getText();
        String address = view.getAddressText().getText();
        String phoneNumber = view.getPhoneNumberText().getText();
        validateCustomerData(name, address, phoneNumber);
        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customerDao.save(customer);
    }
    
    @Override
    public void edit(CustomerPopupView view, Customer customer, SuccessCallback sc, ErrorCallback ec) {
        super.edit(view, customer, sc, ec);
        view.getLbTitle().setText("Sửa khách hàng - " + customer.getCustomerId());
        view.getNameText().setText(customer.getName());
        view.getPhoneNumberText().setText(customer.getPhoneNumber());
        view.getAddressText().setText(customer.getAddress());
    }
    
    @Override
    protected void editEntity(CustomerPopupView view, Customer customer) throws Exception {
        String name = view.getNameText().getText();
        String address = view.getAddressText().getText();
        String phoneNumber = view.getPhoneNumberText().getText();
        validateCustomerData(name, address, phoneNumber);
        customer.setAddress(address);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customerDao.update(customer);
    }
    private void validateCustomerData(String name, String address, String phoneNumber) throws Exception {
        if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ thông tin");
        }
    }


    
}
