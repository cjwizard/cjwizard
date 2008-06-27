/**
 * 
 */
package org.ciscavate.cjwizard;

import java.util.List;

/**
 * @author rcreswick
 *
 */
public interface PageFactory {

   /**
    * Creates (or retrieves) a wizard page based on the path of pages covered
    * so far between now and the start of the dialog, and the map of settings.
    * 
    * @param path  The list of all WizardPages seen so far.
    * @param settings The Map of settings collected.
    * @return The next WizardPage.
    */
   public WizardPage createPage(List<WizardPage> path, WizardSettings settings);
}
