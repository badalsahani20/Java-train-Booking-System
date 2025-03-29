package Entities;

public class Train {
    private int trainId;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private double price;

    public Train(int trainId, String trainName, String source, String destination, int totalSeats, int availableSeats,double price){
        this.trainId = trainId;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public Train(){};

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Train ID:" + trainId +
                "Train Name:" + trainName +
                "(" + source + "->" +
                destination + ")" +
                "\nSeats: " + availableSeats +
                "/" + totalSeats +
                ", Price: ₹" + price;
    }
}
