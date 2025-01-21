package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class TeslaStock extends Stock implements Tradable1{
    private List<Double> bids = new ArrayList<>();

    public TeslaStock() {
        this.setName("TSLA");
        this.setDescription("Tesla vehicle stock");
        this.setPrice(164.49);
    }

    @Override
    public String getMetric() {
        if (bids.isEmpty()) {
            return "No valid bids yet.";
        }
        
        double price = getPrice();
        double midpoint = (bids.get(bids.size()-1) + price) / 2;
        double newMetric = (((bids.get(bids.size() - 1) - price) / midpoint) * 100);
        return String.valueOf(newMetric);
    }

    @Override
    public void setBid(String bid) {
        if (bid == null || bid.isEmpty()) {
            System.out.println("Invalid bid, no update made.");
            return;
        }

        try {
            double newBid = Double.parseDouble(bid);
            bids.add(newBid);
            super.setBid(bid); 

           
            double currentPrice = this.getPrice();
            double newPrice = currentPrice + (newBid - currentPrice) * 0.08; 
            this.setPrice(newPrice);

        } catch (NumberFormatException e) {
            System.out.println("Invalid bid format, provide numeric value.");
        }
    }
}
