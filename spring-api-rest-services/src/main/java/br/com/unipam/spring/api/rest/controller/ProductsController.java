package br.com.unipam.spring.api.rest.controller;

import br.com.unipam.spring.api.rest.request.ProductRequest;
import br.com.unipam.spring.api.rest.response.ProductsResponse;
import br.com.unipam.spring.api.rest.service.ProductsService;
import br.com.unipam.spring.api.rest.service.util.SortEnum;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class ProductsController {
    @Autowired
    ProductsService productsService;
    private static final String PATH = "/products";
    private static final String API_VERSION_ONE = "/v1";
    private static final String API_VERSION_TWO = "/v2";

    @RequestMapping(path = API_VERSION_TWO + PATH, method = RequestMethod.GET)
    public List<ProductsResponse> getProductsWithDateAfterActualDate(@RequestParam(required = false) SortEnum sort, @RequestParam Integer maxDays) {
        return productsService.getValidProducts(sort, maxDays);
    }

    @RequestMapping(path = API_VERSION_ONE + PATH, method = RequestMethod.GET)
    public List<ProductsResponse> getProducts(@RequestParam(required = false) SortEnum sort) {
        return productsService.getProducts(sort);
    }

    @RequestMapping(path = API_VERSION_ONE + PATH, method = RequestMethod.POST)
    public void createProduct(@RequestBody @Valid ProductRequest request) {
        productsService.saveProduct(request);
    }

    @RequestMapping(path = API_VERSION_ONE + PATH + "/{id}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest request) throws NotFoundException {
        productsService.updateProduct(id, request);
    }

    @RequestMapping(path = API_VERSION_ONE + PATH + "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
    }
}
