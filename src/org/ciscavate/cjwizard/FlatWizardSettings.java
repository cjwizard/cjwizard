/**
 * Copyright 2008 Eugene Creswick
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ddearing
 */
public class FlatWizardSettings extends HashMap<String, Object>
implements WizardSettings
{
   
   /**
    * Commons logging log instance
    */
   private static Log log = LogFactory.getLog(FlatWizardSettings.class);
   
   /* (non-Javadoc)
    * @see org.ciscavate.cjwizard.WizardSettings#newPage(java.lang.String)
    */
   @Override
   public void newPage(String id)
   {
      // no-op
   }
   
   /* (non-Javadoc)
    * @see org.ciscavate.cjwizard.WizardSettings#rollBack()
    */
   @Override
   public void rollBack()
   {
      // no-op
   }
   
   /**
    * Serialize the current instance of {@link FlatWizardSettings} to the given
    * filename.
    * 
    * @param filename
    *           The filename.
    */
   public void serialize(String filename) {
      ObjectOutputStream out = null;
      FileOutputStream fout = null;
      try {
         fout = new FileOutputStream(filename);
         out = new ObjectOutputStream(fout);
         out.writeObject(this);
      } catch (IOException ioe) {
         log.error("Error writing settings", ioe);
      } finally {
         if (null != out) {
            try {
               out.close();
            } catch (IOException ioe) {
               log.error("Error closing output stream", ioe);
            }
         }
         if (null != fout) {
            try {
               fout.close();
            } catch (IOException ioe) {
               log.error("Error closing file", ioe);
            }
         }
      }
   }
   
   /**
    * Deserialize a {@link FlatWizardSettings} object from the specified file.
    * 
    * @param filename
    *           The filename.
    * @return The deserialized {@link FlatWizardSettings}.
    */
   static public FlatWizardSettings deserialize(String filename) {
      ObjectInputStream in = null;
      FileInputStream fin = null;
      try {
         fin = new FileInputStream(filename);
         in = new ObjectInputStream(fin);
         return (FlatWizardSettings)in.readObject();
      } catch (IOException ioe) {
         log.error("Error reading settings", ioe);
      } catch (ClassNotFoundException cnfe) {
         log.error("Couldn't instantiate seralized class", cnfe);
      } finally {
         if (null != in) {
            try {
               in.close();
            } catch (IOException ioe) {
               log.error("Error closing inputstream", ioe);
            }
         }
         if (null != fin) {
            try {
               fin.close();
            } catch (IOException ioe) {
               log.error("Error closing file", ioe);
            }
         }
      }
      return null;
   }
   
}
