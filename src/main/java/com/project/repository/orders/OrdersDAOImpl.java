package com.project.repository.orders;

import com.project.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: S.Rogachevsky
 * Date: 20.02.14
 * Time: 14:24
 */

@Repository
public class OrdersDAOImpl implements OrderDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Long saveOrder(Order order) {

        return (Long)sessionFactory.getCurrentSession().save(order);
    }

    @Transactional
    public Long getLastOrderNumber(){
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select max(id) from Order").list().get(0);
    }
}
