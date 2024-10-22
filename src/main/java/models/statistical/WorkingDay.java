/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.statistical;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author P51
 */
public class WorkingDay {
    private ArrayList<Calendar> dates = new ArrayList<>();
    
    public void addWorkDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        valid(calendar);
        if (!dates.contains(calendar)) {
            dates.add(calendar);
        }
    }
    private void valid(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
    }
    public boolean isWorking(Calendar cal) {
        if (cal == null) {
            return false;
        }
        valid(cal);
        return dates.contains(cal);
    }
    public void print() {
        for (Calendar date : dates) {
            System.out.println(date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR));
        }
    }

    public int count() {
        return dates.size();
    }

    @Override
    public String toString() {
        return "WorkingDay{" + "dates=" + dates + '}';
    }
    
}
