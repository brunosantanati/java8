package me.brunosantana.java8.datetimeapi.instant;

import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Order{
    private String product;
    private Long orderDateTime;

    public Order(String product, Long orderDateTime) {
        this.product = product;
        this.orderDateTime = orderDateTime;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Long orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", orderDateTime=" + orderDateTime +
                '}';
    }
}

public class InstantExemplo {

    public static void main(String[] args) {

        Order order1 = new Order("chinelo", Instant.now().toEpochMilli());
        Order order2 = new Order("mouse", Instant.now().minus(23, ChronoUnit.HOURS).toEpochMilli());
        Order order3 = new Order("laptop", Instant.now().minus(Period.ofDays(2)).toEpochMilli());
        Order order4 = new Order("tv", Instant.now().minus(24, ChronoUnit.HOURS).toEpochMilli());
        Order order5 = new Order("panela", Instant.now().minus(25, ChronoUnit.HOURS).toEpochMilli());
        Order order6 = new Order("prato", Instant.now().minus(1440, ChronoUnit.MINUTES).toEpochMilli());
        Order order7 = new Order("video-game", Instant.now().minus(1439, ChronoUnit.MINUTES).toEpochMilli());

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);

        Instant now = Instant.now();
        Instant twentyFourHoursAgo = Instant.now().minus(24, ChronoUnit.HOURS);

        List<Order> filteredOrders = orders.stream()
                .filter(order -> {
                    Instant orderInstant = Instant.ofEpochMilli(order.getOrderDateTime());
                    return (!orderInstant.isBefore(twentyFourHoursAgo)) && orderInstant.isBefore(now);
                })
                .collect(Collectors.toList());

        System.out.println(filteredOrders);

    }

}
