package com.zhou;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestMain {

    public static void main(String[] args) throws Throwable {


        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world_x", "root", "491001coM.");
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            System.out.print("yes");
            Statement stmt = con.createStatement();
            String sql = "select * from city order by countrycode desc, name asc";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "_" + rs.getString(2));
            }
        } catch (Exception e) {
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }


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