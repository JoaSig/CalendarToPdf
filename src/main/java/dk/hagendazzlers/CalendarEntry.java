package dk.hagendazzlers;

import net.fortuna.ical4j.model.component.VEvent;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Date: 08/05/13
 */
public class CalendarEntry implements Comparator<CalendarEntry> {

    private Integer startTime;
    private VEvent event;

    public CalendarEntry(int startTime, VEvent event) {
        this.startTime = startTime;
        this.event = event;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public VEvent getEvent() {
        return event;
    }

    public void setEvent(VEvent event) {
        this.event = event;
    }

    @Override
    public int compare(CalendarEntry calendarEntry1, CalendarEntry calendarEntry2) {
        return calendarEntry1.getStartTime().compareTo(calendarEntry2.getStartTime());
    }

//    @Override
//    public int compare(Integer startTime1, Integer startTime2) {
//        return startTime1.compareTo(startTime2);
//    }

    public static class ByStartTimeComparator implements Comparator<Integer> {
        public int compare(Integer st1, Integer st2) {
            return doCompare(st1, st2);
        }

        public static int doCompare(Integer startTime1, Integer startTime2) {
            return startTime1.compareTo(startTime2);
        }

        public static List<Integer> sort(List<Integer> contents) {
            Collections.sort(contents, new ByStartTimeComparator());
            return contents;
        }
    }
}
