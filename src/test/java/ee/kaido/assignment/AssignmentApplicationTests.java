package ee.kaido.assignment;

import ee.kaido.assignment.model.Share;
import ee.kaido.assignment.model.Stock;
import ee.kaido.assignment.repository.StockRepository;
import ee.kaido.assignment.service.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class AssignmentApplicationTests {

    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockService stockService;

    @Test
    void contextLoads() {
    }

    @Test
    void checkIfStockAddedIsInRepository() {
        Stock stock = new Stock(1l, BigDecimal.valueOf(20.2022), 20, BigDecimal.valueOf(0), new Date(2022 - 01 - 01), 12345,
                new Share(1l,"Altria Group, Inc.","MO", "US02209S1033", "USA", "Food, Beverage & Tobacco"));
        stock.setShare(stockService.getShareFromDb(stock.getShare()));
        stock.setTotalPrice(stockService.calculateTotal(stock));

        Stock saveStock = stockRepository.save(stock);
        Stock fetchStock = stockRepository.findById(saveStock.getId()).get();
        Assertions.assertEquals(saveStock.getVolume(), fetchStock.getVolume());

    }

}
