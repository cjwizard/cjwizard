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

import java.util.Map;

/**
 * This interface declare the minimum methods that all wizard settings classes
 * must implement.
 * 
 * @author ddearing
 * 
 * @version 20141205
 * 
 */
public interface WizardSettings extends Map<String, Object> {

   /**
    * Rolls back the current page, removing it from the settings.
    */
   void rollBack();

   /**
    * Create a new page of settings with the specified identifier.
    * 
    * @param id The identifier of the new page.
    */
   void newPage(String id);

   /**
    * Confirm the current page so it isn't lost when you do a rollBack.
    * 
    * @see #rollBack()
    * 
    * @since 20141205
    */
   public void commit();

}
