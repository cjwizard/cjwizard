/**
 * 
 */
package org.ciscavate.cjwizard;

import java.util.List;

/**
 * @author rcreswick
 *
 */
public interface WizardController {

   /**
    * Register a listener with this wizard.
    * @param listener
    */
   public void addWizardListener(WizardListener listener);
   
   /**
    * Removes the specified listener.
    * @param listener
    */
   public void removeWizardListener(WizardListener listener);

   /**
    * Accessor for the current settings map at any time.
    * 
    * @return The current settings.
    */
   public WizardSettings getSettings();

   /**
    * Gets the path of pages at the current point in the dialog.
    * 
    * @return
    */
   public List<WizardPage> getPath();

   /**
    * @param enabled
    */
   public void setNextEnabled(boolean enabled);

   /**
    * @param enabled
    */
   public void setPrevEnabled(boolean enabled);

   /**
    * @param enabled
    */
   public void setFinishEnabled(boolean enabled);
}