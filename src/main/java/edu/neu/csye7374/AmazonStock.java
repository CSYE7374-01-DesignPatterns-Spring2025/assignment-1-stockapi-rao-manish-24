package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class AmazonStock extends Stock{
    private List<Double> bids = new ArrayList<>();
    private double highestBid = 0.0;

    public AmazonStock() {
        this.setName("AMAZON");
        this.setDescription("Shopping");
        this.setPrice(3200.50);
    }

    @Override
    public String getMetric() {
        if (bids.isEmpty()) {
            return "No valid bids yet.";
        }
        double newMetric = ((highestBid - this.getPrice()) / this.getPrice()) * 100;
        return String.format("%.2f", newMetric);
    }

    @Override
    public void setBid(String bid) {
        if (bid == null || bid.isEmpty()) {
            System.out.println("Invalid bid, no update made.");
            return;
        }

        try {
        	double currentBid = Double.parseDouble(bid);
            bids.add(currentBid);
            highestBid = Math.max(highestBid, currentBid);

           
            double currentPrice = this.getPrice();
            double newPrice = currentPrice + (highestBid - currentPrice) * 0.05;  
            this.setPrice(newPrice);
            super.setBid(bid);
        } catch (NumberFormatException e) {
            System.out.println("Invalid bid format, please provide a numeric value.");
        }
    }
}