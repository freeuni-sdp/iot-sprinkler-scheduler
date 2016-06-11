package ge.edu.freeuni.sdp.iot.service.scheduler.sprinkler.shchedule;

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
    private List<String> excluded;

    @XmlElement
    private Integer startMonth;

    @XmlElement
    private Integer endMonth;

    @XmlElement
    private Double beforeSunSet;

    @XmlElement
    private Double afterSunRise;

    public static Schedule getInstance() {
        return instance;
    }

    private static Schedule instance = new Schedule(new ArrayList<String>(),4,10,0.5,0.5);

    public Schedule (){}

    private Schedule(List<String> excluded, Integer startMonth, Integer endMonth,
                     Double afterSunRise, Double beforeSunSet) {
        this.excluded = excluded;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.afterSunRise = afterSunRise;
        this.beforeSunSet = beforeSunSet;
    }

    public Double getBeforeSunSet() {
        return beforeSunSet;
    }

    public void setBeforeSunSet(Double beforeSunSet) {
        this.beforeSunSet = beforeSunSet;
    }

    public Double getAfterSunRise() {
        return afterSunRise;
    }

    public void setAfterSunRise(Double afterSunRise) {
        this.afterSunRise = afterSunRise;
    }

    public List<String> getExcluded() {
        return excluded;
    }

    public void setExcluded(List<String> excluded) {
        this.excluded = excluded;
    }

    public void addExcluded(String day){
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
