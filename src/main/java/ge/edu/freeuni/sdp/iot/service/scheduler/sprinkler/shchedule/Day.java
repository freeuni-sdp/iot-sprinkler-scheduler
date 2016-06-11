package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by rezo on 6/10/16.
 */
@XmlRootElement
public class Day {
    @XmlElement
    private Integer day;
    @XmlElement
    private Integer month;
    @XmlElement
    private Integer year;

    public Day(Integer day, Integer month, Integer year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Day(){}
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
