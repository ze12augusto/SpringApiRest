package br.com.unipam.spring.api.rest.repository;

import br.com.unipam.spring.api.rest.domain.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAll(Sort sort);

    List<Product> findAllByDateBetween(@Temporal(TemporalType.TIMESTAMP) Date startDate, @Temporal(TemporalType.TIMESTAMP) Date endDate, Sort sort);
}
