package ee.kaido.assignment.controller;

import ee.kaido.assignment.model.Stock;
import ee.kaido.assignment.model.reports.MoneySpent;
import ee.kaido.assignment.model.reports.MonthlyGroupedByShare;
import ee.kaido.assignment.repository.StockRepository;
import ee.kaido.assignment.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    StockRepository stockRepository;

    //Total spent money
    @GetMapping("/money-spent")
    public ResponseEntity<List<MoneySpent>> moneySpentReport() {
        return ResponseEntity.ok().body(reportService.getMoneySpentByStream(null));
    }

    //Total spent money by employee
    @GetMapping("/money-spent/{employeeId}")
    public ResponseEntity<List<MoneySpent>> moneySpentByEmployeeReport(@PathVariable int employeeId) {
        return ResponseEntity.ok().body(reportService.getMoneySpentByStream(employeeId));
    }

    // Get the stock list for month (yyyy-MM)
    @GetMapping("/month-list/{yearMonth}")
    public ResponseEntity<List<Stock>> getMonthlyStatistics(@PathVariable String yearMonth) {
        LocalDate startDate = LocalDate.parse(yearMonth + "-01");
        LocalDate endDate = startDate.plusMonths(1);
        return ResponseEntity.ok().body(stockRepository.findAllByDateAfterAndDateBefore(
                Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        ));
    }

    // Get the stock list for month (yyyy-MM) by employee
    @GetMapping("/month-list/{month}/{employee}")
    public ResponseEntity<List<Stock>> getMonthlyStatistics(@PathVariable String month, @PathVariable int employee) {
        return ResponseEntity.ok().body(stockRepository.findAllByDateContainsAndEmployeeIdEquals(month, employee));
    }

    // Get the monthly overview
    @GetMapping("/monthly")
    public ResponseEntity<Collection<MonthlyGroupedByShare>> getMonthlyReportForShares() {
        return ResponseEntity.ok().body(stockRepository.findAllGroupedByDateAndShare(null));
    }

    // Get the monthly overview by employee
    @GetMapping("/monthly/{employeeId}")
    public ResponseEntity<Collection<MonthlyGroupedByShare>> getMonthlyReportForShares(@PathVariable Long employeeId) {
        return ResponseEntity.ok().body(stockRepository.findAllGroupedByDateAndShare(employeeId));
    }
}
