package com.example.manageaid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Float sum;

    @Size(max = 200)
    private String details;

    @DateTimeFormat
    private Date dateMade;

    @NotBlank
    @Size(max = 200)
    private String payerInfo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Float getSum() {
        return sum;
    }

    public void setSum(@NotNull Float sum) {
        this.sum = sum;
    }

    public @Size(max = 200) String getDetails() {
        return details;
    }

    public void setDetails(@Size(max = 200) String details) {
        this.details = details;
    }

    public @NotBlank String getPayerInfo() {
        return payerInfo;
    }

    public void setPayerInfo(@NotBlank String payerInfo) {
        this.payerInfo = payerInfo;
    }

    public Date getDateMade() {
        return dateMade;
    }

    public void setDateMade(Date dateMade) {
        this.dateMade = dateMade;
    }
}
