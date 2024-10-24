package controllers.employee;


import java.awt.Color;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;

import models.statistical.WorkingDay;
import dao.statistical.StatisticalDao;

import views.employee.CalendarView;
import views.employee.DayView;


public class CalendarController {
    
    private CalendarView view;
    //private int id = SessionManager.getSession().getIdEmployee();
    private StatisticalDao statisticalDao = new StatisticalDao();
    private WorkingDay workingDay;
    private DayController dayController = new DayController();
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,### VND");
}
