package com.icode.sentinel.provider.controller;

import com.icode.sentinel.provider.bean.Depart;
import com.icode.sentinel.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 12:09
 */
@RestController
public class DepartController {
    @Autowired
    private DepartService service;

    @GetMapping("/provider/depart/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        return service.getDepartById(id);
    }

}
