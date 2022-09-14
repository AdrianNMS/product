package com.product.product.models.service;

import com.product.product.models.documents.Product;
import rx.Completable;
import rx.Single;

import java.util.List;


public interface ProductService
{
    Single<Product> create(Product pro);

    Single<Product>  update(String id, Product pro);

    Completable delete(String id);

    Single<List<Product>> findAll();

    Single<Product> find(String id);
}
