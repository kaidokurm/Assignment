package ee.kaido.assignment.service;

import ee.kaido.assignment.model.Share;
import ee.kaido.assignment.model.Stock;
import ee.kaido.assignment.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Autowired
    ShareRepository shareRepository;

    public List<Stock> goThruListOfStocks(List<Stock> stocks) {
        return stocks.stream()
                .map(stock -> {
                    //TODO check for date
                    stock.setTotalPrice(calculateTotal(stock));
                    stock.setShare(getShareFromDb(stock.getShare()));
                    return stock;

                })
                .collect(Collectors.toList());
    }

    public BigDecimal calculateTotal(Stock stock) {
        return stock.getPrice().multiply(BigDecimal.valueOf(stock.getVolume()));
    }

    public Share getShareFromDb(Share share) {
        if(ObjectUtils.isEmpty(shareRepository.findByIsinCodeEquals(share.getIsinCode()))) {
            Share newShare=shareRepository.save(share);
            return newShare;
        }
        return shareRepository.findByIsinCodeEquals(share.getIsinCode());
    }
}
