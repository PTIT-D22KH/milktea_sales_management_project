package controllers.employee;

import controllers.AuthenticationController;
import controllers.popup.ErrorCallback;
import controllers.popup.SuccessCallback;
import dao.EmployeeDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import models.Employee;
import utils.SessionManager;
import views.employee.ChangePasswordView;

/**
 *
 * @param <T>
 */
public class ChangePasswordController extends AuthenticationController<ChangePasswordView> {
    private SuccessCallback sc;
    private ErrorCallback ec;
    private JFrame previousView;
    public ChangePasswordController(ChangePasswordView view) {
        super(view);
    }

    public ChangePasswordController(ChangePasswordView view, EmployeeDao employeeDao) {
        super(view, employeeDao);
    }

    @Override
    public void showView() {
        if (previousView != null && previousView.isDisplayable()) {
            previousView.requestFocus();
            return;
        }
        previousView = view;
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        addEvent();
    }

    public void show(ChangePasswordView view, SuccessCallback sc, ErrorCallback ec) {
        this.sc = sc;
        this.ec = ec;
        showView();
    }

    private void changePassword(ChangePasswordView view) throws Exception {
        String oldPass = new String(view.getOldPasswordField().getPassword());
        String newPass = new String(view.getPasswordField().getPassword());
        String confirmPass = new String(view.getConfirmNewPassField().getPassword());
        Employee currentLoginEmployee = SessionManager.getSession().getEmployee();
        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            throw new Exception("Vui lòng điền đầy đủ các trường");
        }
        if (!oldPass.equals(currentLoginEmployee.getPassword())) {
            throw new Exception("Mật khẩu cũ không chính xác!");
        }
        if (!newPass.equals(confirmPass)) {
            throw new Exception("Xác nhận mật khẩu sai!");
        }
        currentLoginEmployee.setPassword(newPass);
        employeeDao.update(currentLoginEmployee);
        view.dispose();
    }

    @Override
    protected void addEvent() {
        view.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });

        view.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePassword(view);
                    if (sc != null) {
                        sc.onSuccess();
                    }
                } catch (Exception exception) {
                    if (ec != null) {
                        ec.onError(exception);
                    }
                }
            }
        });
    }
}