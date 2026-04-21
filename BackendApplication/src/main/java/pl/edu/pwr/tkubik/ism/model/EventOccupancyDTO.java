package pl.edu.pwr.tkubik.ism.model;

public class EventOccupancyDTO {

    private String eventTitle;
    private Long userCount;

    // getters and setters

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    // constructors

    public EventOccupancyDTO() {
    }

    public EventOccupancyDTO(String eventTitle, Long userCount) {
        this.eventTitle = eventTitle;
        this.userCount = userCount;
    }
}