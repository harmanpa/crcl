/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl.ui.misc;


import static crcl.ui.IconImages.SERVER_IMAGE;
import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;

/**
 *
 * @author shackle
 */
public class ServerSensorJFrame extends javax.swing.JFrame {

    transient private FingerPressureSensorData data;

    public static final String PROP_DATA = "data";

    /**
     * Get the value of data
     *
     * @return the value of data
     */
    public FingerPressureSensorData getData() {
        return data;
    }

    /**
     * Set the value of data
     *
     * @param data new value of data
     */
    public void setData(FingerPressureSensorData data) {
        FingerPressureSensorData oldData = this.data;
        this.data = data;
        propertyChangeSupport.firePropertyChange(PROP_DATA, oldData, data);
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener listener to be notified of changes
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener previously added listener to be removed
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Creates new form ServerSensorJFrame
     */
    public ServerSensorJFrame() {
        initComponents();
        setIconImage(SERVER_IMAGE);
    }
    
    @Override
    final public void setIconImage(Image image) {
        super.setIconImage(image);
    }


    public void setCommandString(String s) {
        try {
            if (javax.swing.SwingUtilities.isEventDispatchThread()) {
                jTextFieldCommand.setText(s);
            } else {
                javax.swing.SwingUtilities.invokeAndWait(() -> jTextFieldCommand.setText(s));
            }
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDirectoryString(String s) {
        try {
            if (javax.swing.SwingUtilities.isEventDispatchThread()) {
                jTextFieldDirectory.setText(s);
            } else {
                javax.swing.SwingUtilities.invokeAndWait(() -> jTextFieldDirectory.setText(s));
            }
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCommandString() {
        return jTextFieldCommand.getText();
    }

    public String getDirectoryString() {
        return jTextFieldDirectory.getText();
    }

    transient private Runnable onStopRunnable = null;

    public void setOnStopRunnable(Runnable r) {
        this.onStopRunnable = r;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxRun = new javax.swing.JCheckBox();
        jTextFieldCommand = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldDirectory = new javax.swing.JTextField();
        jButtonDirBrowse = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaConsoleOutput = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSocketOutput = new javax.swing.JTextArea();
        jButtonClearOutput = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFingerA = new javax.swing.JTextField();
        jTextFieldFingerB = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Finger Pressure Sensor Server");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jCheckBoxRun.setText("Run");
        jCheckBoxRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxRunActionPerformed(evt);
            }
        });

        jTextFieldCommand.setText("C:\\Program Files\\nodejs\\node.exe sensorServer.js");

        jLabel1.setText("Directory:");

        jTextFieldDirectory.setText("C:\\Users\\Public\\Documents\\FingerPressureSensorNodeJS");
        jTextFieldDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDirectoryActionPerformed(evt);
            }
        });

        jButtonDirBrowse.setText("Browse");
        jButtonDirBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDirBrowseActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Console Output"));

        jTextAreaConsoleOutput.setColumns(20);
        jTextAreaConsoleOutput.setRows(5);
        jScrollPane1.setViewportView(jTextAreaConsoleOutput);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Socket Output"));

