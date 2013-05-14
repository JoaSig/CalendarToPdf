package dk.hagendazzlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date: 07/04/13
 */
@SuppressWarnings("unchecked")
public class TimeDuration {

    private long milliSec;
    private String startTime;
    private String endTime;
    private List<Long> durations = new ArrayList<Long>();
    private Set startTimes;
    private Set endTimes;

    public TimeDuration(long milliSec) {
        this.milliSec = milliSec;
        addSubDuration(milliSec);

        startTimes = new HashSet();

        endTimes = new HashSet();
    }

    public long getMilliSec() {
        return milliSec;
    }

    public void setMilliSec(long milliSec) {
        this.milliSec = milliSec;
    }

    public Long getTotalTimeDuration() {
        Long totalTimeDuration = 0L;

        for (Long duration : durations) {
            totalTimeDuration += duration;
        }
        return totalTimeDuration;
    }

    public String getTotalDuration() {
        return getDuration(getTotalTimeDuration());
    }

    public String getDuration(Long milliSec) {
        Integer mins = Integer.valueOf("" + milliSec) / 1000 / 60;
        Integer totalMins = mins;
        Integer hrs = 0;
        if (mins / 60 >= 1) {
            hrs = mins / 60;
            totalMins = mins - (hrs * 60);
        }

        String stringMins = String.valueOf(totalMins);
        if (stringMins.length() < 2) {
            stringMins = "0".concat(stringMins);
        }

        String formattedMins = String.format("0%d:%s", hrs, stringMins);
        if (hrs >= 10) {
            formattedMins = String.format("%d:%s", hrs, stringMins);
        }
        return formattedMins;
    }

    public String getDuration() {
        return getDuration(milliSec);
    }

    public void addSubDuration(Long milliSec) {
        this.milliSec = milliSec;
        durations.add(milliSec);
    }

    public String getStartTimeOfTheDay() {
        List<String> times = new ArrayList<String>(startTimes);
        Collections.sort(times);
        return times.get(0);
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void addStartTime(String newStartTime) {
        startTimes.add(newStartTime);
        this.startTime = newStartTime;
    }

    public String getEndOfDayTime() {
        List<String> times = new ArrayList<String>(endTimes);
        Collections.sort(times);
        return times.get(times.size() - 1);
    }

    public void addEndTime(String newEndTime) {
        endTimes.add(newEndTime);
        this.endTime = newEndTime;
    }

    public String getDurationInHrsAndMins(Long timeInMilliSec) {
        Integer mins = Integer.valueOf("" + timeInMilliSec) / 1000 / 60;
        Integer totalMins = mins;
        Integer hrs = 0;
        if (mins / 60 >= 1) {
            hrs = mins / 60;
            totalMins = mins - (hrs * 60);
        }
        if (totalMins.equals(0)) {
            return String.format("%dt.", hrs);
        }
        return String.format("%dt. %smin.", hrs, totalMins.toString());
    }

    public String getDurationInHrsAndMins() {
        return getDurationInHrsAndMins(milliSec);
    }

    public String getTotalDurationInHrsAndMins() {
        Long totalTimeDuration = getTotalTimeDuration();
        return getDurationInHrsAndMins(totalTimeDuration);
    }
}
