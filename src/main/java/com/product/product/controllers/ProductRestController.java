package com.product.product.controllers;

import com.product.product.handlers.ResponseHandler;
import com.product.product.models.documents.Product;
import com.product.product.models.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rx.Single;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping("/api/")
public class ProductRestController
{
    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @PostMapping
    public Single<ResponseEntity<Object>> create(@RequestBody Product pro)
    {
        return productService.create(pro)
                .subscribeOn(Schedulers.io())
                .map(product -> ResponseHandler.response("Done", HttpStatus.OK, product))
                .onErrorReturn(throwable ->
                        ResponseHandler.response(throwable.getMessage(), HttpStatus.NOT_FOUND, null)
                );
    }

    @PutMapping("/{id}")
    public Single<ResponseEntity<Object>> update(@PathVariable String id, @RequestBody Product pro)
    {
        return productService.update(id, pro)
                .subscribeOn(Schedulers.io())
                .map(product -> ResponseHandler.response("Done", HttpStatus.OK, product))
                .onErrorReturn(throwable ->
                    ResponseHandler.response(throwable.getMessage(), HttpStatus.NOT_FOUND, null)
                );
    }

    @DeleteMapping("/{id}")
    public Single<ResponseEntity<Object>> delete(@PathVariable String id)
    {
        return productService.delete(id)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseHandler.response("Done", HttpStatus.OK, null))
                .onErrorReturn(throwable ->
                        ResponseHandler.response(throwable.getMessage(), HttpStatus.NOT_FOUND, null)
                );
    }

    @GetMapping
    public Single<ResponseEntity<Object>> findAll()
    {
        return productService.findAll()
                .subscribeOn(Schedulers.io())
                .map(products -> ResponseHandler.response("Done", HttpStatus.OK, products))
                .onErrorReturn(throwable ->
                        ResponseHandler.response(throwable.getMessage(), HttpStatus.NOT_FOUND, null)
                );
    }

    @GetMapping("/{id}")
    public Single<ResponseEntity<Object>> find(@PathVariable String id)
    {
        return productService.find(id)
                .subscribeOn(Schedulers.io())
                .map(product -> ResponseHandler.response("Done", HttpStatus.OK, product))
                .onErrorReturn(throwable ->
                        ResponseHandler.response(throwable.getMessage(), HttpStatus.NOT_FOUND, null)
                );
    }
}
