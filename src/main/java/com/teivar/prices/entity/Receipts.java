package com.teivar.prices.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */

@Entity
@Table(name = "Receipts")
public class Receipts {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @Column(name = "TimeStamps")
    private Date timeStamp;

    @Column(name = "Summ")
    private Double summ;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Shops_id", nullable = false)
    private Shops shops;
    @Override
    public String toString(){return this.getShops().getName();}

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receipts")
    private Set<ReceiptItems> receiptItemses;

    public Set<ReceiptItems> getReceiptItemses() {
        return receiptItemses;
    }

    public void setReceiptItemses(Set<ReceiptItems> receiptItemses) {
        this.receiptItemses = receiptItemses;
    }

    public Receipts(){

    }

    public Receipts(Date timeStamp, Double summ, Shops shops){
        this.timeStamp = timeStamp;
        this.summ = summ;
        this.shops = shops;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }


    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getSumm() {
        return summ;
    }

    public void setSumm(Double summ) {
        this.summ = summ;
    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
    }
}
