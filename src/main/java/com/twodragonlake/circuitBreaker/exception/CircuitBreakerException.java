package com.twodragonlake.circuitBreaker.exception;

/**
 * Description.
 *
 * @author : CeaserWang
 * @version : 1.0
 * @since : 2019/4/14 21:37
 */
public class CircuitBreakerException extends RuntimeException{
    public CircuitBreakerException(String message) {
        super(message);
    }

    public CircuitBreakerException(String message, Throwable cause) {
        super(message, cause);
    }
}