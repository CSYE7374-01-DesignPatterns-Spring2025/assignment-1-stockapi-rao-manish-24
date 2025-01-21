package edu.neu.csye7374;

public class TechStock extends StockAPI {
    private static TechStock instance;
    
    private TechStock(String id, double price, String description) {
        super(id, price, description);
    }
    
    public static TechStock getInstance() {
        if (instance == null) {
            instance = new TechStock("TSLA", 180.0, "Tesla Inc Common Stock");
        }
        return instance;
    }

    @Override
    public int getMetric() {
        if (bidCount < 2) return 0;
        
        double priceChange = getPrice() - getPreviousPrice();
        double volatility = 0;
        
        for (int i = 1; i < bidCount; i++) {
            volatility += Math.abs(priceHistory[i] - priceHistory[i-1]);
        }
        
        return (int)((priceChange * 10) - (volatility * 2));
    }
} 