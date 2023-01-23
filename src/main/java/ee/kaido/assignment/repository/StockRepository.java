package ee.kaido.assignment.repository;

import ee.kaido.assignment.model.Stock;
import ee.kaido.assignment.model.reports.MonthlyGroupedByShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAllByEmployeeId(Integer employeeId);

    List<Stock> findAllByDateAfterAndDateBefore(Date date, Date endDate);

    List<Stock> findAllByDateContainsAndEmployeeIdEquals(String date, int employeeId);

    @Query(value = "SELECT " +
            "substring(date,1,7) as date, " +
            "share_name," +
            "company_name," +
            "isin_code," +
            "country," +
            "economic_activity," +
            "ROUND(AVG(price),4) as average_price, " +
            "sum(volume) as total_volume, " +
            "ROUND(sum(total_price),2) as total_price " +
            "FROM Stock " +
            "INNER JOIN Share ON Stock.share_id=Share.id " +
            "WHERE (:employee IS NULL or employee_id = :employee) " +
            "GROUP BY substring(date,1,7), share_id",
            nativeQuery = true)
    Collection<MonthlyGroupedByShare> findAllGroupedByDateAndShare(@Param("employee") Long employee);
}
