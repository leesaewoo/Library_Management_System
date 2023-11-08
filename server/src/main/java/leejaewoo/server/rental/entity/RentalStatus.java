package leejaewoo.server.rental.entity;

public enum RentalStatus {
    AVAILABLE("대여가능"),
    UNAVAILABLE("대여불가");

    private final String statusDescription;

    RentalStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getName() {
        return name();
    }

    public String getDescription() {
        return statusDescription;
    }
}
