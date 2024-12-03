package org.example.springjpaalena.ppt_tasks;

public class DateService {
    public int getYear(String dateString) {
        String[] dateArray = dateString.split("-");
        return Integer.valueOf(dateArray[0]);
    }
}
