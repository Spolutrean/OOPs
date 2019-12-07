package com.company.Task1.Filters;

import com.company.Task1.IFilter;
import com.company.Task1.Product;

import java.util.ArrayList;
import java.util.List;

public class SizeFilter implements IFilter {
    private Product.Size size;

    public SizeFilter(Product.Size size) {
        this.size = size;
    }

    public SizeFilter(int integerSize) {
        if(integerSize <= 47) {
            this.size = Product.Size.S;
        } else if(integerSize >= 50) {
            this.size = Product.Size.L;
        } else {
            this.size = Product.Size.M;
        }
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> ans = new ArrayList<>();
        for(Product product : products) {
            if(product.size.equals(size)) {
                ans.add(product);
            }
        }

        return ans;
    }
}
