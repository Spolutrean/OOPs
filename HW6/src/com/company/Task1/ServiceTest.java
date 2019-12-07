package com.company.Task1;
import com.company.Task1.Filters.CostFilter;
import com.company.Task1.Filters.SizeFilter;
import com.company.Task1.Filters.TypeFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;


@RunWith(JUnit4.class)
public class ServiceTest {
    @Test
    public void findSingle() {
        Product tie = new Product(5, Product.Size.L, Product.Type.tie);
        Product jeans = new Product(45, Product.Size.M, Product.Type.jeans);
        Product shirt = new Product(20, Product.Size.S, Product.Type.shirt);

        List<Product> products = List.of(jeans, shirt, tie);
        ServiceWithStrategy tieService = new ServiceWithStrategy();
        tieService.addFilter(new CostFilter(4, 6));
        tieService.addFilter(new SizeFilter(Product.Size.L));
        tieService.addFilter(new TypeFilter(Product.Type.tie));
        Assert.assertEquals(List.of(tie), tieService.filter(products));
    }

    @Test
    public void findNone() {
        Product tie = new Product(5, Product.Size.L, Product.Type.tie);
        Product jeans = new Product(45, Product.Size.M, Product.Type.jeans);
        Product shirt = new Product(20, Product.Size.S, Product.Type.shirt);

        List<Product> products = List.of(jeans, shirt, tie);
        ServiceWithStrategy tieService = new ServiceWithStrategy();
        tieService.addFilter(new CostFilter(4, 4));
        Assert.assertEquals(List.of(), tieService.filter(products));
    }

    @Test
    public void findMany() {
        Product tie1 = new Product(5, Product.Size.L, Product.Type.tie);
        Product tie2 = new Product(10, Product.Size.L, Product.Type.tie);
        Product shirt = new Product(20, Product.Size.S, Product.Type.shirt);

        List<Product> products = List.of(tie1, shirt, tie2);
        ServiceWithStrategy tieService = new ServiceWithStrategy();
        tieService.addFilter(new CostFilter(4, 16));
        tieService.addFilter(new SizeFilter(Product.Size.L));
        tieService.addFilter(new TypeFilter(Product.Type.tie));
        Assert.assertEquals(List.of(tie1, tie2), tieService.filter(products));
    }
}