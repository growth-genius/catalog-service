package com.sgyj.catalogservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgyj.catalogservice.dto.OrderDto;
import com.sgyj.catalogservice.entity.Catalog;
import com.sgyj.catalogservice.repository.CatalogRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;
    private final ObjectMapper objectMapper;
    @Transactional
    @KafkaListener(topics="example-order-topic")
    public void processMessage(String kafkaMessage) throws JsonProcessingException {
        log.info("Kafka Message: ===> " + kafkaMessage);
        OrderDto orderDto = objectMapper.readValue(kafkaMessage, OrderDto.class);
        Optional<Catalog> byProductId = catalogRepository.findByProductId(orderDto.getProductId());
        byProductId.ifPresent(catalog -> catalog.minusStockByQty(orderDto.getQty()));
    }
}
