package org.juheinz.main;


import org.juheinz.navigation.RouteProvider;
import org.juheinz.utility.RandomDataGenerator;

public class Main {
    public static void main(String[] args) {

        double[] a = RandomDataGenerator.generateRandomCoordinate();
        double[] b = RandomDataGenerator.generateRandomCoordinate();

        System.out.println(a[0] +" " + a[1]);
        System.out.println(b[0] +" " + b[1]);

        RouteProvider.getRoute(a, b);

       //DeliverySimulator deliverySimulator = new DeliverySimulator();
       //deliverySimulator.startSimulation();

    }
}