package cc.superliar.service.Impl;

import cc.superliar.entity.Admin;
import cc.superliar.repo.AdminRepo;
import cc.superliar.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static cc.superliar.entity.AdminSpecs.*;
import java.util.List;

/**
 * Created by shentao on 2016/11/8.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepo adminRepo;


    @Override
    public Admin find(String name) {
        List<Admin> admins =adminRepo.findAll(find11());
        return adminRepo.findByAccount(name);

    }
}
