package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.model.BcsUserPrincipal;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HazelcastCachingService implements CachingService<String, BcsUserPrincipal> {

    private static final String AUTH_MAP = "authmap";

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Override
    public void put(String key, BcsUserPrincipal value) {
        IMap<String, BcsUserPrincipal> map = hazelcastInstance.getMap(AUTH_MAP);
        map.put(key, value);
    }
}
