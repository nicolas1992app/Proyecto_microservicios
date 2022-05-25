package com.example.shoppingservice.store.shopping.repository;

import  com.example.shoppingservice.store.shopping.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
