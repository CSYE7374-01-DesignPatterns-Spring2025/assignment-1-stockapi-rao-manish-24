package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class GoogleStock extends Stock {
    private List<Double> bids = new ArrayList<>();
    private double bidInfluenceFactor;

    public GoogleStock() {
        this.setName("GOOGLE");
        this.setDescription("Google Stocks");
        this.setPrice(2800.00);
        this.bidInfluenceFactor = 0.05; 
    }

    @Override
    public void setBid(String bid) {
        if (bid == null || bid.isEmpty()) {
            System.out.println("Invalid bid, no update made.");
            return;
        }

        double currentBid = Double.parseDouble(bid);
        bids.add(currentBid);
        super.setBid(bid);

        double currentPrice = this.getPrice();
        double newPrice = currentPrice + (currentBid - currentPrice) * bidInfluenceFactor;
        this.setPrice(Math.round(newPrice * 100) / 100.0); 

    }

    @Override
    public String getMetric() {
        if (bids.isEmpty()) {
            return "No valid bids yet.";
        }
        double price = getPrice();
        double currentMetric = ((bids.get(bids.size() - 1) - price) / price) * 100;
        return String.format("%.2f", currentMetric);
    }
}
