package com.example.manageaid.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "memberships")
public class Membership {
    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 200)
    private String offer;

    @DateTimeFormat
    private Date startDate;

    @DateTimeFormat
    private Date endDate;

    @NotBlank
    @Size(max = 200)
    private String customerInfo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(max = 200) String getOffer() {
        return offer;
    }

    public void setOffer(@Size(max = 200) String offer) {
        this.offer = offer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public @NotBlank @Size(max = 200) String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(@NotBlank @Size(max = 200) String customerInfo) {
        this.customerInfo = customerInfo;
    }
}
