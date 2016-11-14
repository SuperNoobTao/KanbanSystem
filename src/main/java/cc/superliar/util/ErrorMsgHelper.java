package cc.superliar.util;


import cc.superliar.enums.ErrorType;

/**
 * Format the error msg.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/6/15
 * @since JDK1.8
 */
public class ErrorMsgHelper {

  /**
   * Get return error message.
   *
   * @param msg  error type
   * @param args args
   * @return return error message
   */
  public static String getReturnMsg(ErrorType msg, String... args) {
    final String COLON = ": ";
    final String PREFIX = String.join("", msg.name(), COLON);
    if (args != null) {
      return String.join("", PREFIX, String.format(msg.description(), (Object) args));//format-格式化！ SY001:XX is exsited;
    } else {
      return String.join("", PREFIX, msg.description());
    }
  }
}
