package edu.neu.csye7374;

public abstract class StockAPI implements Tradable {
    private String id;
    private double price;
    private String description;
    private double previousPrice;
    protected double[] priceHistory = new double[6];
    protected int bidCount = 0;

    protected StockAPI(String id, double price, String description) {
        this.id = id;
        this.price = price;
        this.previousPrice = price;
        this.description = description;
    }

    @Override
    public void setBid(String bid) {
        if (bidCount < 6) {
            previousPrice = price;
            price = Double.parseDouble(bid);
            priceHistory[bidCount++] = price;
        }
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public double getPreviousPrice() {
        return previousPrice;
    }

    @Override
    public String toString() {
        return "Stock[" +
                "id='" + id + '\'' +
                ", price=" + String.format("%.2f", price) +
                ", description='" + description + '\'' +
                ", metric=" + getMetric() +
                ']';
    }
} 