/**
 * 
 */
package org.ciscavate.cjwizard;

/**
 * An interface to allow the CJWizardPage to extract values from 
 * custom (and other non-swing) components.
 * 
 * @author rogue
 *
 */
public interface CustomWizardComponent {

   /**
    * Gets the current value of the component.
    *  
    * @return The value.
    */
   public Object getValue();
}
