package com.twodragonlake.circuitBreaker.exception;

/**
 * Description.
 *
 * @author : CeaserWang
 * @version : 1.0
 * @since : 2019/4/14 21:38
 */
public class CircuitBreakerOpenException extends CircuitBreakerException{
    public CircuitBreakerOpenException(String message, Throwable cause) {
        super("The operation " + message + " has too many failures, tripping circuit breaker.",cause);
    }

    public CircuitBreakerOpenException(String message) {
        super("The operation " + message + " has too many failures, tripping circuit breaker.");
    }
}