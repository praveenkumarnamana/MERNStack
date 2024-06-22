package controller;

import model.ProductTransaction;
import service.ProductTransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/api")
//public class TransactionController {
//
//    @Autowired
//    private ProductTransactionService service;
//
//    @GetMapping("/initialize_db")
//    public String initializeDb() {
//        service.fetchAndSeedData();
//        return "Database initialized with seed data";
//    }
//
//    @GetMapping("/transactions")
//    public List<ProductTransaction> getTransactions(
//            @RequestParam String month,
//            @RequestParam(required = false, defaultValue = "") String search,
//            @RequestParam(required = false, defaultValue = "1") int page,
//            @RequestParam(required = false, defaultValue = "10") int perPage) throws ParseException {
//        return service.getTransactions(month, search, page, perPage);
//    }
//
//    @GetMapping("/statistics")
//    public Map<String, Object> getStatistics(@RequestParam String month) throws ParseException {
//        return service.getStatistics(month);
//    }
//
//    @GetMapping("/bar_chart")
//    public List<Map<String, Object>> getBarChart(@RequestParam String month) throws ParseException {
//        return service.getBarChart(month);
//    }
//
//    @GetMapping("/pie_chart")
//    public List<Map<String, Object>> getPieChart(@RequestParam String month) throws ParseException {
//        return service.getPieChart(month);
//    }
//
//    @GetMapping("/combined_data")
//    public Map<String, Object> getCombinedData(@RequestParam String month) throws ParseException {
//        Map<String, Object> combinedData = new HashMap<>();
//        combinedData.put("transactions", getTransactions(month, "", 1, 10));
//        combinedData.put("statistics", getStatistics(month));
//        combinedData.put("barChart", getBarChart(month));
//        combinedData.put("pieChart", getPieChart(month));
//        return combinedData;
//    }
//}
//
@RestController
@RequestMapping("/api")
public class TransactionController<BarChartData, PieChartData> {

    @Autowired
    private ProductTransactionService proudcttransactionService;

    @GetMapping("/transactions")
    public List<ProductTransaction> getTransactions(
            @RequestParam String month,
            @RequestParam String search,
            @RequestParam int page,
            @RequestParam int perPage) throws ParseException {
        // Implement the logic to fetch transactions
        return proudcttransactionService.getTransactions(month, search, page, perPage);
    }

    @GetMapping("/statistics")
    public Map<String, Object> getStatistics(@RequestParam String month) throws ParseException {
        // Implement the logic to fetch statistics
        return proudcttransactionService.getStatistics(month);
    }

    @GetMapping("/bar_chart")
    public List<Map<String, Object>> getBarChartData(@RequestParam String month) throws ParseException {
        // Implement the logic to fetch bar chart data
        return proudcttransactionService.getBarChart(month);
    }

    @GetMapping("/pie_chart")
    public List<Map<String, Object>> getPieChartData(@RequestParam String month) throws ParseException {
        // Implement the logic to fetch pie chart data
        return proudcttransactionService.getPieChart(month);
    }

	public ProductTransactionService getProudcttransactionService() {
		return proudcttransactionService;
	}

	public void setProudcttransactionService(ProductTransactionService proudcttransactionService) {
		this.proudcttransactionService = proudcttransactionService;
	}
}
