package com.endava.graduates2016.app.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.endava.graduates2016.app.domain.OrderItem;

@Repository
@Transactional
public class OrderRepository {

	@PersistenceContext
	private EntityManager em;

	public void addOrderItem(OrderItem order) {
		em.persist(order);
	}

	public OrderItem getOrderItemById(long id) {
		return em.find(OrderItem.class, id);
	}

	public void saveOrderItem(OrderItem spitter) {
		em.merge(spitter);
	}
}
