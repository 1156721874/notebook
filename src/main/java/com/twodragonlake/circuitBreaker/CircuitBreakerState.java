package com.twodragonlake.circuitBreaker;

/**
 * Description.
 *
 * @author : CeaserWang
 * @version : 1.0
 * @since : 2019/4/14 21:28
 */
public enum CircuitBreakerState {
    CLOSED,    // working normally, calls are transparently passing through
    OPEN,      // method calls are being intercepted and CircuitBreakerExceptions are being thrown instead
    HALF_OPEN  // method calls are passing through; if another blacklisted exception is thrown, reverts back to OPEN
}