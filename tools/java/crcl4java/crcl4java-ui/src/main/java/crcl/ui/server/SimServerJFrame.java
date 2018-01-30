/*
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United Statesm
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.ui.server;

import crcl.base.LengthUnitEnumType;
import static crcl.ui.IconImages.SERVER_IMAGE;
import crcl.utils.outer.interfaces.SimServerMenuOuter;
import crcl.utils.outer.interfaces.SimServerOuter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
public class SimServerJFrame extends javax.swing.JFrame implements SimServerOuter, SimServerMenuOuter {

    /**
     * Creates new form SimServer
     *
     * @throws javax.xml.parsers.ParserConfigurationException when
     * javax.xml.parsers.DocumentBuilderFactory fails in XpathUtils
     */
    public SimServerJFrame() throws ParserConfigurationException {
        initComponents();
        boolean logImages = SimServerJPanel.LOG_IMAGES_DEFAULT;
        this.jCheckBoxMenuItemLogImages.setSelected(logImages);
        this.setIconImage(SERVER_IMAGE);
        this.simServerJPanel1.setMenuOuter(this);
        this.simServerJPanel1.restartServer();
        jCheckBoxMenuItemValidateXML.setSelected(simServerJPanel1.isValidateXMLSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        try {
            simServerJPanel1 = new crcl.ui.server.SimServerJPanel();
        } catch (javax.xml.parsers.ParserConfigurationException e1) {
            e1.printStackTrace();
        }
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuItemSaveProperties = new javax.swing.JMenuItem();
        jMenuItemLoadProperties = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemEditStatus = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemSetSchema = new javax.swing.JMenuItem();
        jCheckBoxMenuItemValidateXML = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jCheckBoxMenuItemRandomPacket = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemAppendZero = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemIncludeGripperStatus = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemReplaceState = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugMoveDone = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugReadCommand = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDebugSendStatus = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDisableTextPopups = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemReplaceXmlHeader = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemEXI = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemLogImages = new javax.swing.JCheckBoxMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemViewCommandLogBrief = new javax.swing.JMenuItem();
        jMenuItemViewCommandLogFull = new javax.swing.JMenuItem();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CRCL SImulation Server");

        jMenu1.setText("File");

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(formListener);
        jMenu1.add(jMenuItemExit);

        jMenuItemSaveProperties.setText("Save Properties As ...");
        jMenuItemSaveProperties.addActionListener(formListener);
        jMenu1.add(jMenuItemSaveProperties);

        jMenuItemLoadProperties.setText("Load Properties ...");
        jMenuItemLoadProperties.addActionListener(formListener);
        jMenu1.add(jMenuItemLoadProperties);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItemEditStatus.setText("Status in Table ...");
        jMenuItemEditStatus.addActionListener(formListener);
        jMenu2.add(jMenuItemEditStatus);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("XML Schemas");

        jMenuItemSetSchema.setText("Set Schema File(s)  ... ");
        jMenuItemSetSchema.addActionListener(formListener);
        jMenu3.add(jMenuItemSetSchema);

        jCheckBoxMenuItemValidateXML.setSelected(true);
        jCheckBoxMenuItemValidateXML.setText("Validate XML with Schema(s)");
        jCheckBoxMenuItemValidateXML.addActionListener(formListener);
        jMenu3.add(jCheckBoxMenuItemValidateXML);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Options");

        jCheckBoxMenuItemRandomPacket.setText("Randomize Packeting");
        jMenu4.add(jCheckBoxMenuItemRandomPacket);

        jCheckBoxMenuItemAppendZero.setText("Append 0 for string termination");
        jMenu4.add(jCheckBoxMenuItemAppendZero);

        jCheckBoxMenuItemIncludeGripperStatus.setText("Connect to Gripper Server ...");
        jCheckBoxMenuItemIncludeGripperStatus.addActionListener(formListener);
        jMenu4.add(jCheckBoxMenuItemIncludeGripperStatus);

        jCheckBoxMenuItemReplaceState.setText("Replace CRCL_Working,CRCL_Done with Working,Done ...");
        jMenu4.add(jCheckBoxMenuItemReplaceState);

        jCheckBoxMenuItemDebugMoveDone.setText("Debug MOVE Done");
        jMenu4.add(jCheckBoxMenuItemDebugMoveDone);

        jCheckBoxMenuItemDebugReadCommand.setText("Debug Read Command");
        jMenu4.add(jCheckBoxMenuItemDebugReadCommand);

        jCheckBoxMenuItemDebugSendStatus.setText("Debug Send Status");
        jMenu4.add(jCheckBoxMenuItemDebugSendStatus);

        jCheckBoxMenuItemDisableTextPopups.setText("Disable Text Popups");
        jCheckBoxMenuItemDisableTextPopups.addActionListener(formListener);
        jMenu4.add(jCheckBoxMenuItemDisableTextPopups);

        jCheckBoxMenuItemReplaceXmlHeader.setSelected(true);
        jCheckBoxMenuItemReplaceXmlHeader.setText("Replace XML Headers");
        jMenu4.add(jCheckBoxMenuItemReplaceXmlHeader);

        jCheckBoxMenuItemEXI.setText("Use EXI (Efficient XML Interchange)");
        jCheckBoxMenuItemEXI.addActionListener(formListener);
        jMenu4.add(jCheckBoxMenuItemEXI);

        jCheckBoxMenuItemLogImages.setText("Log Images");
        jCheckBoxMenuItemLogImages.addActionListener(formListener);
        jMenu4.add(jCheckBoxMenuItemLogImages);

        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(formListener);
        jMenu4.add(jMenuItemAbout);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Tools");

        jMenuItemViewCommandLogBrief.setText("View Command Log Brief");
        jMenuItemViewCommandLogBrief.addActionListener(formListener);
        jMenu5.add(jMenuItemViewCommandLogBrief);

        jMenuItemViewCommandLogFull.setText("View Command Log Full");
        jMenuItemViewCommandLogFull.addActionListener(formListener);
        jMenu5.add(jMenuItemViewCommandLogFull);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simServerJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simServerJPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jMenuItemExit) {
                SimServerJFrame.this.jMenuItemExitActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSaveProperties) {
                SimServerJFrame.this.jMenuItemSavePropertiesActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemLoadProperties) {
                SimServerJFrame.this.jMenuItemLoadPropertiesActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemEditStatus) {
                SimServerJFrame.this.jMenuItemEditStatusActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSetSchema) {
                SimServerJFrame.this.jMenuItemSetSchemaActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemIncludeGripperStatus) {
                SimServerJFrame.this.jCheckBoxMenuItemIncludeGripperStatusActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemDisableTextPopups) {
                SimServerJFrame.this.jCheckBoxMenuItemDisableTextPopupsActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemEXI) {
                SimServerJFrame.this.jCheckBoxMenuItemEXIActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemLogImages) {
                SimServerJFrame.this.jCheckBoxMenuItemLogImagesActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemAbout) {
                SimServerJFrame.this.jMenuItemAboutActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemViewCommandLogBrief) {
                SimServerJFrame.this.jMenuItemViewCommandLogBriefActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemViewCommandLogFull) {
                SimServerJFrame.this.jMenuItemViewCommandLogFullActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemValidateXML) {
                SimServerJFrame.this.jCheckBoxMenuItemValidateXMLActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents


    private void jMenuItemEditStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditStatusActionPerformed
        simServerJPanel1.editStatusAction();
    }//GEN-LAST:event_jMenuItemEditStatusActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemSetSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetSchemaActionPerformed
        simServerJPanel1.setSchemaAction();
    }//GEN-LAST:event_jMenuItemSetSchemaActionPerformed

    public static final Logger LOGGER = Logger.getLogger(SimServerJFrame.class.getName());

    private void jCheckBoxMenuItemIncludeGripperStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemIncludeGripperStatusActionPerformed
        if (this.jCheckBoxMenuItemIncludeGripperStatus.isSelected()) {
            simServerJPanel1.setIncludeGripperAction();
        }
    }//GEN-LAST:event_jCheckBoxMenuItemIncludeGripperStatusActionPerformed


    private void jCheckBoxMenuItemEXIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemEXIActionPerformed
        simServerJPanel1.restartServer();
    }//GEN-LAST:event_jCheckBoxMenuItemEXIActionPerformed


    private void jMenuItemViewCommandLogBriefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewCommandLogBriefActionPerformed
        simServerJPanel1.viewCommandLogBriefAction();
    }//GEN-LAST:event_jMenuItemViewCommandLogBriefActionPerformed

    private void jMenuItemViewCommandLogFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewCommandLogFullActionPerformed
        simServerJPanel1.viewCommandLogFullAction();
    }//GEN-LAST:event_jMenuItemViewCommandLogFullActionPerformed

    private void jCheckBoxMenuItemLogImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemLogImagesActionPerformed
