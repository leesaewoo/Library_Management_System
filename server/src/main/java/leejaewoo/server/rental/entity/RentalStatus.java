package leejaewoo.server.rental.entity;

public enum RentalStatus {

    COMPLETE_RETURN("반납완료(대여가능)"),
    ON_RENTAL("대여중(대여불가)");

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
