package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * User: S.Rogachevsky
 * Date: 08.03.14
 * Time: 19:39
 */
@Entity
@Table(name = "CUSTOMER_MATERIAL")
public class OrderMaterial extends BaseModel {

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @Column(name = "MATERIAL")
    private Integer material;

    @Column(name = "MATERIAL_CONTENT")
    private Integer materialContent;


    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public Integer getMaterialContent() {
        return materialContent;
    }

    public void setMaterialContent(Integer materialContent) {
        this.materialContent = materialContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderMaterial cm = (OrderMaterial) o;

        if (!this.getId().equals(cm.getId())) return false;
        if (!this.getMaterial().equals(cm.getMaterial())) return false;
        if (!this.getMaterialContent().equals(cm.getMaterialContent())) return false;
        if (!this.getOrderNumber().equals(cm.getOrderNumber())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 1;
        result = 31 * result + (this.getOrderNumber() != null ? this.getOrderNumber().hashCode() : 1);
        result = 31 * result + (this.getMaterial() != null ? this.getMaterial().hashCode() : 1);
        result = 31 * result + (this.getMaterialContent() != null ? this.getMaterialContent().hashCode() : 1);

        return result;

    }


}
