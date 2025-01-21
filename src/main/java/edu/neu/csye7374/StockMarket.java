package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StockMarket {
    private List<Tradable> stocks;
    private static StockMarket instance = null;

    // Private constructor for Singleton pattern
    private StockMarket() {
        stocks = new ArrayList<>();
    }

    // Lazy initialization of Singleton instance
    public static synchronized StockMarket getInstance() {
        if (instance == null) {
            instance = new StockMarket();
        }
        return instance;
    }

   
    public void addStock(Stock stock) {
        System.out.println("Stock " + stock.getName() + " has been added to the stock market.");
        this.stocks.add(stock);
    }

  
    public void removeStock(String stockName) {
        Iterator<Tradable> iterator = stocks.iterator();
        while (iterator.hasNext()) {
            Stock stock = (Stock) iterator.next();
            if (stock.getName().equalsIgnoreCase(stockName)) {
                iterator.remove();
                System.out.println("Stock " + stockName + " has been removed from the stock market.");
            }
        }
    }

   
    public void showAllStocks() {
        System.out.println("-------------------------------------------");
        System.out.println("       Stocks Currently in the Market       ");
        System.out.println("-------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %n", "Stock Name", "Price", "Description");
        System.out.println("===========================================");

        for (Tradable tradable : this.stocks) {
            if (tradable instanceof Stock) {
                Stock stock = (Stock) tradable;
                System.out.printf("%-15s $%-14.2f %-15s %n", 
                    stock.getName(), 
                    stock.getPrice(), 
                    stock.getDescription()
                );
            }
        }
    }



  
    public void showStock(String name) {
        for (Tradable tradable : stocks) {
            Stock stock = (Stock) tradable;
            if (stock.getName().equalsIgnoreCase(name)) {
                System.out.println(stock);
            }
        }
    }

    // Trade stock by placing a bid
    public void tradeStock(String stockName, String bid) {
        System.out.println("Trading " + stockName + " stock for a bid of $" + bid + ".");
        for (Tradable tradable : stocks) {
            Stock stock = (Stock) tradable;
            if (stock.getName().equalsIgnoreCase(stockName)) {
                stock.setBid(bid);
            }
        }
    }
    
    
 
    public void executeMarketOperations() {
        Stock amazon = new AmazonStock();
        Stock tesla = new TeslaStock();
        Stock google = new GoogleStock(); 

        this.addStock(amazon);
        this.addStock(tesla);
        this.addStock(google);

        // Trade Amazon stock with multiple bids
        String[] bids2 = {"3610.00", "3635.50", "3650.00", "3700.00", "3720.00", "3750.00"};
        System.out.println("\n============Amazon Stock===================\n");
        for (String bid : bids2) {
            this.tradeStock("AMAZON", bid);
            this.showStock("amazon");
        }
        System.out.println("============End of Amazon Stock===================\n");

        // Trade tesla stock with multiple bids
        String[] bids3 = {"162.22", "151.34", "177.14", "166.32", "162.22", "149.22"};
        System.out.println("\n============Tesla Stock===================\n");
        for (String bid : bids3) {
            this.tradeStock("TSLA", bid);
            this.showStock("tesla");
        }
        System.out.println("============End of TESLA Stock===================\n");
        
        // Trade Google stock with multiple bids
        String[] bids5 = {"2900.00", "2950.00", "3000.00", "3050.00", "3100.00", "3150.00"};
        System.out.println("\n============Google Stock===================\n");
        for (String bid : bids5) {
            this.tradeStock("GOOGLE", bid);
            this.showStock("GOOGLE");
        }
        System.out.println("============End of Google Stock===================\n");
        
        System.out.println("============Display All Stocks in Market===================\n");
        this.showAllStocks();
        System.out.println("\n");
        System.out.println("====================================================\n");
    }

    public static void demo() {
        StockMarket stockMarket = StockMarket.getInstance();
        stockMarket.executeMarketOperations();
    }
}
