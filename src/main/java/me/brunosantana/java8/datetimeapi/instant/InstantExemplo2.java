package me.brunosantana.java8.datetimeapi.instant;

import java.time.Instant;
import java.time.ZoneId;

public class InstantExemplo2 {

    public static void main(String[] args) {

        Instant now = Instant.now();
        long nowMilli = now.toEpochMilli();
        long nowSeconds = (now.toEpochMilli() / 1000);

        System.out.println(nowMilli);
        System.out.println(nowSeconds);

        System.out.println(Instant.ofEpochMilli(nowMilli).atZone(ZoneId.systemDefault()).toLocalDateTime());
        System.out.println(Instant.ofEpochSecond(nowSeconds).atZone(ZoneId.systemDefault()).toLocalDateTime());
        System.out.println(Instant.ofEpochSecond(nowMilli).atZone(ZoneId.systemDefault()).toLocalDateTime()); //errado
    }

}
