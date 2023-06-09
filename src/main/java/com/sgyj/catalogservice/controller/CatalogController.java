package com.sgyj.catalogservice.controller;

import static com.sgyj.catalogservice.utils.ApiUtil.success;

import com.sgyj.catalogservice.entity.Catalog;
import com.sgyj.catalogservice.form.ResponseCatalog;
import com.sgyj.catalogservice.service.CatalogService;
import com.sgyj.catalogservice.utils.ApiUtil.ApiResult;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {

    private final Environment environment;
    private final CatalogService catalogService;

    @GetMapping("/health_check")
    public String status() {
        return String.format( "It's Working in Catalog Service on PORT %s", environment.getProperty("local.server.port") );
    }

    @GetMapping("/catalogs")
    public ApiResult<List<ResponseCatalog>> getCatalogs() {
        Iterable<Catalog> allCatalogs = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = new ArrayList<>();
        allCatalogs.forEach( c -> result.add(ResponseCatalog.from( c )) );
        return success(result);
    }

}
