package com.company.Task1;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceWithoutPatterns {
    public static List<Product> filter(List<Product> products, Predicate<Product> predicate) {
        return products.stream().filter(predicate).collect(Collectors.toList());
    }
}
