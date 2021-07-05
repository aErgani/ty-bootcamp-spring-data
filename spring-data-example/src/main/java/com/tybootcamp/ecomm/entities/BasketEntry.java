package com.tybootcamp.ecomm.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "basketEntry")
public class BasketEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    @Min(value = 1)
    private int count;

    @ManyToOne
    @JoinColumn(nullable = false)
    @MapsId
    @JsonIgnore
    private Basket basket;

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public Basket getBasket()
    {
        return basket;
    }

    public void setBasket(Basket basket)
    {
        this.basket = basket;
    }
}
