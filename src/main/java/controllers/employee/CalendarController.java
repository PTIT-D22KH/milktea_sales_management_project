package controllers.employee;


import dao.WorkDayDao;
import java.awt.Color;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;

import models.statistical.*;
import dao.statistical.StatisticalDao;
import utils.SessionManager;

import views.employee.CalendarView;
import views.employee.DayView;


public class CalendarController {
    
    private CalendarView view;
    private int id = SessionManager.getSession().getEmployeeId();
    private StatisticalDao statisticalDao = new StatisticalDao();
    private WorkingDay workingDay;
    private DayController dayController = new DayController();
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,### VND");
    
    public CalendarController() {
        
    }
    
    public CalendarController(CalendarView view) {
        this.view = view;
        this.view.getCmbMonth().setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        this.view.getTxtYear().setText(String.valueOf(LocalDate.now().getYear()));
        RenderCalendar(view, Calendar.getInstance());
        RenderStatistical(view, Calendar.getInstance());
        addEvent(view);
    }
    
    public void show(JPanel panelRoot, CalendarView view) {
        panelRoot.add(view);
        Calendar cal = Calendar.getInstance();
        view.getCmbMonth().setSelectedIndex(cal.get(Calendar.MONTH));
        view.getTxtYear().setText(cal.get(Calendar.YEAR) + "");
        RenderCalendar(view, Calendar.getInstance());
        RenderStatistical(view, Calendar.getInstance());
        addEvent(view);
    }
    
    public void RenderCalendar(CalendarView view, Calendar cal) {
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

    public void RenderCalendar(int month, int year) {
        view.getPanelMonth().removeAll();
        int day = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
        int days = new GetDayOfMonth(month, year).getDay();
        if (day < 7) {
            for (int i = 0; i < day; i++) {
                Color color = Color.decode("#F0F0F0");
                DayView dayview = new DayView();
                dayview.getLabelNumber().setBackground(color);
                view.getPanelMonth().add(dayview);
            }
        }
        try {
            WorkDayDao workDayDao = new WorkDayDao();
            ArrayList<Integer> list = workDayDao.getDay(id, month, year);
            if (list.size() == 0) {
                for (int i = 1; i <= days; i++) {
                    String date = year + "-" + month + "-" + i;
                    DayView dayView = new DayView();
                    DayController dayController = new DayController(dayView, date, i, false, id);
                    view.getPanelMonth().add(dayView);
                }
            } else {
                int j = 0;
                for (int i = 1; i <= days; i++) {
                    if (list.get(j) == i) {
                        DayView dayView = new DayView();
                        System.out.println(list.get(j));
                        String date = year + "-" + month + "-" + i;
                        DayController dayController = new DayController(dayView, date, i, true, id);
                        view.getPanelMonth().add(dayView);
                        if (j < list.size() - 1) {
                            j++;
                        }
                    } else {
                        String date = year + "-" + month + "-" + i;
                        DayView dayView = new DayView();
                        DayController dayController = new DayController(dayView, date, i, false, id);
                        view.getPanelMonth().add(dayView);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (day == 7) {
            day = 0;
        }
        for (int i = 1; i <= 42 - days - day; i++) {
            Color color = Color.decode("#F0F0F0");
            DayView dayview = new DayView();
            dayview.getLabelNumber().setBackground(color);
            view.getPanelMonth().add(dayview);
        }

        view.updateUI();
    }
    
    public void RenderStatistical(int month, int year) {
        try {
            int days = new GetDayOfMonth(month, year).getDay();
            WorkDayDao workDayDao = new WorkDayDao();
            view.getLabelWorkingDay().setText(workDayDao.getDay(id, month, year).size() + "/" + days);
            view.getLabelNumberOfBills().setText(workDayDao.getTotalOrder(year, month, id) + " đơn");
            view.getLabelWorkingTime().setText(workDayDao.getTotalWorkingMinutes(month, year, id) + " phút");
            view.getLabelSale().setText(workDayDao.getTotalIncome(year, month, id) + "đ");
            view.getLabelBonus().setText(workDayDao.getBonus(id, month, year) + "đ");
        } catch (Exception e) {

        }

    }

    private void RenderStatistical(CalendarView view, Calendar cal) {
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
        } catch (Exception e) {
            view.showError(e);
        }

    }

    public void addEvent(CalendarView view) {
        view.getBtnEnter().addActionListener(evt -> {
            int month = Integer.valueOf((String) view.getCmbMonth().getSelectedItem());
            int year = Integer.parseInt(view.getTxtYear().getText());
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            RenderCalendar(view, cal);
//                RenderStatistical(month, year);
            RenderStatistical(view, cal);
        });
    }
    
}
