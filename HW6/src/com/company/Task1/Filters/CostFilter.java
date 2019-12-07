package com.company.Task1.Filters;

import com.company.Task1.IFilter;
import com.company.Task1.Product;

import java.util.ArrayList;
import java.util.List;

public class CostFilter implements IFilter {
    int minValue, maxValue;

    public CostFilter(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> ans = new ArrayList<>();
        for(Product product : products) {
            if(product.cost >= minValue && product.cost <= maxValue) {
                ans.add(product);
            }
        }

        return ans;
    }
}
