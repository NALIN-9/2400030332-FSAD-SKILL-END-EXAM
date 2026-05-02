package com.transport.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transports")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "transport_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "capacity")
    private Integer capacity;

    public Transport() {
    }

    public Transport(String name, Date date, String status, String vehicleType, Integer capacity) {
        this.name = name;
        this.date = date;
        this.status = status;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