        jTextAreaSocketOutput.setColumns(20);
        jTextAreaSocketOutput.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSocketOutput);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonClearOutput.setText("Clear Output");
        jButtonClearOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearOutputActionPerformed(evt);
            }
        });

        jLabel2.setText("Finger A: ");

        jTextFieldFingerA.setEditable(false);
        jTextFieldFingerA.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldFingerA.setText("0.0");

        jTextFieldFingerB.setEditable(false);
        jTextFieldFingerB.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldFingerB.setText("0.0");

        jLabel3.setText("Finger B: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBoxRun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCommand))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDirectory, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDirBrowse))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFingerA, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFingerB, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonClearOutput)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxRun)
                    .addComponent(jTextFieldCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDirBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonClearOutput)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldFingerA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldFingerB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDirectoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDirectoryActionPerformed

    transient private Process internalProcess;
    transient private Thread monitorOutputThread;
    transient private Thread monitorErrorThread;
    transient private Thread readSocketThread;

    private final int SERVER_PORT_NUM = 4567;

    private void readSocket() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Socket s = new Socket("localhost", SERVER_PORT_NUM);
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            String line = null;
            while (null != (line = br.readLine()) && !Thread.currentThread().isInterrupted()) {
                System.out.println("line = " + line);
                final String str = line;

                FingerPressureSensorData newData = FingerPressureSensorData.fromJSON(str);
                System.out.println("data.sensor_values.FSR_finger_A_distal = " + newData.getFSR_finger_A_distal());
                System.out.println("data.sensor_values.FSR_finger_B_distal = " + newData.getFSR_finger_B_distal());
                setData(newData);
                javax.swing.SwingUtilities.invokeLater(() -> {
                    socketAppend(str + "\n");
                    jTextFieldFingerA.setText(Double.toString(newData.getFSR_finger_A_distal()));
                    jTextFieldFingerB.setText(Double.toString(newData.getFSR_finger_B_distal()));
                });
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString()));
        }
    }

    private List<String> consoleStrings = new LinkedList<String>();

    private void consoleAppend(String s) {
        if (consoleStrings.size() > maxLoggedStrings) {
            consoleStrings.remove(0);
        }
        consoleStrings.add(s);
        String txt = consoleStrings.stream().collect(Collectors.joining());
        jTextAreaConsoleOutput.setText(txt);
        jTextAreaConsoleOutput.setCaretPosition(txt.length());
    }
    private int maxLoggedStrings = 50;

    public int getMaxLoggedStrings() {
        return maxLoggedStrings;
    }

    public void setMaxLoggedStrings(int maxLoggedStrings) {
        this.maxLoggedStrings = maxLoggedStrings;
    }

    private List<String> socketStrings = new LinkedList<String>();

    private void socketAppend(String s) {
        if (consoleStrings.size() > maxLoggedStrings) {
            consoleStrings.remove(0);
        }
        consoleStrings.add(s);
        String txt = consoleStrings.stream().collect(Collectors.joining());
        jTextAreaSocketOutput.setText(txt);
        jTextAreaSocketOutput.setCaretPosition(txt.length());
    }

    private void monitorInternalProcessOutput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(internalProcess.getInputStream()))) {
            String line = null;
            while (null != (line = br.readLine()) && !Thread.currentThread().isInterrupted()) {
                System.out.println(line);
                if (line.startsWith("waiting for clients") && null == readSocketThread) {
                    readSocketThread = new Thread(this::readSocket, "readFingerSensorSocket");
                    readSocketThread.start();
                }
                final String s = line;
                javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(s + "\n"));
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString()));
        }
    }

    private void monitorInternalProcessError() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(internalProcess.getErrorStream()))) {
            String line = null;
            while (null != (line = br.readLine()) && !Thread.currentThread().isInterrupted()) {
                System.err.println(line);
                final String s = line;
                javax.swing.SwingUtilities.invokeLater(() -> consoleAppend("\nERROR:" + s + "\n"));
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString() + "\n"));
        }
    }

    private void jCheckBoxRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxRunActionPerformed
        try {
            stop();
            jTextAreaConsoleOutput.setText("");
            jTextAreaSocketOutput.setText("");
            if (this.jCheckBoxRun.isSelected()) {
                start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString()));
        }
    }//GEN-LAST:event_jCheckBoxRunActionPerformed

    public void start() throws IOException {
        internalProcess = new ProcessBuilder(jTextFieldCommand.getText().split("[ \t]+"))
                .directory(new File(jTextFieldDirectory.getText()))
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start();
        monitorOutputThread = new Thread(this::monitorInternalProcessOutput, "monitorFingerSensorConsole");
        monitorOutputThread.start();
        monitorErrorThread = new Thread(this::monitorInternalProcessError, "monitorFingerSensorError");
        monitorErrorThread.start();
        if (!jCheckBoxRun.isSelected()) {
            jCheckBoxRun.setSelected(true);
        }
    }

    public void stop() {
        if (null != onStopRunnable) {
            onStopRunnable.run();
        }
        if (null != internalProcess) {
            try {
                internalProcess.destroyForcibly().waitFor(10, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            internalProcess = null;
        }
        if (null != monitorOutputThread) {
            monitorOutputThread.interrupt();
            try {
                monitorOutputThread.join(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            monitorOutputThread = null;
        }
        if (null != monitorErrorThread) {
            monitorErrorThread.interrupt();
            try {
                monitorErrorThread.join(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            monitorErrorThread = null;
        }
        if (null != readSocketThread) {
            readSocketThread.interrupt();
            try {
                readSocketThread.join(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            readSocketThread = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        stop();
        super.finalize();
    }


    private void jButtonDirBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDirBrowseActionPerformed
        JFileChooser chooser = new JFileChooser(new File(jTextFieldDirectory.getText()));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = chooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                jTextFieldDirectory.setText(chooser.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(ServerSensorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                javax.swing.SwingUtilities.invokeLater(() -> consoleAppend(ex.toString()));
            }
        }
    }//GEN-LAST:event_jButtonDirBrowseActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        stop();
        onStopRunnable = null;
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stop();
        onStopRunnable = null;
    }//GEN-LAST:event_formWindowClosing

    private void jButtonClearOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearOutputActionPerformed
        this.jTextAreaConsoleOutput.setText("");
        this.jTextAreaSocketOutput.setText("");
    }//GEN-LAST:event_jButtonClearOutputActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerSensorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerSensorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerSensorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerSensorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerSensorJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClearOutput;
    private javax.swing.JButton jButtonDirBrowse;
    private javax.swing.JCheckBox jCheckBoxRun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaConsoleOutput;
    private javax.swing.JTextArea jTextAreaSocketOutput;
    private javax.swing.JTextField jTextFieldCommand;
    private javax.swing.JTextField jTextFieldDirectory;
    private javax.swing.JTextField jTextFieldFingerA;
    private javax.swing.JTextField jTextFieldFingerB;
    // End of variables declaration//GEN-END:variables
}
