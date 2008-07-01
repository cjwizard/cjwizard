/**
 * 
 */
package org.ciscavate.cjwizard;

import java.util.List;

/**
 * A convenience class for implemeting parts of the WizardListener interface.
 * 
 * @author rcreswick
 */
public class WizardAdapter implements WizardListener {

   /* (non-Javadoc)
    * @see org.ciscavate.cjwizard.WizardListener#onCanceled(java.util.List, org.ciscavate.cjwizard.WizardSettings)
    */
   @Override
   public void onCanceled(List<WizardPage> path, WizardSettings settings) {
      // empty implementation.
   }

   /* (non-Javadoc)
    * @see org.ciscavate.cjwizard.WizardListener#onFinished(java.util.List, org.ciscavate.cjwizard.WizardSettings)
    */
   @Override
   public void onFinished(List<WizardPage> path, WizardSettings settings) {
      // empty implementation.
   }

   /* (non-Javadoc)
    * @see org.ciscavate.cjwizard.WizardListener#onPageChanged(org.ciscavate.cjwizard.WizardPage, java.util.List)
    */
   @Override
   public void onPageChanged(WizardPage newPage, List<WizardPage> path) {
      // empty implementation.
   }

}
