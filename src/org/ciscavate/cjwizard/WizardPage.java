/**
 * 
 */
package org.ciscavate.cjwizard;

import java.util.List;

import javax.swing.JPanel;

/**
 * @author rcreswick
 *
 */
public abstract class WizardPage extends JPanel {
   
   private static long _idCounter=0;
   
   private final long _id = _idCounter++;

   private final String _title;
   private final String _description;

   private WizardController _controller;
   
   /**
    * Constructor.  Sets the title and description for
    * this wizard panel.
    * 
    * @param title The short (1-3 word) name of this page.
    * @param description A possibly longer description
    *       (but still under 1 sentence)
    */
   public WizardPage(String title, String description){
      _title = title;
      _description = description;
   }
   
   /**
    * Gets the unique identifier for this wizard page;
    * 
    * @return
    */
   public final String getId(){
      return ""+_id;
   }
   
   public String getTitle(){
      return _title;
   }
   
   public String getDescription(){
      return _description;
   }
   
   /**
    * Updates the settings map after this page has been
    * used by the user.
    * 
    * This method should update the WizardSettings Map so that it contains
    * the new key/value pairs from this page.
    * 
    */
   public void updateSettings(WizardSettings settings){
      
   }
   
   /**
    * Invoked immediately prior to rendering the wizard page on screen.
    * 
    * This provides an opportunity to adjust the next/finish buttons and
    * customize the ui based on feedback.
    */
   public void rendering(List<WizardPage> path, WizardSettings settings){
      // intentionally empty. (default implementation)
   }
   
   /**
    * Registers the controller with this WizardPage.
    * 
    * The default visibility is intentional, but protected would be fine too.
    * 
    * @param controller
    */
   void registerController(WizardController controller){
      _controller = controller;
   }
   
   protected void setNextEnabled(boolean enabled){
      if (null != _controller)
         _controller.setNextEnabled(enabled);
   }
   
   protected void setPrevEnabled(boolean enabled){
      if (null != _controller)
         _controller.setPrevEnabled(enabled);
   }
   
   protected void setFinishEnabled(boolean enabled){
      if (null != _controller)
         _controller.setFinishEnabled(enabled);
   }
   
   public String toString(){
      return getId() + ": " +getTitle();
   }


}
