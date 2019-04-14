package com.twodragonlake.circuitBreaker;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Description.
 *
 * @author : CeaserWang
 * @version : 1.0
 * @since : 2019/4/14 21:40
 */

public class CircuitBreakerRegister {
    private static ConcurrentHashMap<String, CircuitBreaker> breakers = new ConcurrentHashMap<String, CircuitBreaker>();

    public static CircuitBreaker get(String key){
        return breakers.get(key);
    }

    public static void putIfAbsent(String key,CircuitBreaker circuitBreaker){
        breakers.putIfAbsent(key,circuitBreaker);
    }
}