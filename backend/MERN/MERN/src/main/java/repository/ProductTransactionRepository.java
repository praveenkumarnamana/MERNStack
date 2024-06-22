package repository;

import java.util.Date;
import java.util.List;

import org.hibernate.stat.Statistics;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.ProductTransaction;

@Repository
public interface ProductTransactionRepository<PieChartData, BarChartData> extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.dateOfSale LIKE :month% AND t.title LIKE %:search%")
    Page<Transaction> findTransactionsByMonthAndSearch(@Param("month") String month, @Param("search") String search, Pageable pageable);

    @Query("SELECT new com.example.Statistics(SUM(t.price), COUNT(t), SUM(CASE WHEN t.sold = true THEN 1 ELSE 0 END), SUM(CASE WHEN t.sold = false THEN 1 ELSE 0 END)) FROM Transaction t WHERE t.dateOfSale LIKE :month%")
    Statistics findStatisticsByMonth(@Param("month") String month);

    @Query("SELECT new com.example.BarChartData(t.priceRange, COUNT(t)) FROM Transaction t WHERE t.dateOfSale LIKE :month% GROUP BY t.priceRange")
    List<BarChartData> findBarChartDataByMonth(@Param("month") String month);

    @Query("SELECT new com.example.PieChartData(t.category, COUNT(t)) FROM Transaction t WHERE t.dateOfSale LIKE :month% GROUP BY t.category")
    List<PieChartData> findPieChartDataByMonth(@Param("month") String month);

	Long countByPriceBetweenAndDateOfSaleBetween(int lower, int upper, Date startDate, Date endDate);

	List<Object[]> findCategoryCounts(Date startDate, Date endDate);

	Double findTotalSaleAmount(Date startDate, Date endDate);

	Long findTotalSoldItems(Date startDate, Date endDate);

	Long findTotalNotSoldItems(Date startDate, Date endDate);

	List<ProductTransaction> findByDateOfSaleBetween(Date startDate, Date endDate);

	List<ProductTransaction> findByDateOfSaleBetweenAndTitleContainingOrDescriptionContainingOrPriceBetween(
			Date startDate, Date endDate, String search, String search2, double d, double maxValue);
}

