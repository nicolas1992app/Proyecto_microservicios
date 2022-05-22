package com.example.serviceproduct.controller;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.entity.Product;
import com.example.serviceproduct.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "/products",
                method = { RequestMethod.POST,
                           RequestMethod.DELETE,
                           RequestMethod.POST })
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>>ListProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products;
        if (null==categoryId){
            products = productService.listAllProduct();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
        }
        if (products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if (null == product){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){

        if (result.hasErrors()){
            System.out.println("si llego: " + this.formatMessage(result));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product productCreate =  productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping(value = "/update/{id}", consumes="application/json")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);
        Product productDB = productService.updateProduct(product);
        if (productDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDelete = productService.deleteProduct(id);
        if (productDelete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id,
                                                      @RequestParam(name = "quantity") Double quality){
        Product product = productService.updateStock(id, quality);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    private String formatMessage( BindingResult result){
        log.info("INFO_RESULT",result);
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("resultados: "+jsonString);
        return jsonString;
    }
}
