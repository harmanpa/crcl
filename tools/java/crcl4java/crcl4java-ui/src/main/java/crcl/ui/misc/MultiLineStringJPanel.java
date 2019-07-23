/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
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
package crcl.ui.misc;

import crcl.ui.XFuture;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;
import javax.swing.WindowConstants;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
public class MultiLineStringJPanel extends javax.swing.JPanel {

    private static final ConcurrentLinkedDeque<MultiLineStringJPanel> allPanels = new ConcurrentLinkedDeque<>();

    /**
     * Creates new form MultiLineStringJPanel
     */
    @SuppressWarnings("initialization")
    public MultiLineStringJPanel() {
        initComponents();
        popMenu = createCopyPopMenu();
        allPanels.add(this);
    }

    public static void closeAllPanels() {
        MultiLineStringJPanel panel;
        while (null != (panel = allPanels.pollFirst())) {
            panel.cancelAndClose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCancel = new javax.swing.JButton();
        jButtonOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        FormListener formListener = new FormListener();

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(formListener);

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(formListener);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.addMouseListener(formListener);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOK))
                .addContainerGap())
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButtonCancel) {
                MultiLineStringJPanel.this.jButtonCancelActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonOK) {
                MultiLineStringJPanel.this.jButtonOKActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jTextArea1) {
                MultiLineStringJPanel.this.jTextArea1MouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jTextArea1) {
                MultiLineStringJPanel.this.jTextArea1MousePressed(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jTextArea1) {
                MultiLineStringJPanel.this.jTextArea1MouseReleased(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        this.cancelled = false;
        if (null != this.dialog) {
            this.dialog.setVisible(false);
        }
        allPanels.remove(this);
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        cancelAndClose();
        allPanels.remove(this);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void cancelAndClose() {
        this.cancelled = true;
        if (null != this.dialog) {
            this.dialog.setVisible(false);
        }
    }

    final private JPopupMenu popMenu;

    private JPopupMenu createCopyPopMenu() {
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.addActionListener(e -> copyText());
        popMenu.add(copyMenuItem);
        return popMenu;
    }

    private void copyText() {
        this.jTextArea1.getTransferHandler().exportToClipboard(this.jTextArea1,
                Toolkit.getDefaultToolkit().getSystemClipboard(),
                TransferHandler.COPY);
        popMenu.setVisible(false);
    }

    public void showPopup(Component comp, int x, int y) {

        popMenu.show(comp, x, y);
    }

    private void jTextArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MousePressed
        if (evt.isPopupTrigger()) {
            showPopup(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTextArea1MousePressed

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        if (evt.isPopupTrigger()) {
            showPopup(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jTextArea1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseReleased
        if (evt.isPopupTrigger()) {
            showPopup(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTextArea1MouseReleased

    private @Nullable
    JDialog dialog = null;

    private boolean cancelled = false;

    private static @Nullable
    String editTextPrivate(JDialog _dialog, String init) {
        MultiLineStringJPanel panel = new MultiLineStringJPanel();
        panel.jTextArea1.setText(init);
        panel.jScrollPane1.getVerticalScrollBar().setValue(0);
        panel.jTextArea1.setCaretPosition(0);
        panel.dialog = _dialog;
        _dialog.add(panel);
        _dialog.pack();
        _dialog.setVisible(true);
        if (panel.cancelled) {
            return null;
        }
        return panel.jTextArea1.getText();
    }

    public static @Nullable
    String editText(String init, Frame _owner,
            String _title,
            boolean _modal) {
        JDialog dialog = new JDialog(_owner, _title, _modal);
        return editTextPrivate(dialog, init);
    }

    public static @Nullable
    String editText(String init) {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        return editTextPrivate(dialog, init);
    }

    private static boolean showTextPrivate(JDialog _dialog, String init) {
        MultiLineStringJPanel panel = new MultiLineStringJPanel();
        panel.jTextArea1.setText(init);
//        panel.jTextArea1.setEditable(false);
        panel.jScrollPane1.getVerticalScrollBar().setValue(0);
        panel.jTextArea1.setCaretPosition(0);
        _dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _dialog.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                _dialog.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                _dialog.setVisible(false);
            }

        });
        panel.dialog = _dialog;
        _dialog.add(panel);
        _dialog.pack();
        _dialog.setVisible(true);
        return !panel.cancelled;
    }

    public static volatile boolean disableShowText = Boolean.valueOf("crcl.ui.misc.MultiLineString.disableShowText");

    private static boolean ignoreForceShow = false;

    public static void setIgnoreForceShow(boolean val) {
        ignoreForceShow = val;
    }

    public static boolean getIgnoreForceShow() {
        return ignoreForceShow;
    }

    public static XFuture<Boolean> showException(Throwable throwable) {
        return showText(throwable.toString(), null, "Exception", false, true);
    }

    public static XFuture<Boolean> showException(Throwable throwable, StackTraceElement trace[]) {
        return showText(throwable.toString() + "\n\n Thrown from:\r\n" + XFuture.traceToString(throwable.getStackTrace()) + "\n\n Logged from:\r\n" + XFuture.traceToString(trace), null, "Exception", false, true);
    }

    public static XFuture<Boolean> showText(String init) {
        return showText(init, null, "", false, false);
    }

    public static XFuture<Boolean> forceShowText(String init , JFrame parentJframe) {
        if(null == parentJframe) {
            throw new IllegalArgumentException("null == parentJframe");
        }
        return showText(init, parentJframe, "Message from " +parentJframe.getTitle(), false, true);
    }

    public static XFuture<Boolean> showText(String init,
            @Nullable JFrame _owner,
            String _title,
            boolean _modal) {
        return showText(init, _owner, _title, _modal, false);
    }

    public static XFuture<Boolean> showText(String init,
            @Nullable JFrame _owner) {
        return showText(init, _owner, "", false, false);
    }

    public static XFuture<Boolean> showText(String init,
            @Nullable JFrame _owner,
            String _title,
            boolean _modal,
            boolean forceShow) {

        final XFuture<Boolean> ret = new XFuture<>("showText(" + init + "," + _title + ")");
        if (!disableShowText || (forceShow && !ignoreForceShow)) {
            runOnDispatchThread(() -> {
                JDialog dialog = new JDialog(_owner, _title, _modal);
                ret.complete(showTextPrivate(dialog, init));
            });
        } else {
            ret.complete(false);
        }
        return ret;
    }

    private static void runOnDispatchThread(final Runnable r) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            javax.swing.SwingUtilities.invokeLater(r);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(MultiLineStringJPanel.class.getName());
}
