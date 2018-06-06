package com.blackcrowsys.authservice.db.migration;

import com.blackcrowsys.commonutils.db.Migration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MigrationService {

    @Autowired
    private Migration migration;

    @PostConstruct
    public void init() {
        migration.migrate();
    }
}
