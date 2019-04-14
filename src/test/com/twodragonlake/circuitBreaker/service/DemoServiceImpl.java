package com.twodragonlake.circuitBreaker.service;

import java.util.UUID;

/**
 * Description.
 *
 * @author : CeaserWang
 * @version : 1.0
 * @since : 2019/4/14 22:51
 */
public class DemoServiceImpl implements DemoService{
    public String getUuid(int idx) {
        if(idx % 2 == 0){
            throw new RuntimeException();
        }
        return UUID.randomUUID().toString() + idx;
    }

    public void illegalEx(int idx) {
        if(idx % 2 == 0){
            throw new IllegalArgumentException();
        }
    }
}