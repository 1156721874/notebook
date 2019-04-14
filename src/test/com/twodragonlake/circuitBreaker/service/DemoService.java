package com.twodragonlake.circuitBreaker.service;

import com.twodragonlake.circuitBreaker.annotation.GuardByCircuitBreaker;

/**
 * Description.
 *
 * @author : CeaserWang
 * @version : 1.0
 * @since : 2019/4/14 22:50
 */
public interface DemoService {

    @GuardByCircuitBreaker(noTripExceptions = {})
    public String getUuid(int idx);

    @GuardByCircuitBreaker(noTripExceptions = {IllegalArgumentException.class})
    public void illegalEx(int idx);
}