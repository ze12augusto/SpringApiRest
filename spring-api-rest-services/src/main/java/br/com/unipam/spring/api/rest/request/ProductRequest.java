package br.com.unipam.spring.api.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProductRequest {
    @NotNull
    private String name;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @NotNull
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
