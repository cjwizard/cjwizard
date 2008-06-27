/**
 * 
 */
package org.ciscavate.utilities;

/**
 * @author rcreswick
 *
 */
public class ExceptionUtilities {

   /**
    * Checks to see if obj is null, and if so throws an
    * IllegalArgumentException with the given message.
    * 
    * @param obj
    * @param msg
    */
   public static void checkNull(Object obj, String msg) {
      if (null == obj)
         throw new IllegalArgumentException(msg);
   }

}
