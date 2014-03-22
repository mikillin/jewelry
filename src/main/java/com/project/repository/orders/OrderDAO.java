package com.project.repository.orders;

import com.project.model.Order;

/**
 * User: S.Rogachevsky
 * Date: 24.02.14
 * Time: 21:54
 */
public interface OrderDAO {

    public Long saveOrder(Order order);

    public Long getLastOrderNumber();

}
