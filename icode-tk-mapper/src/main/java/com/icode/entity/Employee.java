package com.icode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * TODO
 *
 * @author caiyq
 * @date 2021/12/11 8:44
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tabple_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;
    private String empName;
    @Column(name = "emp_salary_apple")
    private Double empSalary;
    private Integer empAge;
}
