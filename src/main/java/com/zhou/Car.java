package com.zhou;

class Car {
    private String brand;
    private String color;
    private int maxSpeed;

    public Car() {
    }

    public Car(String brand, String color, int maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    void introduce() {
        System.out.println("brand:" + this.brand + ";color:" + this.color + ";maxSpeed:" + this.maxSpeed);
    }

    public String getBrand() {
        return this.brand;
    }

    public String getColor() {
        return this.color;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
