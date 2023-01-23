package ee.kaido.assignment.controller;

import ee.kaido.assignment.controller.exception.PropertyValueException;
import ee.kaido.assignment.model.Stock;
import ee.kaido.assignment.repository.StockRepository;
import ee.kaido.assignment.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    StockService stockService;

    @PostMapping
    public ResponseEntity<String> addStock(@RequestBody Stock stock) throws PropertyValueException {
        try {
            stockService.goThruListOfStocks(new ArrayList<>(Arrays.asList(stock)));
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            throw new PropertyValueException();
        }
    }

    @PostMapping("/list")
    public ResponseEntity<String> addStockList(@RequestBody List<Stock> stocks) throws PropertyValueException {
        try {
            stockRepository.saveAll(stockService.goThruListOfStocks(stocks));
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            throw new PropertyValueException();
        }
    }
}
