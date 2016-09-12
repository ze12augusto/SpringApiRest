package br.com.unipam.spring.api.rest.response;

import br.com.unipam.spring.api.rest.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductsResponse {
    private Long id;
    private String name;
    private Double value;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    public ProductsResponse(Product product) {
        this.name = product.getName();
        this.value = product.getValue();
        this.date = product.getDate();
        this.id = product.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
