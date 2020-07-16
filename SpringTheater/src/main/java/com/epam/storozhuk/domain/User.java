package com.epam.storozhuk.domain;

import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.lang.Nullable;
import com.epam.storozhuk.status.UserStatus;

public class User {
    private int id;
    private String name;
    private String email;
    private String birthday;
    private UserStatus userStatus;
    private Set<Ticket> tickets;

    public User() {
    }

    public User(@Nullable int id, String name, String email, String birthday, UserStatus userStatus, @Nullable Set<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.userStatus = userStatus;
        if (tickets != null) {
            this.tickets = new LinkedHashSet<>(tickets);
        }
    }

    public User(String name, String email, String birthday, UserStatus userStatus, @Nullable Set<Ticket> tickets) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.userStatus = userStatus;
        if (tickets != null) {
            this.tickets = new LinkedHashSet<>(tickets);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public static User emptyUser() {
        return new User();
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public boolean addTicket(Ticket ticket) {
        if (tickets == null) {
            tickets = new LinkedHashSet<Ticket>();
        }
        return tickets.add(ticket);
    }
}
