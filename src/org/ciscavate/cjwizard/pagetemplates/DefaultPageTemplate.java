/**
 * 
 */
package org.ciscavate.cjwizard.pagetemplates;

import java.awt.CardLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ciscavate.cjwizard.WizardContainer;
import org.ciscavate.cjwizard.WizardPage;

/**
 * This class provides a point at which third-party code can
 * introduce custom wrappers around the WizardPages that are displayed.
 * To do so, implement the IPageTemplate interface and wrap this
 * PageTemplate class with your own custom components, delegating the setPage
 * invocation to the wrapped instance of PageTemplate.
 * 
 * @author rcreswick
 *
 */
public class DefaultPageTemplate extends PageTemplate {
   
   /**
    * Commons logging log instance
    */
   private static Log log = LogFactory.getLog(WizardContainer.class);
   
   private final CardLayout _layout = new CardLayout();
   
   public DefaultPageTemplate(){
      this.setLayout(_layout);
   }
   
   /* (non-Javadoc)
    * @see org.ciscavate.cjwizard.PageTemplate#setPage(org.ciscavate.cjwizard.WizardPage)
    */
   public void setPage(final WizardPage page){
      log.debug("Setting page: "+page);

      // remove the page, just in case it was added before:
      remove(page);
      validate();
      
      add(page, page.getId());
      _layout.show(this, page.getId());
   }
}
