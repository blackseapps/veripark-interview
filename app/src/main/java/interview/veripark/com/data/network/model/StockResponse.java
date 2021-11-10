package interview.veripark.com.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class StockResponse {

    @SerializedName("stocks")
    @Expose
    private List<Stock> stocks = null;

    @SerializedName("status")
    @Expose
    private Status status;

    public StockResponse(List<Stock> stocks, Status status) {
        this.stocks = stocks;
        this.status = status;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public class Stock {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("isDown")
        @Expose
        private boolean isDown;

        @SerializedName("isUp")
        @Expose
        private boolean isUp;

        @SerializedName("bid")
        @Expose
        private double bid;

        @SerializedName("difference")
        @Expose
        private double difference;

        @SerializedName("offer")
        @Expose
        private double offer;

        @SerializedName("price")
        @Expose
        private double price;

        @SerializedName("volume")
        @Expose
        private double volume;

        @SerializedName("symbol")
        @Expose
        private String symbol;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isDown() {
            return isDown;
        }

        public void setDown(boolean down) {
            isDown = down;
        }

        public boolean isUp() {
            return isUp;
        }

        public void setUp(boolean up) {
            isUp = up;
        }

        public double getBid() {
            return bid;
        }

        public void setBid(double bid) {
            this.bid = bid;
        }

        public double getDifference() {
            return difference;
        }

        public void setDifference(double difference) {
            this.difference = difference;
        }

        public double getOffer() {
            return offer;
        }

        public void setOffer(double offer) {
            this.offer = offer;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }
}