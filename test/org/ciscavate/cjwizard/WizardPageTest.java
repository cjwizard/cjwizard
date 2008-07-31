/**
 * 
 */
package org.ciscavate.cjwizard;

import java.awt.Component;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import junit.framework.Assert;

import org.junit.Test;


/**
 * @author rogue
 *
 */
public class WizardPageTest {

   private class CustomTestComponent extends JPanel implements CustomWizardComponent {
      /* (non-Javadoc)
       * @see org.ciscavate.cjwizard.CustomWizardComponent#getValue()
       */
      @Override
      public Object getValue() {
         return "sample value";
      }
      
      /* (non-Javadoc)
       * @see org.ciscavate.cjwizard.CustomWizardComponent#setValue(java.lang.Object)
       */
      @Override
      public void setValue(Object o)
      {
         // no-op
      }
   }
   
   private class TestWizardPage extends WizardPage{

      /**
       * @param title
       * @param description
       */
      public TestWizardPage(String title, String description) {
         super(title, description);
         
         JTextField field = new JTextField();
         field.setName("test field");
         field.setText("some text");
         
         JCheckBox box = new JCheckBox("check box");
         box.setName("test box");
         
         CustomTestComponent ctc = new CustomTestComponent();
         ctc.setName("custom comp");
         
         add(new JLabel("test label"));
         
         add(field);
         add(box);
         add(ctc);
      }
      
   }
   
   /**
    * Verifies that widgets added to a WizardPage with names are located and
    * accessed properly.
    * 
    * Test method for {@link org.ciscavate.cjwizard.WizardPage#getNamedComponents(org.ciscavate.cjwizard.WizardSettings)}.
    */
   @Test
   public void testGetNamedComponents_countComponents() {
      WizardPage page = new TestWizardPage("Test Page","");
      
      Set<Component> components = page.getNamedComponents();
      
      Assert.assertEquals("wrong number of named components", 3, components.size());
   }
   
   /**
    * Verifies that widgets added to a WizardPage with names are located and
    * accessed properly.
    * 
    * Test method for {@link org.ciscavate.cjwizard.WizardPage#updateSettings(org.ciscavate.cjwizard.WizardSettings)}.
    */
   @Test
   public void testUpdateSettings_keysExist() {
      WizardPage page = new TestWizardPage("Test Page","");
      
      WizardSettings settings = new StackWizardSettings();
      page.updateSettings(settings);
      
      Assert.assertTrue("Key set did not contain needed key.",
            settings.keySet().contains("test box"));
      Assert.assertTrue("Key set did not contain needed key.",
            settings.keySet().contains("test field"));
      Assert.assertTrue("Key set did not contain needed key.",
            settings.keySet().contains("custom comp"));
   }
   
   /**
    * Verifies that widgets added to a WizardPage with names are located and
    * accessed properly.
    * 
    * Test method for {@link org.ciscavate.cjwizard.WizardPage#updateSettings(org.ciscavate.cjwizard.WizardSettings)}.
    */
   @Test
   public void testUpdateSettings_getValues() {
      WizardPage page = new TestWizardPage("Test Page","");
      
      WizardSettings settings = new StackWizardSettings();
      page.updateSettings(settings);


      boolean checkVal = (Boolean)settings.get("test box");
      Assert.assertEquals("Wrong value.", false, checkVal);
      
      String fieldVal = (String)settings.get("test field");
      Assert.assertEquals("Wrong value.", "some text", fieldVal);

      String ctcValue = (String)settings.get("custom comp");

      Assert.assertEquals("Wrong value.", "sample value", ctcValue);
      
   }
}
