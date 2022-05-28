package com.icode.sentinel.provider.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 12:04
 */
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Depart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
