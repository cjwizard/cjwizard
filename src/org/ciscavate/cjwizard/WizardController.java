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
    * Transfer the state of the dialog to the specified page.  If this page
    * has been seen before, move back to that state. Otherwise, add the page to
    * the path and continue from there.
    * 
    * @param page The page to visit.
    */
   public void visitPage(WizardPage page);
   
   /**
    * Forcibly visit the next page.  This is equivalent to the user clicking on
    * "Next >", but this <b>can</b> be done when the button is disabled.
    */
   public void next();
   
   /**
    * Forcibly visit the previous page.  This is equivalent to the user clicking on
    * "< Prev", but this <b>can</b> be done when the button is disabled.
    */
   public void prev();
   
   /**
    * Forcibly finish the wizard.  This is equivalent to the user clicking on
    * "Finish", but this <b>can</b> be done when the button is disabled.
    */
   public void finish();
   
   /**
    * Forcibly cancel the wizard.  This is equivalent to the user clicking on
    * "Cancel", but this <b>can</b> be done when the button is disabled (which
    * should never happen, but if it did..).
    */
   public void cancel();
   
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