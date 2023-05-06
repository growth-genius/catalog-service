package com.sgyj.catalogservice.service;


import com.sgyj.catalogservice.entity.Catalog;

public interface CatalogService {

    Iterable<Catalog> getAllCatalogs();

}
