package infoshareacademy;

import events.EventJson;

public class Event implements Comparable<Event>{
    private EventJson eventJson;
    private String sortParameter;
    private Integer display;

    public Event (EventJson eventJson) {
        this.eventJson = eventJson;
        sortParameter = "ID";
        this.display = 1;
    }

    public EventJson getEventJson() {
        return eventJson;
    }

    public String getSortParameter() {
        return sortParameter;
    }

    public void setSortParameter(String sortParameter) {
        this.sortParameter = sortParameter;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getDisplay() {
        return display;
    }

    @Override
    public int compareTo (Event o) {
        return this.getSortParameter().compareTo(o.getSortParameter());
    }
}
