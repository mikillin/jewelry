package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: S.Rogachevsky
 * Date: 08.03.14
 * Time: 18:17
 */

@Entity
@Table(name = "MATERIAL")
public class Material extends BaseModel {

    @Column(name = "CODE")
    private Integer code;


    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material m = (Material) o;

        if (!m.getId().equals(this.getId())) return false;
        if (!m.getCode().equals(this.getCode())) return false;
        if (!m.getDescription().equals(this.getDescription())) return false;
        if (!m.getName().equals(this.getName())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 1;
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 1);
        result = 31 * result + (getName() != null ? getName().hashCode() : 1);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 1);

        return result;
    }
}
