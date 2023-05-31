package com.example.SimpleComplex.Records;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class WeeklyUpdate {
    private int userId;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private LocalDate date;
    private int occupancy;
    private int prospects;
    private int sales;
    private double oneBedPrice;
    private double twoBedPrice;
    private double threeBedPrice;

    public WeeklyUpdate() {
    }

    public WeeklyUpdate(int userId, int id, LocalDate date, int occupancy, int prospects, int sales, double oneBedPrice, double twoBedPrice, double threeBedPrice) {
        this.userId = userId;
        this.id = id;
        this.date = date;
        this.occupancy = occupancy;
        this.prospects = prospects;
        this.sales = sales;
        this.oneBedPrice = oneBedPrice;
        this.twoBedPrice = twoBedPrice;
        this.threeBedPrice = threeBedPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getProspects() {
        return prospects;
    }

    public void setProspects(int prospects) {
        this.prospects = prospects;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public double getOneBedPrice() {
        return oneBedPrice;
    }

    public void setOneBedPrice(double oneBedPrice) {
        this.oneBedPrice = oneBedPrice;
    }

    public double getTwoBedPrice() {
        return twoBedPrice;
    }

    public void setTwoBedPrice(double twoBedPrice) {
        this.twoBedPrice = twoBedPrice;
    }

    public double getThreeBedPrice() {
        return threeBedPrice;
    }

    public void setThreeBedPrice(double threeBedPrice) {
        this.threeBedPrice = threeBedPrice;
    }
}
