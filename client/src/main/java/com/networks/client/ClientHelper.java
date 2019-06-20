package com.networks.client;

import java.util.Scanner;

public class ClientHelper {
    public static String clientAddress;
    public static Integer clientPort;

    public static String getClientAddress() {

        System.out.println("insert address");
        Scanner scanner = new Scanner( System. in);
        clientAddress = scanner. nextLine();
        return clientAddress;
    }
    public static Integer getClientPort() {
        Scanner scanner = new Scanner( System. in);
        System.out.println("insert port")   ;
        clientPort = scanner.nextInt();
        return clientPort;
    }

}
