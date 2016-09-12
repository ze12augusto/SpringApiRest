package br.com.unipam.spring.api.rest.service;

import br.com.unipam.spring.api.rest.domain.Product;
import br.com.unipam.spring.api.rest.repository.ProductRepository;
import br.com.unipam.spring.api.rest.request.ProductRequest;
import br.com.unipam.spring.api.rest.response.ProductsResponse;
import br.com.unipam.spring.api.rest.service.util.SortEnum;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    ProductRepository productRepository;

    private Sort orderBy(SortEnum sortEnum) {
        if (sortEnum != null) {
            return new Sort(sortEnum.getDirection(), sortEnum.getValue());
        }
        return new Sort(Sort.Direction.DESC, "id");
    }

    public List<ProductsResponse> getValidProducts(SortEnum sort, Integer maxDays) {
        List<ProductsResponse> productsResponseList = new ArrayList<>();
        Calendar limitDate = Calendar.getInstance();
        limitDate.add(Calendar.DATE, maxDays);
        List<Product> products = productRepository.findAllByDateBetween(Calendar.getInstance().getTime(), limitDate.getTime(), orderBy(sort));
        productsResponseList.addAll(products.stream().map(ProductsResponse::new).collect(Collectors.toList()));
        return productsResponseList;
    }

    public List<ProductsResponse> getProducts(SortEnum sort) {
        List<ProductsResponse> productsResponseList = new ArrayList<>();
        List<Product> products = productRepository.findAll(orderBy(sort));
        productsResponseList.addAll(products.stream().map(ProductsResponse::new).collect(Collectors.toList()));
        return productsResponseList;
    }

    public void saveProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setValue(request.getValue());
        product.setDate(request.getDate());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    public void updateProduct(Long id, ProductRequest request) throws NotFoundException {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new NotFoundException("Produto não encontrado");
        }
        product.setName(request.getName());
        product.setValue(request.getValue());
        product.setDate(request.getDate());
        productRepository.save(product);
    }
}
