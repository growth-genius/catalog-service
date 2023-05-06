package com.sgyj.catalogservice.form;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sgyj.catalogservice.entity.Catalog;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {

    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private LocalDateTime createdAt;

    private ResponseCatalog() {}
    public static ResponseCatalog from(Catalog catalog) {
        ResponseCatalog responseCatalog = new ResponseCatalog();
        copyProperties(catalog, responseCatalog);
        return responseCatalog;
    }
}
