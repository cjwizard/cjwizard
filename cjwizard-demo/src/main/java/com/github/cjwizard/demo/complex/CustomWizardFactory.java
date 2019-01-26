/*

 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cjwizard.demo.complex;

import com.github.cjwizard.APageFactory;
import com.github.cjwizard.WizardPage;
import com.github.cjwizard.WizardSettings;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements the following workflow
 * <br><img src="doc-files/workflow.png">
 *
 * @author AO
 */
public class CustomWizardFactory extends APageFactory {

    private final Logger log = LoggerFactory.getLogger(CustomWizardFactory.class.getSimpleName());

    /**
     * in this more complex example, we'll use this array to define ALL pages in
     * order. This will be used to populate the navigation pane.
     *
     * We also cheat a bit here to simulate a tree like navigation pane by using
     * some spacing with the names of all the wizard pages.
     */
    protected final WizardPage[] pages = {
        new JPanelStep1(),
        new JPanelStep2(),
        new JPanelStep3(),
        new JPanelStep3A(),
        new JPanelStep3B(),
        new JPanelStep4(),
        new JPanelStep5()

    };

    @Override
    public WizardPage createPage(List<WizardPage> path, WizardSettings settings) {
        log.info("creating page " + path.size());

        if (path.isEmpty()) {
            return pages[0];
        }

        //last page viewed.
        WizardPage lastPage = path.get(path.size() - 1);

        if (lastPage instanceof JPanelStep3) {
            if (((JPanelStep3) lastPage).isIsChoiceA()) {
                return pages[3];
            } else {
                return pages[4];
            }
        }
        if (lastPage instanceof JPanelStep3B) {
            return pages[5];
        }
        if (lastPage instanceof JPanelStep3A) {
            return pages[5];
        }
        if (lastPage instanceof JPanelStep4) {
            return pages[6];
        }
        if (lastPage instanceof JPanelStep5) {
            return pages[0];
        }

        if (lastPage instanceof JPanelStep1) {
            return pages[1];
        }
        if (lastPage instanceof JPanelStep2) {
            return pages[2];
        }

        throw new RuntimeException();
        // in this example, we have a panel that will skip a step in the wizard
        // since it's skipping, we need to calculate what the next panel should be
        // based on the last item viewed.
        /*WizardPage lastViewed = path.get(path.size() - 1);
        for (int i = 0; i < pages.length; i++) {
            if (pages[i] == lastViewed) {
                log.info("Returning page: " + pages[i + 1]);
                return pages[i + 1];
            }
        }
         */
    }

}
