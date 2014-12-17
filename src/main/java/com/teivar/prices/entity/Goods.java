package com.teivar.prices.entity;

/**
 * Created by zalesskiy_k on 16.12.2014.
 */
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Barcode")
    private String barcode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "goods")
    private Set<ReceiptItems> receiptItems;

    public Set<ReceiptItems> getReceiptItemses() {
        return receiptItems;
    }

    public void setReceiptItemses(Set<ReceiptItems> receiptItems) {
        this.receiptItems = receiptItems;
    }

    public Goods() {

    }

    public Goods(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}