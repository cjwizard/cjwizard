/**
 * 
 */
package org.ciscavate.cjwizard;

import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author rcreswick
 *
 */
public class WizardTest extends JDialog {

   /**
    * Commons logging log instance
    */
   private static Log log = LogFactory.getLog(WizardTest.class);
   
   /**
    * @param args
    */
   public static void main(String[] args) {
      WizardTest test = new WizardTest();
           
      test.setVisible(true);
   }
   
   public WizardTest(){
      final WizardContainer wc = new WizardContainer(new TestFactory());
      wc.addWizardListener(new WizardListener(){
         @Override
         public void onCanceled(List<WizardPage> path, WizardSettings settings) {
            log.debug("settings: "+wc.getSettings());
            WizardTest.this.dispose();
         }

         @Override
         public void onFinished(List<WizardPage> path, WizardSettings settings) {
            log.debug("settings: "+wc.getSettings());
            WizardTest.this.dispose();
         }

         @Override
         public void onPageChanged(WizardPage newPage, List<WizardPage> path) {
            log.debug("settings: "+wc.getSettings());
         }
      });
      
      
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      this.getContentPane().add(wc);
      this.pack();
   }

   
   private class TestFactory implements PageFactory{
      String one = "one";
      String two = "two";
      String three = "three";
      
      private final WizardPage[] pages = {
            new WizardPage(one, one){
               JTextField field = new JTextField();
               {
                  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
                  add(new JLabel("One!"));
                  add(field);
               }
               /* (non-Javadoc)
                * @see org.ciscavate.cjwizard.WizardPage#updateSettings(org.ciscavate.cjwizard.WizardSettings)
                */
               @Override
               public void updateSettings(WizardSettings settings) {
                  settings.put("testField", field.getText());
               }
               
            },
            new WizardPage(two,two){
               JCheckBox box = new JCheckBox("testBox");
               {
                  add(new JLabel("Two!"));
                  add(box);
               }
               public void updateSettings(WizardSettings settings) {
                  settings.put("testBox", box.isSelected());
               }
               
            },
            new WizardPage(three,three){
               {
                  add(new JLabel("Three!"));
                  setBackground(Color.green);
               }

               public void rendering(List<WizardPage> path, WizardSettings settings) {
                  setFinishEnabled(true);
                  setNextEnabled(false);
               }
            }
      };
      
      
      /* (non-Javadoc)
       * @see org.ciscavate.cjwizard.PageFactory#createPage(java.util.List, org.ciscavate.cjwizard.WizardSettings)
       */
      @Override
      public WizardPage createPage(List<WizardPage> path,
            WizardSettings settings) {
         log.debug("creating page "+path.size());
         
         WizardPage page =  pages[path.size()];
         
         log.debug("Returning page: "+page);
         return page;
      }
      
   }
}
