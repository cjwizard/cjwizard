/**
 * 
 */
package org.ciscavate.cjwizard;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author rcreswick
 *
 */
public class StackWizardSettingsTest {

   /**
    * Test method for {@link org.ciscavate.cjwizard.StackWizardSettings#rollBack(java.lang.String)}.
    */
   @Test
   public void testRollBack() {
      WizardSettings s = new StackWizardSettings();

      final String key1 = "key1";
      final String key2 = "key2";
      final String key3 = "key3";
      final String value1 = "value1";
      final String value2 = "value2";
      final String value3 = "value3";
      final String value4 = "value4";
      
      final String page1 = "page1";
      final String page2 = "page2";
      
      s.newPage(page1);
      s.put(key1, value1);
      s.put(key2, value2);
      
      s.newPage(page2);
      s.put(key3, value3);
      // key 1 should still be the same as on Page1:
      Assert.assertEquals("Wrong value", s.get(key1), value1);
      
      s.put(key1, value4);
      Assert.assertEquals("Wrong value", s.get(key1), value4);
      // make sure key 2 is still visible:
      Assert.assertEquals("Wrong value", s.get(key2), value2);
      
      // key3 should now be visible:
      Assert.assertEquals("Wrong value", s.get(key3), value3);
      
      s.rollBack();
      // page2 keys should not have any effect:
      Assert.assertNull("key should not exist", s.get(key3));
      
      Assert.assertEquals("Wrong value", s.get(key1), value1);
      Assert.assertEquals("Wrong value", s.get(key2), value2);
      
   }

   /**
    * Test method for {@link java.util.HashMap#get(java.lang.Object)}.
    */
   @Test
   public void testGetAndPut_simple() {
      final String key1 = "key1";
      final String key2 = "key2";
      final String key3 = "key3";
      final String value1 = "value1";
      final String value2 = "value2";
      final String value3 = "value3";
      
      WizardSettings s = new StackWizardSettings();
      s.newPage("page1");
      s.put(key1, value1);
      s.put(key2, value2);
      
      // make sure we have basic hash map behaviour:
      Assert.assertEquals("Wrong value", s.get(key1), value1);
      Assert.assertEquals("Wrong value", s.get(key2), value2);
   }

   @Test
   public void testKeySet(){
      final String key1 = "key1";
      final String key2 = "key2";
      final String key3 = "key3";
      final String value1 = "value1";
      final String value2 = "value2";
      final String value3 = "value3";
      
      String[] oracle1 = {key1, key2};
      String[] oracle2 = {key1, key2, key3};
      
      WizardSettings s = new StackWizardSettings();
      s.newPage("page1");
      s.put(key1, value1);
      s.put(key2, value2);
      
      Set<String> keys = s.keySet();
      assertEquals(oracle1, keys);
      
      s.newPage("page2");
      s.put(key3, value3);
      keys = s.keySet();
      assertEquals(oracle2, keys);
            
      // roll back and ensure the set still makes sense:
      s.rollBack();
      keys = s.keySet();
      assertEquals(oracle1, keys);
   }

   /**
    * @param oracle1
    * @param keys
    */
   private void assertEquals(String[] oracle, Set<String> keys) {
      Assert.assertEquals("Set is wrong size", oracle.length, keys.size());
      for (String s : oracle){
         Assert.assertTrue("Set should contain: "+s, keys.contains(s));
      }
   }
}
