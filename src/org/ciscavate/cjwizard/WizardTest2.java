/**
 * Copyright 2008  Eugene Creswick
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ciscavate.cjwizard;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ciscavate.cjwizard.pagetemplates.TitledPageTemplate;

/**
 * This demo class uses a JDialog to hold the wizard.
 * 
 * 
 * @author rcreswick
 *
 */
public class WizardTest2 extends JDialog {

   /**
    * Commons logging log instance
    */
   private static Log log = LogFactory.getLog(WizardTest.class);
   
   /**
    * @param args
    */
   public static void main(String[] args) {
      // create the dialog, and show it:
      WizardTest2 test = new WizardTest2();
      test.setVisible(true);
   }
   
   public WizardTest2(){
      // first, build the wizard.  The TestFactory defines the
      // wizard content and behavior.
      final WizardContainer wc =
         new WizardContainer(new TestFactory(),
                             new TitledPageTemplate(),
                             new StackWizardSettings());
      
      // add a wizard listener to update the dialog titles and notify the
      // surrounding application of the state of the wizard:
      wc.addWizardListener(new WizardListener(){
         @Override
         public void onCanceled(List<WizardPage> path, WizardSettings settings) {
            log.debug("settings: "+wc.getSettings());
            WizardTest2.this.dispose();
         }

         @Override
         public void onFinished(List<WizardPage> path, WizardSettings settings) {
            log.debug("settings: "+wc.getSettings());
            WizardTest2.this.dispose();
         }

         @Override
         public void onPageChanged(WizardPage newPage, List<WizardPage> path) {
            log.debug("settings: "+wc.getSettings());
            // Set the dialog title to match the description of the new page:
            WizardTest2.this.setTitle(newPage.getDescription());
         }
      });
      
      // Set up the standard bookkeeping stuff for a dialog, and
      // add the wizard to the JDialog:
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      this.getContentPane().add(wc);
      this.pack();
   }

   /**
    * Implementation of PageFactory to generate the wizard pages needed
    * for the wizard.
    */
   private class TestFactory implements PageFactory{
      
      /* (non-Javadoc)
       * @see org.ciscavate.cjwizard.PageFactory#createPage(java.util.List, org.ciscavate.cjwizard.WizardSettings)
       */
      @Override
      public WizardPage createPage(List<WizardPage> path,
            WizardSettings settings) {
         log.debug("creating page "+path.size());
         
         // Get the next page to display.  The path is the list of all wizard
         // pages that the user has proceeded through from the start of the
         // wizard, so we can easily see which step the user is on by taking
         // the length of the path.  This makes it trivial to return the next
         // WizardPage:
         WizardPage page = buildPage(path.size(), settings);


         // if we wanted to, we could use the WizardSettings object like a
         // Map<String, Object> to change the flow of the wizard pages.
         // In fact, we can do arbitrarily complex computation to determine
         // the next wizard page.
         
         log.debug("Returning page: "+page);
         return page;
      }

		 private WizardPage buildPage(int pageCount, final WizardSettings settings) {
			  switch (pageCount)  {
			  case 0: 
					return new WizardPage("One", "First Page"){
						 {
							  JTextField field = new JTextField();
							  // set a name on any component that you want to collect values
							  // from.  Be sure to do this *before* adding the component to
							  // the WizardPage.
							  field.setName("city");
							  field.setPreferredSize(new Dimension(50, 20));
							  add(new JLabel("Enter City:"));
							  add(field);
						 }
					};
			  case 1:
					return new WizardPage("Two", "Second Page"){
						 {
							  // get a value out of the settings, and display it on the second page:
							  add(new JLabel("The city you entered was: "+settings.get("city")));
							  
							  JTextField field = new JTextField();
							  field.setName("city");
							  field.setPreferredSize(new Dimension(50, 20));
							  add(new JLabel("Enter a different city:"));
							  add(field);
						 }
					};
			  case 2:
					return new WizardPage("Three", "Third Page"){
						 {
							  // get a value with the same key -- notice that it was altered by the user's input on page 2:
							  add(new JLabel("The city you entered was: "+settings.get("city")));
						 }
						 /**
						  * This is the last page in the wizard, so we will enable the finish
						  * button and disable the "Next >" button just before the page is
						  * displayed:
						  */
						 public void rendering(List<WizardPage> path, WizardSettings settings) {
							  super.rendering(path, settings);
							  setFinishEnabled(true);
							  setNextEnabled(false);
						 }
					};
			  }
			  return null;
		 }
      
   }
}
