package events;

public class Organizer {
    private Integer id;
    private String designation;

    private Organizer (Integer id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public String getName() {
        return designation;
    }
}
