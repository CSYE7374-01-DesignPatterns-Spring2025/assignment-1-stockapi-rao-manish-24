package edu.neu.csye7374;

public interface Tradable0 extends Tradable {

    @Override
    default void setBid(String bid) {
        System.out.println("Default bid settings: " + bid);
    }

    @Override
    default String getMetric() {

        return "0.0";
    }
}