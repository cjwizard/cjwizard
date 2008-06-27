/**
 * 
 */
package org.ciscavate.cjwizard;

import javax.swing.JPanel;

/**
 * @author rcreswick
 *
 */
public abstract class PageTemplate extends JPanel {

   /**
    * Reference to the wizard controller that manages this template.
    */
   private WizardController _controller = null;
   
   /**
    * Called when a new page is to be displayed in the wizard.
    * 
    * @param page The page to display.
    */
   protected abstract void setPage(final WizardPage page);

   /**
    * Registers a WizardController with this class.
    * 
    * @param controller
    */
   protected void registerController(WizardController controller) {
      _controller = controller;
   }

   /**
    * @return the WizardController that manages this wizard.
    */
   protected WizardController getController() {
      return _controller;
   }
}