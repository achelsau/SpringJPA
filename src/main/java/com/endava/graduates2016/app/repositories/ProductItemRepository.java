package com.endava.graduates2016.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.endava.graduates2016.app.domain.ProductItem;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {
	public List<ProductItem> readByNameIgnoringCase(String name);
}
