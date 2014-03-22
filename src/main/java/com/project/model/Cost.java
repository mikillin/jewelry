package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: S.Rogachevsky
 * Date: 08.03.14
 * Time: 20:18
 */


@Entity
@Table(name = "COST")
public class Cost extends BaseModel {


    @Column(name  = "ORDER_NUMBER")
    private Integer orderNumber;

    @Column(name = "PRODUCT_COST")
    private Integer productCost;

    @Column(name = "MATERIAL_CONTENT_TEST")
    private Integer materialContentTest;

    @Column(name = "CELERITY")
    private Integer  celerity;

    @Column(name = "SERVICE")
    private Integer  service;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getMaterialContentTest() {
        return materialContentTest;
    }

    public void setMaterialContentTest(Integer materialContent) {
        this.materialContentTest = materialContent;
    }

    public Integer getCelerity() {
        return celerity;
    }

    public void setCelerity(Integer celerity) {
        this.celerity = celerity;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getProductCost() {
        return productCost;
    }

    public void setProductCost(Integer productCost) {
        this.productCost = productCost;
    }

    @Override
    public int hashCode() {
        int result  = getId() != null ? getId().hashCode() : 1;

        result = 31 * result + (getOrderNumber() != null ? getOrderNumber().hashCode() : 1);
        result = 31 * result + (getProductCost() != null ? getProductCost().hashCode() : 1);
        result = 31 * result + (getCelerity() != null ? getCelerity().hashCode() : 1);
        result = 31 * result + (getMaterialContentTest() != null ? getMaterialContentTest().hashCode() : 1);
        result = 31 * result + (getService() != null ? getService().hashCode() : 1);

        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost = (Cost) o;

        if (!this.getId().equals(cost.getId())) return  false;
        if (!this.getService().equals(cost.getService())) return false;
        if (!this.getProductCost().equals(cost.getProductCost())) return false;
        if (!this.getMaterialContentTest().equals(cost.getMaterialContentTest())) return false;
        if (!this.getCelerity().equals(cost.getCelerity())) return false;
        if (!this.getOrderNumber().equals(cost.getOrderNumber())) return false;

        return true;


    }
}
