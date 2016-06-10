package ge.edu.freeuni.sdp.sprinklerscheduler.core.shchedule;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezo on 6/10/16.
 */
@XmlRootElement
public class Schedule {

    @XmlElement
    private List<Day> excluded;

    @XmlElement
    private Integer startMonth;

    @XmlElement
    private Integer endMonth;

    @XmlElement
    private Integer beforeSunSet;

    @XmlElement
    private Integer afterSunRise;

    public static Schedule getInstance() {
        return instance;
    }

    private static Schedule instance = new Schedule(new ArrayList<Day>(),4,10);

    public Schedule (){}

    private Schedule(List<Day> excluded, Integer startMonth, Integer endMonth) {
        this.excluded = excluded;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public Integer getBeforeSunSet() {
        return beforeSunSet;
    }

    public void setBeforeSunSet(Integer beforeSunSet) {
        this.beforeSunSet = beforeSunSet;
    }

    public Integer getAfterSunRise() {
        return afterSunRise;
    }

    public void setAfterSunRise(Integer afterSunRise) {
        this.afterSunRise = afterSunRise;
    }

    public List<Day> getExcluded() {
        return excluded;
    }

    public void setExcluded(List<Day> excluded) {
        this.excluded = excluded;
    }

    public void addExcluded(Day day){
        this.excluded.add(day);
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }
}
