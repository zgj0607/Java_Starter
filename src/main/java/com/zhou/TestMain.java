package com.zhou;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestMain {

    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
        car.introduce();
    }

    private static int testOut() {
        System.out.println("Hello World !");
        return 123;
    }

    private static Car initByDefaultConst() throws Throwable {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("Car");
        Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
        Car car = (Car) cons.newInstance();
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "  CA72  ");

        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "  ");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 200);

        Field brand = clazz.getDeclaredField("brand");
        brand.setAccessible(true);
        brand.set(car, "Benz");
        return car;
    }
}