//        this.overHeadJPanel1.setLogImages(this.jCheckBoxMenuItemLogImages.isSelected());
//        this.sideViewJPanel1.setLogImages(this.jCheckBoxMenuItemLogImages.isSelected());
        simServerJPanel1.setLogImages(jCheckBoxMenuItemLogImages.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemLogImagesActionPerformed


    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        simServerJPanel1.aboutAction();
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jMenuItemSavePropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSavePropertiesActionPerformed
        JFileChooser chooser = new JFileChooser();
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
            setPropertiesFile(chooser.getSelectedFile());
            try {
                saveProperties();
            } catch (IOException ex) {
                Logger.getLogger(SimServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSavePropertiesActionPerformed

    private void jMenuItemLoadPropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoadPropertiesActionPerformed
        JFileChooser chooser = new JFileChooser();
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
            setPropertiesFile(chooser.getSelectedFile());
            try {
                loadProperties();
            } catch (IOException ex) {
                Logger.getLogger(SimServerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItemLoadPropertiesActionPerformed

    private void jCheckBoxMenuItemDisableTextPopupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDisableTextPopupsActionPerformed
        crcl.ui.misc.MultiLineStringJPanel.disableShowText = jCheckBoxMenuItemDisableTextPopups.isSelected();
    }//GEN-LAST:event_jCheckBoxMenuItemDisableTextPopupsActionPerformed

    private void jCheckBoxMenuItemValidateXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemValidateXMLActionPerformed
        simServerJPanel1.setValidateXMLSelected(jCheckBoxMenuItemValidateXML.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemValidateXMLActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new SimServerJFrame().setVisible(true);
                } catch (ParserConfigurationException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemAppendZero;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugMoveDone;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugReadCommand;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDebugSendStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDisableTextPopups;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemEXI;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemIncludeGripperStatus;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemLogImages;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRandomPacket;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemReplaceState;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemReplaceXmlHeader;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemValidateXML;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemEditStatus;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemLoadProperties;
    private javax.swing.JMenuItem jMenuItemSaveProperties;
    private javax.swing.JMenuItem jMenuItemSetSchema;
    private javax.swing.JMenuItem jMenuItemViewCommandLogBrief;
    private javax.swing.JMenuItem jMenuItemViewCommandLogFull;
    private javax.swing.JSplitPane jSplitPane1;
    private crcl.ui.server.SimServerJPanel simServerJPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isDebugMoveDoneSelected() {
        return this.jCheckBoxMenuItemDebugMoveDone.isSelected();
    }

    @Override
    public boolean isDebugReadCommandSelected() {
        return this.jCheckBoxMenuItemDebugReadCommand.isSelected();
    }

    @Override
    public boolean isDebugSendStatusSelected() {
        return this.jCheckBoxMenuItemDebugSendStatus.isSelected();
    }

    @Override
    public void updateConnectedClients(int numClients) {
        simServerJPanel1.updateConnectedClients(numClients);
    }

    @Override
    public void updateCycleCount(int _newCycleCount) {
        simServerJPanel1.updateCycleCount(_newCycleCount);
    }

    @Override
    public void updatePanels(boolean jointschanged) {
        simServerJPanel1.updatePanels(jointschanged);
    }

    @Override
    public void updateIsInitialized(boolean initialized) {
        simServerJPanel1.updateIsInitialized(initialized);
    }

    @Override
    public void updateCurrentCommandType(String cmdName) {
        simServerJPanel1.updateCurrentCommandType(cmdName);
    }

    @Override
    public void updateEndEffector(String text) {
        simServerJPanel1.updateEndEffector(text);
    }

    @Override
    public void updateToolChangerIsOpen(boolean isOpen) {
        simServerJPanel1.updateToolChangerIsOpen(isOpen);
    }

    @Override
    public void showMessage(String msgString) {
        simServerJPanel1.showMessage(msgString);
    }

    @Override
    public void finishSetCurrentWaypoint(int currentWaypoint) {
        simServerJPanel1.finishSetCurrentWaypoint(currentWaypoint);
    }

    @Override
    public void updateLengthUnit(LengthUnitEnumType lu) {
        simServerJPanel1.updateLengthUnit(lu);
    }

    @Override
    public void showDebugMessage(String s) {
        simServerJPanel1.showDebugMessage(s);
    }

    @Override
    public void updateNumWaypoints(int numWaypoints) {
        simServerJPanel1.updateNumWaypoints(numWaypoints);
    }

    @Override
    public boolean isInitializedSelected() {
        return simServerJPanel1.isInitializedSelected();
    }

    @Override
    public boolean isSendStatusWithoutRequestSelected() {
        return simServerJPanel1.isSendStatusWithoutRequestSelected();
    }

    @Override
    public boolean isAppendZeroSelected() {
        return jCheckBoxMenuItemAppendZero.isSelected();
    }

    @Override
    public boolean isReplaceXmlHeaderSelected() {
        return jCheckBoxMenuItemReplaceXmlHeader.isSelected();
    }

    @Override
    public boolean isRandomPacketSelected() {
        return jCheckBoxMenuItemRandomPacket.isSelected();
    }

    @Override
    public boolean isReplaceStateSelected() {
        return jCheckBoxMenuItemReplaceState.isSelected();
    }

    @Override
    public boolean isEditingStatus() {
        return simServerJPanel1.isEditingStatus();
    }

    @Override
    public boolean isEXISelected() {
        return jCheckBoxMenuItemEXI.isSelected();
    }

    public boolean isValidateXMLSelected() {
        return jCheckBoxMenuItemValidateXML.isSelected();
    }

    public int getPort() {
        return simServerJPanel1.getPort();
    }

    @Override
    public SimServerMenuOuter getMenuOuter() {
        return this;
    }

    public File getPropertiesFile() {
        return simServerJPanel1.getPropertiesFile();
    }

    public void setPropertiesFile(File f) {
        simServerJPanel1.setPropertiesFile(f);
    }

    public void loadProperties() throws IOException {
        simServerJPanel1.loadProperties();
        jCheckBoxMenuItemValidateXML.setSelected(simServerJPanel1.isValidateXMLSelected());
    }

    public void saveProperties() throws IOException {
        simServerJPanel1.saveProperties();
    }
}
