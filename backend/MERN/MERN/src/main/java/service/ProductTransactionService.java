package service;

import model.ProductTransaction;
import repository.ProductTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProductTransactionService {

    @Autowired
    private ProductTransactionRepository repository;

    public void fetchAndSeedData() {
        // Mock implementation, should fetch from the provided API and save to database
        // String url = "https://s3.amazonaws.com/roxiler.com/product_transaction.json";
        // Use RestTemplate or similar to fetch the data

        List<ProductTransaction> transactions = new ArrayList<>();
        // Populate transactions list with data fetched from the API
        // For now, adding a dummy transaction
        transactions.add(new ProductTransaction(null, "Sample Product", "Description", 99.99, new Date(), true, "Category A"));

        repository.saveAll(transactions);
    }

    public List<ProductTransaction> getTransactions(String month, String search, int page, int perPage) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date startDate = sdf.parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();

        if (search.isEmpty()) {
            return repository.findByDateOfSaleBetween(startDate, endDate);
        } else {
            return repository.findByDateOfSaleBetweenAndTitleContainingOrDescriptionContainingOrPriceBetween(
                    startDate, endDate, search, search, 0.0, Double.MAX_VALUE);
        }
    }

    public Map<String, Object> getStatistics(String month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date startDate = sdf.parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();

        Double totalSaleAmount = repository.findTotalSaleAmount(startDate, endDate);
        Long totalSoldItems = repository.findTotalSoldItems(startDate, endDate);
        Long totalNotSoldItems = repository.findTotalNotSoldItems(startDate, endDate);

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalSaleAmount", totalSaleAmount);
        stats.put("totalSoldItems", totalSoldItems);
        stats.put("totalNotSoldItems", totalNotSoldItems);

        return stats;
    }

    public List<Map<String, Object>> getBarChart(String month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date startDate = sdf.parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();

        List<Map<String, Object>> barChart = new ArrayList<>();

        int[] ranges = {0, 100, 200, 300, 400, 500, 600, 700, 800, 900, Integer.MAX_VALUE};
        for (int i = 0; i < ranges.length - 1; i++) {
            int lower = ranges[i];
            int upper = ranges[i + 1];

            Long count = repository.countByPriceBetweenAndDateOfSaleBetween(lower, upper, startDate, endDate);
            Map<String, Object> rangeData = new HashMap<>();
            rangeData.put("priceRange", lower + " - " + (upper == Integer.MAX_VALUE ? "above" : upper));
            rangeData.put("count", count);
            barChart.add(rangeData);
        }

        return barChart;
    }

    public List<Map<String, Object>> getPieChart(String month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date startDate = sdf.parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();

        List<Object[]> categoryCounts = repository.findCategoryCounts(startDate, endDate);
        List<Map<String, Object>> pieChart = new ArrayList<>();
        for (Object[] categoryCount : categoryCounts) {
            Map<String, Object> data = new HashMap<>();
            data.put("category", categoryCount[0]);
            data.put("count", categoryCount[1]);
            pieChart.add(data);
        }

        return pieChart;
    }
}
