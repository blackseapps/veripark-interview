package interview.veripark.com.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mertKaradeniz on 7.11.2021
 * <p>
 * This is an interview project.
 */

public class DetailResponse {

    @SerializedName("isDown")
    @Expose
    private boolean isDown;

    @SerializedName("isUp")
    @Expose
    private boolean isUp;

    @SerializedName("bid")
    @Expose
    private double bid;

    @SerializedName("channge")
    @Expose
    private double channge;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("difference")
    @Expose
    private double difference;

    @SerializedName("offer")
    @Expose
    private double offer;

    @SerializedName("highest")
    @Expose
    private double highest;

    @SerializedName("lowest")
    @Expose
    private double lowest;

    @SerializedName("maximum")
    @Expose
    private double maximum;

    @SerializedName("minimum")
    @Expose
    private double minimum;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("volume")
    @Expose
    private double volume;

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("graphicData")
    @Expose
    private List<GraphicDatum> graphicData = null;

    @SerializedName("status")
    @Expose
    private Status status;

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

    public double getChannge() {
        return channge;
    }

    public void setChannge(double channge) {
        this.channge = channge;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public double getHighest() {
        return highest;
    }

    public void setHighest(double highest) {
        this.highest = highest;
    }

    public double getLowest() {
        return lowest;
    }

    public void setLowest(double lowest) {
        this.lowest = lowest;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
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

    public List<GraphicDatum> getGraphicData() {
        return graphicData;
    }

    public void setGraphicData(List<GraphicDatum> graphicData) {
        this.graphicData = graphicData;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public class GraphicDatum {

        @SerializedName("day")
        @Expose
        private int day;

        @SerializedName("value")
        @Expose
        private float value;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

    }
}
