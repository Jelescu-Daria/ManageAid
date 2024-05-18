package com.example.manageaid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {
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
    private String payeeInfo;

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


    public Date getDateMade() {
        return dateMade;
    }

    public void setDateMade(Date dateMade) {
        this.dateMade = dateMade;
    }

    public @NotBlank @Size(max = 200) String getPayeeInfo() {
        return payeeInfo;
    }

    public void setPayeeInfo(@NotBlank @Size(max = 200) String payeeInfo) {
        this.payeeInfo = payeeInfo;
    }
}
