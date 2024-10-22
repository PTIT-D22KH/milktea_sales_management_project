/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.employee;

import dao.statistical.StatisticalDao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import views.employee.WorkDayInfoView;

/**
 *
 * @author P51
 */
public class WorkDayInfoController {
    private WorkDayInfoView previousView = null;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###,### VND");
    private final StatisticalDao statisticalDao = new StatisticalDao();
    
    public void show(WorkDayInfoView view, Calendar calendar, int employeeId) throws Exception {
        if (previousView != null) {
            previousView.dispose();
        }
        previousView = view;
        Calendar startCalendar = (Calendar) calendar.clone();
        Calendar endCalendar = (Calendar) calendar.clone();
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.HOUR_OF_DAY, 24);
        endCalendar.set(Calendar.MINUTE, 0);
        Timestamp startTime = new Timestamp(startCalendar.getTimeInMillis());
        Timestamp endTime = new Timestamp(endCalendar.getTimeInMillis());
        int totalOrder = statisticalDao.getTotalOrder(startTime, endTime, employeeId);
        int workingMinus = statisticalDao.getTotalWorkingMinutes(startTime, endTime, employeeId);
        int totalAmount = statisticalDao.getTotalIncome(startTime, endTime, employeeId);
        view.getDateLabel().setText(dateFormat.format(calendar.getTime()));
        view.getTotalOrderLabel().setText("" + totalOrder);
        view.getBonusLabel().setText(decimalFormat.format(totalOrder * 2000));
        view.getTotalTimeLabel().setText(minutesToHours(workingMinus));
        view.getTotalAmountLabel().setText(decimalFormat.format(totalAmount));
        view.pack();
        view.setVisible(true);
        view.getExitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                view.dispose();
            }
        });
        
        
        
    }
    private String minutesToHours(int m) {
        int hours = m / 60, minutes = m % 60;
        return String.format("%02d:%02d:00", hours, minutes);
    }
}
