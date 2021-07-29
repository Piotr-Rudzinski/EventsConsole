package events;

import java.util.List;

public class EventJson{
    private Integer id;
    private Place place;
    private String endDate;
    private String name;
    private Urls urls;
    private List<FileName> attachments;
    private String descLong;
    private Integer categoryId;
    private String startDate;
    private Organizer organizer;
    private String active;
    private String descShort;
    private Tickets tickets;

    public EventJson(Integer id, Place place, String endDate, String name, Urls urls, List<FileName> attachments, String descLong,
                     Integer categoryId, String startDate, Organizer organizer, String active, String descShort, Tickets tickets) {
        this.id = id;
        this.place = place;
        this.endDate = endDate;
        this.name = name;
        this.urls = urls;
        this.attachments = attachments;
        this.descLong = descLong;
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.organizer = organizer;
        this.active = active;
        this.descShort = descShort;
        this.tickets = tickets;
    }

    public Integer getID () {
        return id;
    }

    public String getDescLong () {
       return  descLong;
    }

    public Place getPlace () {
        return  place;
    }

    public void setID (Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescShort() {
        return descShort;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public Urls getUrls() {
        return urls;
    }

    public FileName getAttachment() {
        if (attachments.isEmpty()) {
            FileName fileName = new FileName("-");
            attachments.add(fileName);
        }
        return attachments.get(0);
    }

    public String getActive() {
        return active;
    }

    public Tickets getTickets() {
        return tickets;
    }
}
