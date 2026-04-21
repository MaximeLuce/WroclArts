package pl.edu.pwr.tkubik.ism.model;

public class OrganizationStatsDTO {

    private String organizationName;
    private Long eventCount;

    // getters and setters

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getEventCount() {
        return eventCount;
    }

    public void setEventCount(Long eventCount) {
        this.eventCount = eventCount;
    }

    // constructors

    public OrganizationStatsDTO() {
    }

    public OrganizationStatsDTO(String organizationName, Long eventCount) {
        this.organizationName = organizationName;
        this.eventCount = eventCount;
    }
}