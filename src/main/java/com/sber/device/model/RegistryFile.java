package com.sber.device.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "registry_file", schema ="payment")
@NoArgsConstructor
@Data
public class RegistryFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String reg_file_name;
    @Temporal(TemporalType.DATE)
    private Date load_time;

    @Column(length = 2)
    private Integer status;

    public RegistryFile(String reg_file_name, Date load_time, Integer status) {
        this.reg_file_name = reg_file_name;
        this.load_time = load_time;
        this.status = status;
    }

}
