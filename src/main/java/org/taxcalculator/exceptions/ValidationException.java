package org.taxcalculator.exceptions;

import org.taxcalculator.enums.ExceptionCode;

/**
 * This Exception should be thrown for Validation Error.
 */
public class ValidationException extends TaxException {
  public ValidationException(String message) {
    super(message, ExceptionCode.VALIDATION_ERROR);
  }
}
