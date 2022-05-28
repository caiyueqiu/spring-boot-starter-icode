package com.icode.sentinel.provider.service.impl;

import com.icode.sentinel.provider.bean.Depart;
import com.icode.sentinel.provider.repository.DepartRepository;
import com.icode.sentinel.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/3/19 12:11
 */
@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartRepository repository;

    @Override
    public Depart getDepartById(int id) {
        if (repository.existsById(id)) {
            return repository.getOne(id);
        }
        Depart depart = new Depart();
        depart.setName("no this depart");
        return depart;
    }

}
