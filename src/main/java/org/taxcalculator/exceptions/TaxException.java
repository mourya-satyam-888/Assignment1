package org.taxcalculator.exceptions;

import org.taxcalculator.enums.ExceptionCode;

/**
 * BaseException class should only be used to throw exception in this
 * application.
 */
public class TaxException extends RuntimeException {
  private String detailedMessage;
  private ExceptionCode errorCode;

  /**
   * Exception with only message.
   *
   * @param message detailed message
   */
  public TaxException(String message) {
    super(message);
    this.detailedMessage = message;
  }

  /**
   * Exception with message and error code.
   *
   * @param message detailed message
   * @param code    error code
   */
  public TaxException(String message, ExceptionCode code) {
    super(message);
    this.detailedMessage = message;
    this.errorCode = code;
  }

  public String getDetailedMessage() {
    return detailedMessage;
  }

  public ExceptionCode getErrorCode() {
    return errorCode;
  }
}
