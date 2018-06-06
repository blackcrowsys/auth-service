package com.blackcrowsys.authservice.service;

public interface CachingService<K, V> {

    void put(K key, V value);
}
