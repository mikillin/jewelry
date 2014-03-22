package com.project.model;

import javax.persistence.*;

/**
 * User: S.Rogachevsky
 * Date: 08.03.14
 * Time: 18:23
 */
@MappedSuperclass
public class BaseModel {

    @Id
    @Column(name  = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
