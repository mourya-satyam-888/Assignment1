package org.taxcalculator.exceptions;

import org.taxcalculator.enums.ExceptionCode;

/**
 * BaseException class should only be used to throw exception in this
 * application.
 */
@SuppressWarnings("serial")
public class BaseApplicationException extends RuntimeException {
  /**
   * detailed Message.
   */
  private final String detailedMessage;
  /**
   * ErrorCode.
   */
  private ExceptionCode errorCode;

  /**
   * Exception with only message.
   *
   * @param message detailed message
   */
  public BaseApplicationException(final String message) {
    super(message);
    this.detailedMessage = message;
  }

  /**
   * Exception with message and error code.
   *
   * @param message detailed message
   * @param code    error code
   */
  public BaseApplicationException(final String message, final ExceptionCode code) {
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
