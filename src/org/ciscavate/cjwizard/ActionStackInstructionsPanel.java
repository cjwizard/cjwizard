///**
// *
// */
//package org.ciscavate.cjwizard;
//
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.util.NoSuchElementException;
//
//import javax.swing.BorderFactory;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.DefaultListModel;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.ListCellRenderer;
//import javax.swing.ListSelectionModel;
//import javax.swing.SwingConstants;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.netbeans.api.wizard.displayer.InstructionsPanel;
//import org.netbeans.api.wizard.displayer.WizardDisplayerImpl;
//import org.netbeans.modules.wizard.MergeMap;
//import org.netbeans.spi.wizard.Wizard;
//import org.netbeans.spi.wizard.WizardObserver;
//
///**
// * @author rcreswick
// *
// */
//public class ActionStackInstructionsPanel
//   implements InstructionsPanel, WizardObserver, ListSelectionListener {
//
//   /**
//    * Commons logging log instance
//    */
//   private static Log log = LogFactory.getLog(ActionStackInstructionsPanel.class);
//
//   private final Wizard wizard;
//
//   private JPanel navPanel;
//
//   private JList stepList;
//   private DefaultListModel listModel;
//
//   private boolean inSummaryPage;
//
//   private WizardDisplayerImpl displayer;
//
//   /**
//    * @param wizard
//    */
//   public ActionStackInstructionsPanel(Wizard wizard, WizardDisplayerImpl displayer) {
//      this.displayer = displayer;
//
//      listModel = new DefaultListModel();
//      stepList = new JList(listModel);
//
//      stepList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//      stepList.addListSelectionListener(this);
//
//      stepList.setCellRenderer(new StepCellRenderer());
//
//      this.wizard = wizard;
//      this.wizard.addWizardObserver(this);
//
//      JScrollPane listScroller = new JScrollPane(stepList);
//      listScroller.setMaximumSize(new Dimension(Short.MAX_VALUE,
//            Short.MAX_VALUE));
//
//      navPanel = new JPanel();
//      navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.PAGE_AXIS));
//      navPanel.add(Box.createRigidArea(new Dimension(150, 0)));
//      navPanel.add(listScroller);
//
//
//      navPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//   }
//
//   /* (non-Javadoc)
//    * @see org.netbeans.api.wizard.displayer.InstructionsPanel#getComponent()
//    */
//   @Override
//   public Container getComponent() {
//      log.trace("getComponent");
//      return navPanel;
//   }
//
//   /* (non-Javadoc)
//    * @see org.netbeans.api.wizard.displayer.InstructionsPanel#setInSummaryPage(boolean)
//    */
//   @Override
//   public void setInSummaryPage(boolean val) {
//      log.trace("setInSummaryPage("+val+")");
//      this.inSummaryPage = val;
//   }
//
//   /* (non-Javadoc)
//    * @see org.netbeans.spi.wizard.WizardObserver#navigabilityChanged(org.netbeans.spi.wizard.Wizard)
//    */
//   @Override
//   public void navigabilityChanged(Wizard wizard) {
//      // TODO Auto-generated method stub
//      log.trace("navigabilityChanged");
//   }
//
//   /**
//    * Called whenever the current step changes.
//    *
//    * @param wizard The wizard whose current step has changed
//    */
//   @Override
//   public void selectionChanged(Wizard wizard) {
//      log.debug("Wizard Page changed to: "+wizard.getCurrentStep());
//
//      // see if the stack contains the current step, and if so,
//      // pop everything between that step and the head.
//      String curStep = wizard.getCurrentStep();
//
//      if (null == curStep){
//         log.error("Null step retrieved from wizard!");
//         return;
//      }
//
//      int idx = listModel.indexOf(curStep);
//
//      if (-1 == idx){
//         listModel.addElement(wizard.getCurrentStep());
//         idx = listModel.indexOf(curStep);
//      } else {
//         int begining = idx + 1;
//         int end = listModel.size() - 1;
//
//         if (begining <= end) {
//            log.debug("Removing range: ["+begining+"-"+end+"]");
//            stepList.removeListSelectionListener(this);
//            listModel.removeRange(begining, end);
//            stepList.addListSelectionListener(this);
//         }
//      }
//
//      // select the active page:
//      if (stepList.getSelectedIndex() != idx){
//         stepList.setSelectedIndex(idx);
//      }
//   }
//
//   /* (non-Javadoc)
//    * @see org.netbeans.spi.wizard.WizardObserver#stepsChanged(org.netbeans.spi.wizard.Wizard)
//    */
//   @Override
//   public void stepsChanged(Wizard wizard) {
//      // TODO Auto-generated method stub
//      log.trace("stepsChanged");
//   }
//
//   /* (non-Javadoc)
//    * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
//    */
//   @Override
//   public void valueChanged(ListSelectionEvent e) {
//      if (e.getValueIsAdjusting()){
//         // do nothing, selection hasn't been finalized yet.
//         return;
//      }
//
//      if (stepList.getSelectedIndex() == (listModel.size() - 1)){
//         // do nothing, this is the last index in the list.
//         return;
//      }
//
//      // if we've made it this far, we need to adjust the display and settings
//      // to correspond to an earlier step:
//
//      String stepId = (String)stepList.getSelectedValue();
//      log.debug("Navigating to "+stepId);
//
//      // trim the settings from the intervening steps:
//      MergeMap settings = getSettings();
//      if (null == settings){
//         return;
//      }
//
//      log.debug("popping settings to reach id: "+
//               stepId+" (currId="+settings.currID()+")");
//
//      while (!settings.currID().equals(stepId)) {
//         try{
//            String poppedMap = settings.popAndCalve();
//            log.debug("Removed submap: '"+poppedMap+"'");
//         }catch(NoSuchElementException nsee) {
//            log.debug("no more elements", nsee);
//         }
//      }
//
//      displayer.setDeferredResult(null);
//      displayer.navigateTo(stepId);
//      displayer.setInSummary(false);
//
//      //wizard.navigatingTo(stepId, settings);
//   }
//
//   private MergeMap getSettings(){
//      return displayer.getSettings();
//   }
//
//
//
//   private class StepCellRenderer extends JLabel implements ListCellRenderer{
//
//      private Color offsetColor = new Color(220,220,220);
//
//      /* (non-Javadoc)
//       * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
//       */
//      @Override
//      public Component getListCellRendererComponent(JList list, Object value,
//            int index, boolean isSelected, boolean cellHasFocus) {
//         this.setOpaque(true);
//
//         String title = (String)value;
//
//         if (isSelected) {
//            setBackground(list.getSelectionBackground());
//            setForeground(list.getSelectionForeground());
//         } else {
//            Color bg = list.getBackground();
//            Color fg = list.getForeground();
//
//            if (0 != (index % 2)){
//               bg = offsetColor;
//            }
//
//            setBackground(bg);
//            setForeground(fg);
//
//         }
//
//         setText(title);
//         setFont(list.getFont());
//
//         setHorizontalAlignment(SwingConstants.CENTER);
//
//         this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//
//         return this;
//      }
//   }
//}
