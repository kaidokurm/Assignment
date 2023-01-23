package ee.kaido.assignment.service;

import ee.kaido.assignment.model.Stock;
import ee.kaido.assignment.model.reports.MoneySpent;
import ee.kaido.assignment.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    StockRepository stockRepository;

    public List<MoneySpent> getMoneySpentByStream(Integer employeeId) {
        List<Stock> stocks = employeeId == null ? stockRepository.findAll() : stockRepository.findAllByEmployeeId(employeeId);
        return stocks
                .parallelStream()
                .map(
                        report -> new MoneySpent(
                                report.getDate().toString().substring(0, 7),
                                report.getPrice().multiply(
                                        BigDecimal.valueOf(report.getVolume())
                                )
                        )
                )
                .collect(Collectors.toMap(
                        sum -> sum.getMonth(),
                        Function.identity(),
                        (sum1, sum2) -> new MoneySpent(
                                sum1.getMonth(),
                                sum1.getMoney().add(sum2.getMoney())
                        )
                ))
                .values()
                .stream()
                .sorted(Comparator.comparing(MoneySpent::getMonth))
                .collect(Collectors.toList());
    }
}
