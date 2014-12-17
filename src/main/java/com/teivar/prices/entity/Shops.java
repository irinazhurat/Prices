package com.teivar.prices.entity;

/**
 * Created by Zalesskiy_K on 17.12.2014.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Shops")
public class Shops {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String desc;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shops")
    private Set<Receipts> receipts;

    public Set<Receipts> getUsers() {
        return receipts;
    }

    public void setUsers(Set<Receipts> receipts) {
        this.receipts = receipts;
    }

    public Shops() {

    }

    public Shops(String name, String desc) {
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String barcode) {
        this.desc = barcode;
    }
}