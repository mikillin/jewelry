package com.project.model;


import javax.persistence.*;
import java.util.Date;

/**
 * User: S.Rogachevsky
 * Date: 20.02.14
 * Time: 6:34
 */

@Entity
@Table(name = "ORDERS")
public class Order extends BaseModel {

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Column(name = "ORDER_DATE_COMPLETION")
    private Date orderDateCompletion;

    @Column(name = "FIO")
    private String fio;

    @Column(name = "HOME_ADDRESS")
    private String homeAddress;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "LOSES")
    private Float loses;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderMaterial orderMaterial;

    @Column(name = "ADVANCE")
    private Integer advance;


    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDateCompletion() {
        return orderDateCompletion;
    }

    public void setOrderDateCompletion(Date orderDateCompletion) {
        this.orderDateCompletion = orderDateCompletion;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Float getLoses() {
        return loses;
    }

    public void setLoses(Float loses) {
        this.loses = loses;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public OrderMaterial getOrderMaterial() {
        return orderMaterial;
    }

    public void setOrderMaterial(OrderMaterial material) {
        this.orderMaterial = material;
    }

    public Integer getAdvance() {
        return advance;
    }

    public void setAdvance(Integer advance) {
        this.advance = advance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        Order order = (Order) o;
        if (!this.getId().equals(order.getId())) return false;
        if (!this.getFio().equals(order.getFio())) return false;
        if (!this.getHomeAddress().equals(order.getHomeAddress())) return false;
        if (!this.getLoses().equals(order.getLoses())) return false;
        if (!this.getOrderDate().equals(order.getOrderDate())) return false;
        if (!this.getOrderDateCompletion().equals(order.getOrderDateCompletion())) return false;
        if (!this.getOrderNumber().equals(order.getOrderNumber())) return false;
        if (!this.getFio().equals(order.getFio())) return false;
        if (!this.getPhone().equals(order.getPhone())) return false;
        if (!this.getProductName().equals(order.getProductName())) return false;
        if (!this.getOrderMaterial().equals(order.getOrderMaterial())) return false;
        if (!this.getAdvance().equals(order.getAdvance())) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 1;
        result = 31 * result + (getOrderNumber() != null ? getOrderNumber().hashCode() : 1);
        result = 31 * result + (getLoses() != null ? getLoses().hashCode() : 1);
        result = 31 * result + (getFio() != null ? getFio().hashCode() : 1);
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 1);
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 1);
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 1);
        result = 31 * result + (getHomeAddress() != null ? getHomeAddress().hashCode() : 1);
        result = 31 * result + (getOrderDateCompletion() != null ? getOrderDateCompletion().hashCode() : 1);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 1);
        result = 31 * result + (getOrderMaterial() != null ? getOrderMaterial().hashCode() : 1);
        result = 31 * result + (getAdvance() != null ? getAdvance().hashCode() : 1);


        return result;
    }

}
