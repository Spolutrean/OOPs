package com.company.Task1;

import java.util.ArrayList;
import java.util.List;

public class ServiceWithStrategy {
    private List<IFilter> filters = new ArrayList<>();
    public void addFilter(IFilter filter) {
        filters.add(filter);
    }
    public List<Product> filter(List<Product> products) {
        List<Product> ans = new ArrayList<>(products);
        for(IFilter IFilter : filters) {
            ans = IFilter.filter(ans);
        }
        return ans;
    }
}
