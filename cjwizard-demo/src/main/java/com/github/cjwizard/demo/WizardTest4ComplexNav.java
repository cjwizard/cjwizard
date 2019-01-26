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

package com.github.cjwizard.demo;

import com.github.cjwizard.AbstractPageFactory;
import com.github.cjwizard.StackWizardSettings;
import com.github.cjwizard.WizardContainer;
import com.github.cjwizard.WizardListener;
import com.github.cjwizard.WizardPage;
import com.github.cjwizard.WizardSettings;
import com.github.cjwizard.pagetemplates.TitledPageTemplate;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author AO
 */
public class WizardTest4ComplexNav extends javax.swing.JDialog {

   
    /**
     * Commons logging log instance
     */
    private final Logger log = LoggerFactory.getLogger(WizardTest.class.getSimpleName());
    final private TestFactory testFactory = new TestFactory();

    private javax.swing.JList<String> jListNavigation;
    private javax.swing.JScrollPane jScrollPane1;

    /**
     * Creates new form WizardNavBar
     * @param parent parent
     * @param modal true/false as a modal
     */
    public WizardTest4ComplexNav(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListNavigation = new javax.swing.JList<>();

        // first, build the wizard.  The TestFactory defines the
        // wizard content and behavior.
        final WizardContainer wc
                = new WizardContainer(testFactory,
                        new TitledPageTemplate(),
                        new StackWizardSettings());

        //do you want to store previously visited path and repeat it if you hit back
        //and then go forward a second time?
        //this options makes sense if you have a conditional path where depending on choice of a page
        // you can visit one of two other pages.
        wc.setForgetTraversedPath(true);

        // add a wizard listener to update the dialog titles and notify the
        // surrounding application of the state of the wizard:
        wc.addWizardListener(new WizardListener() {
            @Override
            public void onCanceled(List<WizardPage> path, WizardSettings settings) {
                log.info("settings: " + wc.getSettings());
                WizardTest4ComplexNav.this.dispose();
            }

            @Override
            public void onFinished(List<WizardPage> path, WizardSettings settings) {
                log.info("settings: " + wc.getSettings());
                WizardTest4ComplexNav.this.dispose();
            }

            @Override
            public void onPageChanged(WizardPage newPage, List<WizardPage> path) {
                log.info("settings: " + wc.getSettings());
                // Set the dialog title to match the description of the new page:
                WizardTest4ComplexNav.this.setTitle(newPage.getDescription());

                //update our nav view
                jListNavigation.setSelectedValue(newPage.getTitle(), true);
            }

            @Override
            public void onPageChanging(WizardPage newPage, List<WizardPage> path) {
            }
        });

        //this populates the list view (nav bar)
        jListNavigation.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return testFactory.pages.length;
            }

            @Override
            public String getElementAt(int index) {
                return testFactory.pages[index].getTitle();
            }
        });
        
        //hightlight the first item since that's the start position of the wizard
        jListNavigation.setSelectedIndex(0);

        //TODO this is set to false to prevent users from selecting another list item
        //since there's no clear way to jump to a specific wizard page
        jListNavigation.setEnabled(false);
        jScrollPane1.setViewportView(jListNavigation);

        
        // Set up the standard bookkeeping stuff for a dialog, and
        // add the wizard to the JDialog:
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(wc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(wc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();

    }

    /**
     * Implementation of PageFactory to generate the wizard pages needed for the
     * wizard.
     */
    private class TestFactory extends AbstractPageFactory {

        // To keep things simple, we'll just create an array of wizard pages:
        private final WizardPage[] pages = {
            new WizardPage("One", "First Page") {
                // this is an instance initializer -- it's a constructor for
                // an anonymous class.  WizardPages don't need to be anonymous,
                // of course.  It just makes the demo fit in one file if we do it
                // this way:
                {
                    JTextField field = new JTextField();
                    // set a name on any component that you want to collect values
                    // from.  Be sure to do this *before* adding the component to
                    // the WizardPage.
                    field.setName("testField");
                    field.setPreferredSize(new Dimension(50, 20));
                    add(new JLabel("One!"));
                    add(field);
                }
            },
            new WizardPage("Two", "Second Page") {
                {
                    JCheckBox box = new JCheckBox("testBox");
                    box.setName("box");
                    add(new JLabel("Two!"));
                    add(box);
                }

                /* (non-Javadoc)
                * @see com.github.cjwizard.WizardPage#updateSettings(com.github.cjwizard.WizardSettings)
                 */
                @Override
                public void updateSettings(WizardSettings settings) {
                    super.updateSettings(settings);

                    // This is called when the user clicks next, so we could do
                    // some longer-running processing here if we wanted to, and
                    // pop up progress bars, etc.  Once this method returns, the
                    // wizard will continue.  Beware though, this runs in the
                    // event dispatch thread (EDT), and may render the UI
                    // unresponsive if you don't issue a new thread for any long
                    // running ops.  Future versions will offer a better way of
                    // doing this.
                }

            },
            new WizardPage("Three", "Third Page") {
                {
                    add(new JLabel("Three!"));
                    JButton btn = new JButton("skip");
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            getController().visitPage(pages[4]);
                        }
                    });
                    add(btn);
                    setBackground(Color.green);
                }
  
                /**
                 * here we are dynamically injecting a new page into the workflow
                 * @param path
                 * @param settings 
                 */
                public void rendering(List<WizardPage> path, WizardSettings settings) {
                    super.rendering(path, settings);
                    /*
                    getController().visitPage(new WizardPage("Step 3A", "injected") {
                        {
                            add(new JLabel("3A!"));
                            setBackground(Color.BLUE);
                        }
                    });*/
                    //definitely not right.
                    /*
                    getController().getPath().add(new WizardPage("Step 3A", "injected") {
                        {
                            add(new JLabel("3A!"));
                            setBackground(Color.BLUE);
                        }
                    });*/
                    
                }
            },
            new WizardPage("Four", "Third Page") {
                {
                    add(new JLabel("Four!"));
                }

            },
            new WizardPage("Five", "Third Page") {
                {
                    add(new JLabel("Five!"));
                    
                }

            },
            new WizardPage("Six", "Third Page") {
                {
                    add(new JLabel("Six!"));
                    
                }

            },
            new WizardPage("Final", "Final Page") {
                {
                    add(new JLabel("And we're done!"));
                    JButton btn = new JButton("Start again?");
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            getController().visitPage(pages[0]);
                        }
                    });
                    add(btn);
                    
                }

                /**
                 * This is the last page in the wizard, so we will enable the
                 * finish button and disable the "Next >" button just before the
                 * page is displayed:
                 */
                public void rendering(List<WizardPage> path, WizardSettings settings) {
                    super.rendering(path, settings);
                    setFinishEnabled(true);
                    setNextEnabled(false);
                    
                }
            }
        };

        /* (non-Javadoc)
       * @see com.github.cjwizard.PageFactory#createPage(java.util.List, com.github.cjwizard.WizardSettings)
         */
        @Override
        public WizardPage createPage(List<WizardPage> path,
                WizardSettings settings) {
            log.info("creating page " + path.size());

           
            if (path.isEmpty())
                return pages[0];
            
            // in this example, we have a panel that will skip a step in the wizard
            // since it's skipping, we need to calculate what the next panel should be
            // based on the last item viewed.
            
            WizardPage lastViewed = path.get(path.size()-1);
            for (int i=0; i < pages.length; i++) {
                if (pages[i] == lastViewed) {
                    log.info("Returning page: " + pages[i+1]);
                    return pages[i+1];
                }
            }
            throw new RuntimeException();
        }
    }
}