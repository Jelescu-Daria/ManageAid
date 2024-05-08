package com.example.manageaid.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @NotNull
    @DateTimeFormat
    private Date dateMade;

    @NotBlank
    @Size(max = 200)
    private String payerInfo;

    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateMade() {
        return dateMade;
    }

    public void setDateMade(Date dateMade) {
        this.dateMade = dateMade;
    }
}
