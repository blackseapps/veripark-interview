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
        private Integer id;

        @SerializedName("isDown")
        @Expose
        private Boolean isDown;

        @SerializedName("isUp")
        @Expose
        private Boolean isUp;

        @SerializedName("bid")
        @Expose
        private Double bid;

        @SerializedName("difference")
        @Expose
        private Double difference;

        @SerializedName("offer")
        @Expose
        private Double offer;

        @SerializedName("price")
        @Expose
        private Double price;

        @SerializedName("volume")
        @Expose
        private Double volume;

        @SerializedName("symbol")
        @Expose
        private String symbol;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getIsDown() {
            return isDown;
        }

        public void setIsDown(Boolean isDown) {
            this.isDown = isDown;
        }

        public Boolean getIsUp() {
            return isUp;
        }

        public void setIsUp(Boolean isUp) {
            this.isUp = isUp;
        }

        public Double getBid() {
            return bid;
        }

        public void setBid(Double bid) {
            this.bid = bid;
        }

        public Double getDifference() {
            return difference;
        }

        public void setDifference(Double difference) {
            this.difference = difference;
        }

        public Double getOffer() {
            return offer;
        }

        public void setOffer(Double offer) {
            this.offer = offer;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getVolume() {
            return volume;
        }

        public void setVolume(Double volume) {
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