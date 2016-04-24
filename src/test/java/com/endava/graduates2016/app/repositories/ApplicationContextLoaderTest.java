package com.endava.graduates2016.app.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.endava.graduates2016.app.domain.OrderItem;
import com.endava.graduates2016.app.domain.ProductItem;
import com.endava.graduates2016.configuration.SpringJPAConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJPAConfiguration.class)
public class ApplicationContextLoaderTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductItemRepository productItemRepository;

	@Test
	public void insertionWorking() {
		OrderItem persistedOrder = new OrderItem();
		persistedOrder.setDatePlaced(new Date());
		persistedOrder.setStoreName("EMAG");
		ProductItem productItem1 = new ProductItem();
		productItem1.setAddedOnSite(new DateTime(2016, 1, 1, 1, 1).toDate());
		productItem1.setName("TV");
		productItem1.setPrice(2.34);
		productItem1.setSpecs("Samsung");
		productItem1.setStock(2);

		ProductItem productItem2 = new ProductItem();
		productItem2.setAddedOnSite(new DateTime(2016, 1, 1, 1, 1).toDate());
		productItem2.setName("PC");
		productItem2.setPrice(4.34);
		productItem2.setSpecs("Dell");
		productItem2.setStock(1);
		List<ProductItem> productItem = new ArrayList<ProductItem>();
		productItem.add(productItem1);
		productItem.add(productItem2);
		persistedOrder.setItems(productItem);

		orderRepository.addOrderItem(persistedOrder);

		OrderItem retrievedOrder = orderRepository.getOrderItemById(persistedOrder.getOrderItemId());
		Assert.assertEquals(persistedOrder, retrievedOrder);
	}

	@Test
	public void testSpringDataAutoRepo() {
		ProductItem product = productItemRepository.findOne(1);
		Assert.assertEquals("TV", product.getName());
	}

	@Test
	public void testReadByNameIgnoringCase() {
		List<ProductItem> items = productItemRepository.readByNameIgnoringCase("TV");

		Assert.assertTrue(items.size() > 0);
	}
}
