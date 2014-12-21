package com.teivar.prices.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Teivar on 18.12.2014.
 */

@Entity
@Table(name = "receiptitems")
public class ReceiptItems {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @Column(name = "Price")
    private double price;

    @Column(name = "Quan")
    private double quan;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "Receipt_id", nullable = false)
    private Receipts receipts;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "Good_id", nullable = false)
    private Goods goods;
    @Override
    public String toString()
    {
        return this.goods.getName();
    }

    public ReceiptItems(){

    }

    public ReceiptItems(long id, double price, double quan, Receipts receipts, Goods goods){
        this.id = id;
        this.price = price;
        this.quan = quan;
        this.receipts = receipts;
        this.goods = goods;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuan() {
        return quan;
    }

    public void setQuan(double quan) {
        this.quan = quan;
    }

    public Receipts getReceipts() {
        return receipts;
    }

    public void setReceipts(Receipts receipts) {
        this.receipts = receipts;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
