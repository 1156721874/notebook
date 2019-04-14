package com.twodragonlake.circuitBreaker.handler;

/**
 * Description.
 *
 * @author : CeaserWang
 * @version : 1.0
 * @since : 2019/4/14 21:37
 */
public class ProxyFactory {
    public static <T> T proxyBean(Object target){
        return (T) new CircuitBreakerInvocationHandler(target).proxy();
    }
}