package com.sgyj.catalogservice.repository;

import com.sgyj.catalogservice.entity.Catalog;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findByProductId(String productId);

}
