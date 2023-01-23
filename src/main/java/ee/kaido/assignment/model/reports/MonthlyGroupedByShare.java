package ee.kaido.assignment.model.reports;

import java.math.BigDecimal;
import java.util.Date;

public interface MonthlyGroupedByShare {
    Date getDate();
    String getShare_name();
    String getCompany_name();
    String getIsin_code();
    String getCountry();
    String getEconomic_activity();
    BigDecimal getAverage_price();
    Integer getTotal_volume();
    BigDecimal getTotal_price();
}
