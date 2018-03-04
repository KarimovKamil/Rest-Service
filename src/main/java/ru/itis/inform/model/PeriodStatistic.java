package ru.itis.inform.model;

public class PeriodStatistic {
    private int totalVisits;
    private int uniqueUsers;
    private int regularUsers;

    public PeriodStatistic(int totalVisits, int uniqueUsers, int regularUsers) {
        this.totalVisits = totalVisits;
        this.uniqueUsers = uniqueUsers;
        this.regularUsers = regularUsers;
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(int totalVisits) {
        this.totalVisits = totalVisits;
    }

    public int getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(int uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }

    public int getRegularUsers() {
        return regularUsers;
    }

    public void setRegularUsers(int regularUsers) {
        this.regularUsers = regularUsers;
    }
}
