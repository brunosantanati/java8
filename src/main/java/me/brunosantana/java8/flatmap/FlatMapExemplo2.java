package me.brunosantana.java8.flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Campaign{

    private String externalId;
    private List<String> commerceItems;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<String> getCommerceItems() {
        return commerceItems;
    }

    public void setCommerceItems(List<String> commerceItems) {
        this.commerceItems = commerceItems;
    }
}

public class FlatMapExemplo2 {

    public static void main(String[] args) {
        Campaign c1 = new Campaign();
        c1.setExternalId("5000021");
        c1.setCommerceItems(Arrays.asList("sku1001", "sku1002"));

        Campaign c2 = new Campaign();
        c2.setExternalId("6000021");
        c2.setCommerceItems(Arrays.asList("sku2001", "sku2002"));

        List<Campaign> campaigns = Arrays.asList(c1, c2);

        // Using flatMap
        List<String> skus = campaigns.stream()
                .flatMap(c -> c.getCommerceItems().stream())
                .collect(Collectors.toList());

        System.out.println(skus);

        // Using forEach
        List<String> skus2 = new ArrayList<>();
        campaigns.forEach(c -> skus2.addAll(c.getCommerceItems()));

        System.out.println(skus2);
    }

}
