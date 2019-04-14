package com.twodragonlake.circuitBreaker.exception;

/**
 * Description.
 *
 * @author : CeaserWang
 * @version : 1.0
 * @since : 2019/4/14 21:38
 */
public class CircuitBreakerHalfOpenException extends CircuitBreakerException{
    public CircuitBreakerHalfOpenException(String message) {
        super(message);
    }

    public CircuitBreakerHalfOpenException(String message, Throwable cause) {
        super(message, cause);
    }
}