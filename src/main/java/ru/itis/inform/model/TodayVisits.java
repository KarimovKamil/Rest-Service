package ru.itis.inform.model;

public class TodayVisits {
    private int totalVisits;
    private int uniqueUsers;

    public TodayVisits(int totalVisits, int uniqueUsers) {
        this.totalVisits = totalVisits;
        this.uniqueUsers = uniqueUsers;
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
}
