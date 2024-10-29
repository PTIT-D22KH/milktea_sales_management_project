package controllers.employee;


import java.awt.Color;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.JPanel;

import models.statistical.*;
import dao.statistical.StatisticalDao;
import utils.SessionManager;

import views.employee.CalendarView;
import views.employee.DayView;


public class CalendarController {
    
    private CalendarView view;
//    private int id = SessionManager.getSession().getEmployeeId();
    private int id;
    private final StatisticalDao statisticalDao;
    private WorkingDay workingDay;
    private DayController dayController;
    private final DecimalFormat decimalFormat;
    
    public CalendarController() {
        this.decimalFormat = new DecimalFormat("###,###,### VND");
        this.statisticalDao = new StatisticalDao();
        this.id = SessionManager.getSession().getEmployee().getEmployeeId();
        this.dayController = new DayController();
    }
    
    public CalendarController(CalendarView view) {
        this.decimalFormat = new DecimalFormat("###,###,### VND");
        this.statisticalDao = new StatisticalDao();
        this.id = SessionManager.getSession().getEmployee().getEmployeeId();
        this.dayController = new DayController();
        this.view = view;
        this.view.getCmbMonth().setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        this.view.getTxtYear().setText(String.valueOf(LocalDate.now().getYear()));
        renderCalendar(view, Calendar.getInstance());
        renderStatistical(view, Calendar.getInstance());
        addEvent(view);
    }
    
    public void show(JPanel panelRoot, CalendarView view) {
        panelRoot.add(view);
        Calendar cal = Calendar.getInstance();
        view.getCmbMonth().setSelectedIndex(cal.get(Calendar.MONTH));
        view.getTxtYear().setText(cal.get(Calendar.YEAR) + "");
        renderCalendar(view, Calendar.getInstance());
        renderStatistical(view, Calendar.getInstance());
        addEvent(view);
    }
    
    private void renderCalendar(CalendarView view, Calendar cal) {
        view.getPanelMonth().removeAll();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Calendar start = (Calendar) cal.clone(), end = (Calendar) cal.clone();
        start.set(Calendar.DAY_OF_MONTH, 1);
        end.set(Calendar.DAY_OF_MONTH, maxDay);
        try {
            workingDay = statisticalDao.getWorkingDays(new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), id);
        } catch (SQLException ex) {
            workingDay = new WorkingDay();
        }
        int i;
        for (i = 1; i < dayOfWeek; i++) {
            Color color = Color.decode("#F0F0F0");
            DayView dayview = new DayView();
            dayview.setBackground(color);
            view.getPanelMonth().add(dayview);
        }
        for (i = 1; i <= maxDay; i++) {
            DayView dayView = new DayView();
            dayView.getLabelNumber().setText(i + "");
            Calendar thisDay = (Calendar) cal.clone();
            thisDay.set(Calendar.DAY_OF_MONTH, i);
            dayController.addDay(dayView, id, thisDay, workingDay.isWorking(thisDay));
            view.getPanelMonth().add(dayView);
        }
        for (i = maxDay + dayOfWeek; i <= 42; i++) {
            Color color = Color.decode("#F0F0F0");
            DayView dayview = new DayView();
            dayview.setBackground(color);
            view.getPanelMonth().add(dayview);
        }
    }

    

    private void renderStatistical(CalendarView view, Calendar cal) {
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Calendar start = (Calendar) cal.clone(), end = (Calendar) cal.clone();
        start.set(Calendar.DAY_OF_MONTH, 1);
        end.set(Calendar.DAY_OF_MONTH, maxDay);
        Timestamp startTime = new Timestamp(start.getTimeInMillis()), endTime = new Timestamp(end.getTimeInMillis());
        try {

            WorkingDay workingDays = statisticalDao.getWorkingDays(startTime, endTime, id);
            int totalOrder = statisticalDao.getTotalOrder(startTime, endTime, id);
            int totalAmount = statisticalDao.getTotalIncome(startTime, endTime, id);
            int totalTime = statisticalDao.getTotalWorkingMinutes(startTime, endTime, id);
            view.getLabelWorkingDay().setText(workingDays.count() + " / " + maxDay);
            view.getLabelNumberOfBills().setText(totalOrder + " đơn");
            view.getLabelBonus().setText(decimalFormat.format(totalOrder * 2000));
            view.getLabelSale().setText(decimalFormat.format(totalAmount));
            view.getLabelWorkingTime().setText(totalTime + " phút");
        } catch (SQLException e) {
            view.showError(e);
        }

    }

    private void addEvent(CalendarView view) {
        view.getBtnEnter().addActionListener(evt -> {
            int month = Integer.parseInt((String) view.getCmbMonth().getSelectedItem());
            int year = Integer.parseInt(view.getTxtYear().getText());
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            renderCalendar(view, cal);
//                RenderStatistical(month, year);
            renderStatistical(view, cal);
        });
    }
    
}