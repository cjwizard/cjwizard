/**
 * 
 */
package org.ciscavate.cjwizard;

import java.util.List;


/**
 * @author rcreswick
 *
 */
public interface WizardListener {

   public void onPageChanged(WizardPage newPage, List<WizardPage> path);
   
   public void onFinished(List<WizardPage> path, WizardSettings settings);
   
   public void onCanceled(List<WizardPage> path, WizardSettings settings);
}
