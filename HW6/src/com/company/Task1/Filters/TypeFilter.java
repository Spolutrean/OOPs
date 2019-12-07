package com.company.Task1.Filters;

import com.company.Task1.IFilter;
import com.company.Task1.Product;

import java.util.ArrayList;
import java.util.List;

public class TypeFilter implements IFilter {
    private Product.Type type;

    public TypeFilter(Product.Type type) {
        this.type = type;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> ans = new ArrayList<>();
        for(Product product : products) {
            if(product.type.equals(type)) {
                ans.add(product);
            }
        }

        return ans;
    }
}
