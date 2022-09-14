package com.product.product.models.service.impl;

import com.product.product.models.documents.Product;
import com.product.product.models.repository.ProductRepository;
import com.product.product.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Completable;
import rx.Single;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements ProductService
{
    @Autowired
    ProductRepository productRepository;

    @Override
    public Single<Product> create(Product pro) {
        return Single.create(singleSubscriber -> {
           var prod = productRepository.save(pro);
           singleSubscriber.onSuccess(prod);
        });
    }

    @Override
    public Single<Product> update(String id, Product pro) {
        return Single.create(singleSubscriber -> {
           if(productRepository.existsById(id))
           {
               var prod = productRepository.save(pro);
               singleSubscriber.onSuccess(prod);
           }
           else
           {
               singleSubscriber.onError(new Throwable());
           }

        });
    }

    @Override
    public Completable delete(String id) {
        return Completable.create(completableSubscriber -> {
           if(productRepository.existsById(id))
           {
               productRepository.deleteById(id);
               completableSubscriber.onCompleted();
           }
           else
           {
               completableSubscriber.onError(new Throwable());
           }
        });
    }

    @Override
    public Single<List<Product>> findAll() {
        return Single.create(singleSubscriber -> {
            List<Product> productList = productRepository.findAll();
            singleSubscriber.onSuccess(productList);
        });
    }

    @Override
    public Single<Product> find(String id) {
        return Single.create(singleSubscriber -> {
            Optional<Product> optionalProduct = productRepository.findById(id);

            if(optionalProduct.isPresent())
                singleSubscriber.onSuccess(optionalProduct.get());
            else
                singleSubscriber.onError(new Throwable());
        });
    }
}
