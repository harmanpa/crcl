package com.github.wshackle.crcl4java.vaadin.webapp;

import com.vaadin.annotations.Push;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import crcl.base.ActuateJointType;
import crcl.base.ActuateJointsType;
import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GetStatusType;
import crcl.base.InitCanonType;
import crcl.base.JointSpeedAccelType;
import crcl.base.JointStatusType;
import crcl.base.MessageType;
import crcl.base.MoveToType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.StopConditionEnumType;
import crcl.base.StopMotionType;
import crcl.base.VectorType;
import crcl.utils.CRCLPosemath;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLSocketException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import rcs.posemath.PmException;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;

/**
 *
 */
@Theme("default_theme")
@Widgetset("com.github.wshackle.crcl4java.vaadin.webapp.Crcl4JavaWidgetset")
@Push
public class CrclClientUI extends UI {

//    private Navigator navigator;
    private CRCLSocket socket;
    private Thread updateThread;
    private CRCLProgramType program;
    private ByteArrayOutputStream recieverOutputStream;
    private int program_index = 0;
    private static Map<String, Resource> browserMap;
    private static final Resource defaultBrowserResource
            = new ExternalResource("http://www.gtri.gatech.edu/canonicalrobotcommandlanguage");
    private boolean running = false;
    private boolean skip_wait_for_done = false;
    private final BrowserFrame browser = new BrowserFrame("Message", defaultBrowserResource);
    private final TextField hostField = new TextField("Host");
    private final TextField portField = new TextField("Port");
    private final Button disconnectButton = new Button("Disconnect");
    private final Button connectButton = new Button("Connect");
    private final Table progTable = new Table("Program");
    private final Label programIndexLabel = new Label("Program Index : " + program_index);
    private final Label cmdIdLbl = new Label("Command ID :" + String.format("%10s", "0"));
    private final Label stateLbl = new Label("State : UNKNOWN");
    private final Label stateDescriptionLbl = new Label("");
    private String lastDescription = "";
    private final Label statusIdLbl = new Label("Status ID :" + String.format("%10s", "0"));
    private final Table posTable = new Table("Position");
    private final Table rotTable = new Table("Rotation");
    private final Resource defaultOverheadImageResource = new ThemeResource("overhead.jpg");
    private final Image overHeadImage = new Image("Overhead", defaultOverheadImageResource);
    private final Resource defaultSideImageResource = new ThemeResource("side.jpg");
    private final Image sideImage = new Image("Side", defaultSideImageResource);
    private static File tempDir = null;
    private final JogButton xPlusJB = new JogButton(" X+ ");
    private final JogButton xMinusJB = new JogButton(" X- ");
    private final Label xJogLabel = new Label(" X: " + String.format("%+6.1f ", 0.0) + " mm ");
    private final JogButton yPlusJB = new JogButton(" Y+ ");
    private final JogButton yMinusJB = new JogButton(" Y- ");
    private final Label yJogLabel = new Label(" Y: " + String.format("%+6.1f ", 0.0) + " mm ");
    private final JogButton zPlusJB = new JogButton(" Z+ ");
    private final JogButton zMinusJB = new JogButton(" Z- ");
    private final Label zJogLabel = new Label(" Z: " + String.format("%+6.1f ", 0.0) + " mm ");
    private final JogButton rollPlusJB = new JogButton(" Roll+ ");
    private final JogButton rollMinusJB = new JogButton(" Roll- ");
    private final Label rollJogLabel = new Label(" Roll: " + String.format("%+6.1f ", 0.0) + " mm ");
    private final JogButton pitchPlusJB = new JogButton(" Pitch+ ");
    private final JogButton pitchMinusJB = new JogButton(" Pitch- ");
    private final Label pitchJogLabel = new Label(" Pitch: " + String.format("%+6.1f ", 0.0) + " mm ");
    private final JogButton yawPlusJB = new JogButton(" Yaw+ ");
    private final JogButton yawMinusJB = new JogButton(" Yaw- ");
    private final Label yawJogLabel = new Label(" Yaw: " + String.format("%+6.1f ", 0.0) + " mm ");
    private final JogButton jogJointPlusButtons[] = new JogButton[]{
        new JogButton("Joint1 +"),
        new JogButton("Joint2 +"),
        new JogButton("Joint3 +"),
        new JogButton("Joint4 +"),
        new JogButton("Joint5 +"),
        new JogButton("Joint6 +"),};
    private final JogButton jogJointMinusButtons[] = new JogButton[]{
        new JogButton("Joint1 -"),
        new JogButton("Joint2 -"),
        new JogButton("Joint3 -"),
        new JogButton("Joint4 -"),
        new JogButton("Joint5 -"),
        new JogButton("Joint6 -"),};
    private final Label jogJointLabels[] = new Label[]{
        new Label("Joint1 : " + String.format("%+6.1f ", 0.0)),
        new Label("Joint2 : " + String.format("%+6.1f ", 0.0)),
        new Label("Joint3 : " + String.format("%+6.1f ", 0.0)),
        new Label("Joint4 : " + String.format("%+6.1f ", 0.0)),
        new Label("Joint5 : " + String.format("%+6.1f ", 0.0)),
        new Label("Joint6 : " + String.format("%+6.1f ", 0.0)),};
    private final HorizontalLayout jogJointLines[] = new HorizontalLayout[6];
    private final CRCLCommandInstanceType instance = new CRCLCommandInstanceType();

    static {
        String tempDirProp = System.getProperty("temp.dir");
        if (tempDirProp != null && tempDirProp.length() > 0) {
            tempDir = new File(tempDirProp);
            if (!tempDir.exists()
                    || !tempDir.isDirectory()
                    || !tempDir.canWrite()) {
                Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, "Can't use tempDir from property temp.dir " + tempDir);
                tempDir = null;
            }
        }
        if (null == tempDir) {
            try {
                File testTempFile = File.createTempFile("test", "suffix");
                tempDir = testTempFile.getParentFile();
            } catch (IOException ex) {
                Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        browserMap = new HashMap<>();
        browserMap.put("MoveThroughToType", new ExternalResource("http://www.gtri.gatech.edu/crclcommands#Link11"));
        browserMap.put("SetLengthUnitsType", new ExternalResource("http://www.gtri.gatech.edu/crclcommands#Link22"));
    }

    public class JogView extends VerticalLayout implements View {

        @Override
        public void enter(ViewChangeListener.ViewChangeEvent event) {
        }

    }

    public class MainView extends HorizontalLayout implements View {

        @Override
        public void enter(ViewChangeListener.ViewChangeEvent event) {
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout navLayout = new VerticalLayout();
        final HorizontalLayout navButtons = new HorizontalLayout();
        final Button mainNavButton = new Button("Main View");
        navButtons.addComponent(mainNavButton);
        final Button jogWorldNavButton = new Button("Jog World View");
        navButtons.addComponent(jogWorldNavButton);
        final Button jogJointNavButton = new Button("Jog Joint View");
        navButtons.addComponent(jogJointNavButton);
        navLayout.addComponent(navButtons);
        Panel panel = new Panel();
        navLayout.addComponent(panel);
        final MainView mainLayout = new MainView();
        final JogView jogJointLayout = new JogView();
        final JogView jogWorldLayout = new JogView();
//        navigator = new Navigator(this, navLayout);
//        navigator.addView("", mainLayout);
//        navigator.addView("Jog", jogLayout);
//        jogNavButton.addClickListener(l -> navigator.navigateTo("Jog"));
//        mainNavButton.addClickListener(l -> navigator.navigateTo(""));
//        navigator.navigateTo("");
        jogJointNavButton.addClickListener(l -> panel.setContent(jogJointLayout));
        jogWorldNavButton.addClickListener(l -> panel.setContent(jogWorldLayout));
        mainNavButton.addClickListener(l -> panel.setContent(mainLayout));
        panel.setContent(mainLayout);
        mainLayout.setMargin(true);
        setContent(navLayout);
        final VerticalLayout leftLayout = new VerticalLayout();
        mainLayout.addComponent(leftLayout);
        final VerticalLayout middleLayout = new VerticalLayout();
        mainLayout.addComponent(middleLayout);
        middleLayout.setMargin(true);
        middleLayout.addComponent(cmdIdLbl);
        middleLayout.addComponent(stateLbl);
        middleLayout.addComponent(stateDescriptionLbl);
        middleLayout.addComponent(statusIdLbl);
        posTable.addContainerProperty("Axis", String.class, null);
        posTable.addContainerProperty("Value", Double.class, null);
        posTable.addItem(new Object[]{"X", 0.0}, 0);
        posTable.addItem(new Object[]{"Y", 0.0}, 1);
        posTable.addItem(new Object[]{"Z", 0.0}, 2);
        posTable.setWidth("220px");
        posTable.setHeight("170px");
        rotTable.addContainerProperty("Axis", String.class, null);
        rotTable.addContainerProperty("I", Double.class, null);
        rotTable.addContainerProperty("J", Double.class, null);
        rotTable.addContainerProperty("K", Double.class, null);
        rotTable.addItem(new Object[]{"X", 1.0, 0.0, 0.0}, "X");
        rotTable.addItem(new Object[]{"Z", 0.0, 0.0, 1.0}, "Z");
        rotTable.setWidth("220px");
        rotTable.setHeight("120px");
        middleLayout.addComponent(overHeadImage);
        middleLayout.addComponent(sideImage);
        middleLayout.addComponent(posTable);
        middleLayout.addComponent(rotTable);
        middleLayout.addComponent(programIndexLabel);
        browser.setWidth("800px");
        browser.setHeight("800px");
//        browser.setWidth(50, Unit.PERCENTAGE);
//        browser.setHeight(100, Unit.PERCENTAGE);
        mainLayout.addComponent(browser);
        hostField.setValue("localhost");
        leftLayout.addComponent(hostField);
        portField.setValue("64444");
        leftLayout.addComponent(portField);
        disconnectButton.setEnabled(false);
        final HorizontalLayout connectDisconnectLayout = new HorizontalLayout();
        connectDisconnectLayout.addComponent(disconnectButton);
        connectDisconnectLayout.addComponent(connectButton);
        leftLayout.addComponent(connectDisconnectLayout);
        final Upload uploadProgram = new Upload("Upload Program", new Upload.Receiver() {

            @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
                recieverOutputStream = new ByteArrayOutputStream();
                return recieverOutputStream;
            }
        });
        progTable.addContainerProperty("Index", Integer.class, null);
        progTable.addContainerProperty("Command", String.class, null);
        progTable.setWidth("500px");
        progTable.setHeight("500px");
        final Button runButton = new Button("Run");
        runButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (null == socket) {
                    connect();
                }
                if (null != socket && null != program && program.getMiddleCommand().size() > 0) {
                    try {
                        if (program_index < 1) {
                            CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
                            instance.setCRCLCommand(program.getInitCanon());
                            instance.getCRCLCommand().setCommandID(BigInteger.ONE);
                            socket.writeCommand(instance);
                        } else {
                            skip_wait_for_done = true;
                        }
                        running = true;
                    } catch (CRCLSocketException ex) {
                        Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        progTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                String cmd = event.getItem().getItemProperty("Command").getValue().toString();
                int spaceindex = cmd.indexOf(' ');
                if (spaceindex > 0) {
                    cmd = cmd.substring(0, spaceindex);
                }
                Resource r = browserMap.get(cmd);
                if (null != r) {
                    CrclClientUI.this.access(() -> {
                        browser.setSource(r);
                    });
                } else {
                    CrclClientUI.this.access(() -> {
                        browser.setSource(defaultBrowserResource);
                    });
                }
            }
        });
        uploadProgram.addSucceededListener(new Upload.SucceededListener() {

            @Override
            public void uploadSucceeded(Upload.SucceededEvent event) {
                if (null != recieverOutputStream) {
                    String string = recieverOutputStream.toString();
                    if (null != string && string.length() > 0) {
                        try {
                            CRCLSocket tmpsocket = socket;
                            if (null == tmpsocket) {
                                tmpsocket = new CRCLSocket();
                            }
                            final CRCLSocket tmpsocketf = tmpsocket;
                            program = tmpsocket.stringToProgram(string, false);
                            program_index = 0;
                            running = false;
                            CrclClientUI.this.access(() -> {
                                programIndexLabel.setValue("Program Index :" + program_index);
                                progTable.clear();
                                try {
                                    for (int i = 0; i < program.getMiddleCommand().size(); i++) {
                                        String tableCommandString = tmpsocketf.commandToSimpleString(program.getMiddleCommand().get(i));
                                        progTable.addItem(new Object[]{i, tableCommandString}, i);
                                        Item item = progTable.getItem(i);
                                        item.<String>getItemProperty("Command").setValue(tableCommandString);

                                    }
                                } catch (ParserConfigurationException | SAXException | IOException ex) {
                                    Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            });
                        } catch (CRCLSocketException ex) {
                            Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        connectButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                connect();
            }
        });
        disconnectButton.addClickListener(
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event
            ) {
                try {
                    if (null != socket) {
                        socket.close();
                        socket = null;
                    }
                    CrclClientUI.this.access(() -> {
                        connectButton.setEnabled(true);
                        disconnectButton.setEnabled(false);
                    });
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        leftLayout.addComponent(uploadProgram);
        final HorizontalLayout runStopLayout = new HorizontalLayout();

        runStopLayout.addComponent(runButton);
        final Button stepButton = new Button("One Step");

        stepButton.addClickListener(
                new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    if (null == socket) {
                        connect();
                    }
                    runOneProgramStep();
                } catch (CRCLSocketException ex) {
                    Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        runStopLayout.addComponent(stepButton);

        final Button stopButton = new Button("Stop");

        stopButton.addClickListener(
                new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                running = false;
            }
        }
        );
        runStopLayout.addComponent(stopButton);
        leftLayout.addComponent(runStopLayout);
        leftLayout.addComponent(progTable);

        final HorizontalLayout xLine = new HorizontalLayout();
        xLine.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        xLine.addComponent(xMinusJB);
        xMinusJB.addMouseDownConsumer(med -> curJogState = JogState.X_MINUS);
        xMinusJB.addMouseUpConsumer(med -> stopMotion());
        xPlusJB.addMouseDownConsumer(med -> curJogState = JogState.X_PLUS);
        xPlusJB.addMouseUpConsumer(med -> stopMotion());

        xLine.addComponent(xPlusJB);
        xLine.addComponent(xJogLabel);
        jogWorldLayout.addComponent(xLine);

        final HorizontalLayout yLine = new HorizontalLayout();
        yLine.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        yLine.addComponent(yMinusJB);
        yMinusJB.addMouseDownConsumer(med -> curJogState = JogState.Y_MINUS);
        yMinusJB.addMouseUpConsumer(med -> stopMotion());
        yPlusJB.addMouseDownConsumer(med -> curJogState = JogState.Y_PLUS);
        yPlusJB.addMouseUpConsumer(med -> stopMotion());

        yLine.addComponent(yPlusJB);
        yLine.addComponent(yJogLabel);
        jogWorldLayout.addComponent(yLine);

        final HorizontalLayout zLine = new HorizontalLayout();
        zLine.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        zLine.addComponent(zMinusJB);
        zMinusJB.addMouseDownConsumer(med -> curJogState = JogState.Z_MINUS);
        zMinusJB.addMouseUpConsumer(med -> stopMotion());
        zPlusJB.addMouseDownConsumer(med -> curJogState = JogState.Z_PLUS);
        zPlusJB.addMouseUpConsumer(med -> stopMotion());

        zLine.addComponent(zPlusJB);
        zLine.addComponent(zJogLabel);
        jogWorldLayout.addComponent(zLine);

        final HorizontalLayout rollLine = new HorizontalLayout();
        rollLine.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        rollLine.addComponent(rollMinusJB);
        rollMinusJB.addMouseDownConsumer(med -> curJogState = JogState.ROLL_MINUS);
        rollMinusJB.addMouseUpConsumer(med -> stopMotion());
        rollPlusJB.addMouseDownConsumer(med -> curJogState = JogState.ROLL_PLUS);
        rollPlusJB.addMouseUpConsumer(med -> stopMotion());

        rollLine.addComponent(rollPlusJB);
        rollLine.addComponent(rollJogLabel);
        jogWorldLayout.addComponent(rollLine);

        final HorizontalLayout pitchLine = new HorizontalLayout();
        pitchLine.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        pitchLine.addComponent(pitchMinusJB);
        pitchMinusJB.addMouseDownConsumer(med -> curJogState = JogState.PITCH_MINUS);
        pitchMinusJB.addMouseUpConsumer(med -> stopMotion());
        pitchPlusJB.addMouseDownConsumer(med -> curJogState = JogState.PITCH_PLUS);
        pitchPlusJB.addMouseUpConsumer(med -> stopMotion());

        pitchLine.addComponent(pitchPlusJB);
        pitchLine.addComponent(pitchJogLabel);
        jogWorldLayout.addComponent(pitchLine);

        final HorizontalLayout yawLine = new HorizontalLayout();
        yawLine.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        yawLine.addComponent(yawMinusJB);
        yawMinusJB.addMouseDownConsumer(med -> curJogState = JogState.YAW_MINUS);
        yawMinusJB.addMouseUpConsumer(med -> stopMotion());
        yawPlusJB.addMouseDownConsumer(med -> curJogState = JogState.YAW_PLUS);
        yawPlusJB.addMouseUpConsumer(med -> stopMotion());

        yawLine.addComponent(yawPlusJB);
        yawLine.addComponent(yawJogLabel);
        jogWorldLayout.addComponent(yawLine);
        for (int i = 0; i < jogJointLines.length; i++) {
            final int jointIndex = i;
            HorizontalLayout hl = new HorizontalLayout();
            jogJointLines[i] = hl;
            hl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
            JogButton jmButton = jogJointMinusButtons[i];
            jmButton.addMouseDownConsumer(med -> {
                curJogState = JogState.JOINT_MINUS;
                jogJointNumber = jointIndex;
            });
            jmButton.addMouseUpConsumer(med -> stopMotion());
            hl.addComponent(jmButton);
            JogButton jpButton = jogJointPlusButtons[i];
            jpButton.addMouseDownConsumer(med -> {
                curJogState = JogState.JOINT_PLUS;
                jogJointNumber = jointIndex;
            });
            jpButton.addMouseUpConsumer(med -> stopMotion());
            hl.addComponent(jpButton);
            hl.addComponent(jogJointLabels[i]);
            jogJointLayout.addComponent(jogJointLines[i]);
        }
        connect();
    }

    public void stopMotion() {
        try {
            curJogState = JogState.NONE;
            StopMotionType stopMotionCmd = new StopMotionType();
            stopMotionCmd.setStopCondition(StopConditionEnumType.IMMEDIATE);
            BigInteger nextId = lastCmdIdSent.add(BigInteger.ONE);
            stopMotionCmd.setCommandID(nextId);
            instance.setCRCLCommand(stopMotionCmd);
            socket.writeCommand(instance);
            lastCmdIdSent = nextId;
        } catch (CRCLSocketException ex) {
            Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            curJogState = JogState.NONE;
        }
    }

    @Override
    public void close() {
        try {
            program = null;
            running = false;
            program_index = 0;
            if (null != updateThread) {
                updateThread.interrupt();
                try {
                    updateThread.join(50);
                } catch (InterruptedException ex) {
//                Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateThread = null;
            }
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException ex) {
//                Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                socket = null;
            }
        } finally {
            super.close();
        }
    }

    @SuppressWarnings("unchecked")
    private void connect() {
        try {
            if (null != socket) {
                socket.close();
            }
            if (null != updateThread) {
                updateThread.interrupt();
                try {
                    updateThread.join();
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
            socket = new CRCLSocket(hostField.getValue(), Integer.valueOf(portField.getValue()));
            InitCanonType initCmd = new InitCanonType();
            lastCmdIdSent = lastCmdIdSent.add(BigInteger.ONE);
            initCmd.setCommandID(lastCmdIdSent);
            instance.setCRCLCommand(initCmd);
            socket.writeCommand(instance);
            CrclClientUI.this.access(() -> {
                connectButton.setEnabled(false);
                disconnectButton.setEnabled(true);
            });
            updateThread = new Thread(() -> {
                try {
                    pollForStatus();
                } catch (CRCLSocketException | InterruptedException ex) {
                    Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            updateThread.start();
        } catch (IOException | CRCLSocketException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    enum JogState {
        NONE,
        X_MINUS, X_PLUS,
        Y_MINUS, Y_PLUS,
        Z_MINUS, Z_PLUS,
        ROLL_MINUS, ROLL_PLUS,
        PITCH_MINUS, PITCH_PLUS,
        YAW_MINUS, YAW_PLUS,
        JOINT_MINUS, JOINT_PLUS;
    }

    private int jogJointNumber = -1;
    private JogState curJogState = JogState.NONE;

    private void pollForStatus() throws CRCLSocketException, InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(200);
            CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
            GetStatusType getStatus = new GetStatusType();
            getStatus.setCommandID(BigInteger.ONE);
            instance.setCRCLCommand(getStatus);
            socket.writeCommand(instance);
            final CRCLStatusType stat = socket.readStatus();

            if (running
                    && null != program
                    && null != stat
                    && null != stat.getCommandStatus()
                    && program_index < program.getMiddleCommand().size()
                    && (skip_wait_for_done
                    || (stat.getCommandStatus().getCommandState() == CommandStateEnumType.CRCL_DONE
                    && (program_index < 1 || program_index + 1 <= stat.getCommandStatus().getCommandID().intValue())))) {
                runOneProgramStep();
            }

            access(() -> {
                updateUIComponents(stat);
            });
        }
    }

    @SuppressWarnings("unchecked")
    private void updateUIComponents(final CRCLStatusType stat) {
        try {
            CommandStatusType cst = stat.getCommandStatus();
            if (null != cst) {
                cmdIdLbl.setValue("Command ID: " + String.format("%10s", cst.getCommandID()));
                stateLbl.setValue("State: " + cst.getCommandState());
//                String description = cst.getStateDescription();
//                if (null == description) {
//                    description = "";
//                }
//                if(cst.getCommandState() == CommandStateEnumType.CRCL_ERROR) {
//                    if(description.length() > 1 && !description.equals(lastDescription)) {
//                        new Notification(description).show(Page.getCurrent());
//                        lastDescription = description;
//                    }
//                }
//                if(description.length() > 40) {
//                    description = description.substring(0, 36)+" ...";
//                }
//                stateDescriptionLbl.setValue(description);
                statusIdLbl.setValue("Status ID: " + cst.getStatusID());
            }
            PoseType pose = CRCLPosemath.getPose(stat);
            if (null != pose) {
                PointType pt = pose.getPoint();
                if (null != pt) {
                    System.out.println("cst.getCommandID() = " + cst.getCommandID());
                    System.out.println("lastCmdIdSent = " + lastCmdIdSent);
                    System.out.println("cst.getCommandID().compareTo(lastCmdIdSent) = " + cst.getCommandID().compareTo(lastCmdIdSent));
                    if (cst.getCommandState() == CommandStateEnumType.CRCL_DONE
                            && cst.getCommandID().compareTo(lastCmdIdSent) >= 0) {
                        MoveToType moveToCmd = new MoveToType();

                        PoseType endPos = new PoseType();
                        endPos.setPoint(new PointType());
                        endPos.setXAxis(new VectorType());
                        endPos.setZAxis(new VectorType());
                        moveToCmd.setEndPosition(endPos);
                        moveToCmd.getEndPosition().getPoint().setX(pose.getPoint().getX());
                        moveToCmd.getEndPosition().getPoint().setY(pose.getPoint().getY());
                        moveToCmd.getEndPosition().getPoint().setZ(pose.getPoint().getZ());
                        moveToCmd.getEndPosition().getXAxis().setI(pose.getXAxis().getI());
                        moveToCmd.getEndPosition().getXAxis().setJ(pose.getXAxis().getJ());
                        moveToCmd.getEndPosition().getXAxis().setK(pose.getXAxis().getK());
                        moveToCmd.getEndPosition().getZAxis().setI(pose.getZAxis().getI());
                        moveToCmd.getEndPosition().getZAxis().setJ(pose.getZAxis().getJ());
                        moveToCmd.getEndPosition().getZAxis().setK(pose.getZAxis().getK());
                        System.out.println("curJogState = " + curJogState);
                        BigInteger nextId;
                        switch (curJogState) {
                            case X_MINUS:
                                moveToCmd.getEndPosition().getPoint().setX(pose.getPoint().getX().add(BigDecimal.valueOf(-5)));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                                break;

                            case X_PLUS:
                                moveToCmd.getEndPosition().getPoint().setX(pose.getPoint().getX().add(BigDecimal.valueOf(5)));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                                break;

                            case Y_MINUS:
                                moveToCmd.getEndPosition().getPoint().setY(pose.getPoint().getY().add(BigDecimal.valueOf(-5)));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                                break;

                            case Y_PLUS:
                                moveToCmd.getEndPosition().getPoint().setY(pose.getPoint().getY().add(BigDecimal.valueOf(5)));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                                break;

                            case Z_MINUS:
                                moveToCmd.getEndPosition().getPoint().setZ(pose.getPoint().getZ().add(BigDecimal.valueOf(-5)));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                                break;

                            case Z_PLUS:
                                moveToCmd.getEndPosition().getPoint().setZ(pose.getPoint().getZ().add(BigDecimal.valueOf(5)));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                                break;

                            case ROLL_MINUS: {
                                PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                                rpy.r -= Math.toRadians(3.0);
                                moveToCmd.setEndPosition(CRCLPosemath.toPoseType(CRCLPosemath.pointToPmCartesian(pt), rpy));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                            }
                            break;

                            case ROLL_PLUS: {
                                PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                                rpy.r += Math.toRadians(3.0);
                                moveToCmd.setEndPosition(CRCLPosemath.toPoseType(CRCLPosemath.pointToPmCartesian(pt), rpy));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                            }
                            break;

                            case PITCH_MINUS: {
                                PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                                rpy.p -= Math.toRadians(3.0);
                                moveToCmd.setEndPosition(CRCLPosemath.toPoseType(CRCLPosemath.pointToPmCartesian(pt), rpy));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                            }
                            break;

                            case PITCH_PLUS: {
                                PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                                rpy.p += Math.toRadians(3.0);
                                moveToCmd.setEndPosition(CRCLPosemath.toPoseType(CRCLPosemath.pointToPmCartesian(pt), rpy));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                            }
                            break;

                            case YAW_MINUS: {
                                PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                                rpy.y -= Math.toRadians(3.0);
                                moveToCmd.setEndPosition(CRCLPosemath.toPoseType(CRCLPosemath.pointToPmCartesian(pt), rpy));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                            }
                            break;

                            case YAW_PLUS: {
                                PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                                rpy.y += Math.toRadians(3.0);
                                moveToCmd.setEndPosition(CRCLPosemath.toPoseType(CRCLPosemath.pointToPmCartesian(pt), rpy));
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                moveToCmd.setCommandID(nextId);
                                instance.setCRCLCommand(moveToCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                            }
                            break;

                            case JOINT_MINUS: {
                                ActuateJointsType actuateJointsCmd = new ActuateJointsType();
                                for (JointStatusType js : stat.getJointStatuses().getJointStatus()) {
                                    ActuateJointType actuateJoint = new ActuateJointType();
                                    actuateJoint.setJointNumber(js.getJointNumber());
                                    actuateJoint.setJointPosition(js.getJointPosition());
                                    if (jogJointNumber == js.getJointNumber().intValue() - 1) {
                                        actuateJoint.setJointPosition(js.getJointPosition().subtract(BigDecimal.ONE));
                                    }
                                    JointSpeedAccelType jas = new JointSpeedAccelType();
                                    jas.setJointSpeed(BigDecimal.ONE);
                                    actuateJoint.setJointDetails(jas);
                                    actuateJointsCmd.getActuateJoint().add(actuateJoint);
                                }
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                actuateJointsCmd.setCommandID(nextId);
                                instance.setCRCLCommand(actuateJointsCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                            }
                            break;

                            case JOINT_PLUS: {
                                ActuateJointsType actuateJointsCmd = new ActuateJointsType();
                                for (JointStatusType js : stat.getJointStatuses().getJointStatus()) {
                                    ActuateJointType actuateJoint = new ActuateJointType();
                                    actuateJoint.setJointNumber(js.getJointNumber());
                                    actuateJoint.setJointPosition(js.getJointPosition());
                                    if (jogJointNumber == js.getJointNumber().intValue() - 1) {
                                        actuateJoint.setJointPosition(js.getJointPosition().add(BigDecimal.ONE));
                                    }
                                    JointSpeedAccelType jas = new JointSpeedAccelType();
                                    jas.setJointSpeed(BigDecimal.ONE);
                                    actuateJoint.setJointDetails(jas);
                                    actuateJointsCmd.getActuateJoint().add(actuateJoint);
                                }
                                nextId = lastCmdIdSent.add(BigInteger.ONE);
                                actuateJointsCmd.setCommandID(nextId);
                                instance.setCRCLCommand(actuateJointsCmd);
                                socket.writeCommand(instance);
                                lastCmdIdSent = nextId;
                            }
                            break;

                            case NONE:
                                break;
                        }
                    }
                    if (null != pt.getX()) {
                        Item it = posTable.getItem(0);
                        if (null != it) {
                            Property ip = it.getItemProperty("Value");
                            if (null != ip) {
                                ip.setValue(pt.getX().doubleValue());
                            }
                        }
                        xJogLabel.setValue(" X: " + String.format("%+6.1f ", pt.getX().doubleValue()) + " mm ");
                    }
                    if (null != pt.getY()) {
                        Item it = posTable.getItem(1);
                        if (null != it) {
                            Property ip = it.getItemProperty("Value");
                            if (null != ip) {
                                ip.setValue(pt.getY().doubleValue());
                            }
                        }
                        yJogLabel.setValue(" Y: " + String.format("%+6.1f ", pt.getY().doubleValue()) + " mm ");
                    }
                    if (null != pt.getZ()) {
                        Item it = posTable.getItem(2);
                        if (null != it) {
                            Property ip = it.getItemProperty("Value");
                            if (null != ip) {
                                ip.setValue(pt.getZ().doubleValue());
                            }
                        }
                        zJogLabel.setValue(" Z: " + String.format("%+6.1f ", pt.getZ().doubleValue()) + " mm ");
                    }
                    PmRpy rpy = CRCLPosemath.toPmRpy(pose);
                    rollJogLabel.setValue(" Roll: " + String.format("%+6.1f ", Math.toDegrees(rpy.r)) + " degrees ");
                    pitchJogLabel.setValue(" Pitch: " + String.format("%+6.1f ", Math.toDegrees(rpy.p)) + " degrees  ");
                    yawJogLabel.setValue(" Yaw: " + String.format("%+6.1f ", Math.toDegrees(rpy.y)) + " degrees  ");
                    for (JointStatusType js : stat.getJointStatuses().getJointStatus()) {
                        jogJointLabels[js.getJointNumber().intValue()-1].setValue("Joint"+js.getJointNumber()+" "+ String.format("%+6.1f ", js.getJointPosition().doubleValue()));
                    }
                }
                VectorType xAxis = pose.getXAxis();
                if (null != xAxis) {
                    Item xItem = rotTable.getItem("X");
                    xItem.getItemProperty("I").setValue(xAxis.getI().doubleValue());
                    xItem.getItemProperty("J").setValue(xAxis.getJ().doubleValue());
                    xItem.getItemProperty("K").setValue(xAxis.getK().doubleValue());
                }
                VectorType zAxis = pose.getZAxis();
                if (null != zAxis) {
                    Item zItem = rotTable.getItem("Z");
                    zItem.getItemProperty("I").setValue(zAxis.getI().doubleValue());
                    zItem.getItemProperty("J").setValue(zAxis.getJ().doubleValue());
                    zItem.getItemProperty("K").setValue(zAxis.getK().doubleValue());
                }
            }
            if (null != tempDir) {
                File dirOverhead = new File(tempDir, "simserver/overhead");
                long max_last_modified = 0;
                File max_last_modified_File = null;
                if (dirOverhead.exists()) {
                    for (File f : dirOverhead.listFiles()) {
                        long last_modified = f.lastModified();
                        if (max_last_modified < last_modified) {
                            max_last_modified = last_modified;
                            max_last_modified_File = f;
                        }
                    }
                    for (File f : dirOverhead.listFiles()) {
                        long last_modified = f.lastModified();
                        if (max_last_modified > last_modified + 2000) {
                            f.delete();
                        }
                    }
                    if (null != max_last_modified_File) {
                        Resource res = new FileResource(max_last_modified_File);
                        overHeadImage.setSource(res);
                        overHeadImage.markAsDirty();
                    }
                }
                File dirSide = new File(tempDir, "simserver/side");
                max_last_modified = 0;
                max_last_modified_File = null;
                if (dirSide.exists()) {
                    for (File f : dirSide.listFiles()) {
                        long last_modified = f.lastModified();
                        if (max_last_modified < last_modified) {
                            max_last_modified = last_modified;
                            max_last_modified_File = f;
                        }
                    }
                    for (File f : dirSide.listFiles()) {
                        long last_modified = f.lastModified();
                        if (max_last_modified > last_modified + 2000) {
                            f.delete();
                        }
                    }
                    if (null != max_last_modified_File) {
                        Resource res = new FileResource(max_last_modified_File);
                        sideImage.setSource(res);
                        sideImage.markAsDirty();
                    }
                }
            }
        } catch (CRCLSocketException | Property.ReadOnlyException | PmException ex) {
            Logger.getLogger(CrclClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BigInteger lastCmdIdSent = BigInteger.ONE;

    @SuppressWarnings("unchecked")
    private void runOneProgramStep() throws CRCLSocketException {
        instance.setCRCLCommand(program.getMiddleCommand().get(program_index));
        instance.getCRCLCommand().setCommandID(BigInteger.valueOf(program_index + 2));
        lastCmdIdSent = instance.getCRCLCommand().getCommandID();
        socket.writeCommand(instance);
        program_index++;
        skip_wait_for_done = false;
        if (program.getMiddleCommand().get(program_index) instanceof crcl.base.MessageType) {
            MessageType msg = (MessageType) program.getMiddleCommand().get(program_index);
            final String msgString = msg.getMessage();
            CrclClientUI.this.access(() -> {
                Notification n = new Notification("Program Paused to Show Message. Review the message to the right, then click Run to continue.");
                n.setDelayMsec(5000);
                n.show(Page.getCurrent());
                if (msgString.startsWith("http:") || msgString.startsWith("https:")) {
                    browser.setSource(new ExternalResource(msgString));
                } else {
                    browser.setSource(new StreamResource(
                            () -> new ByteArrayInputStream(("<html><body>" + msgString + "</body></html>").getBytes()),
                            System.currentTimeMillis() + "msg.html"));
                }
                running = false;
                program_index++;
            });
            running = false;
        }
        CrclClientUI.this.access(() -> {
            Item item = progTable.getItem(program_index);
            if (null != item) {
                programIndexLabel.setValue("Program Index :" + program_index);
                progTable.select(program_index);
                progTable.setCurrentPageFirstItemId(program_index);
            }
        });
    }
    private static final Logger LOGGER = Logger.getLogger(CrclClientUI.class
            .getName());

    @WebServlet(urlPatterns = "/*", name = "CrclClientUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CrclClientUI.class, productionMode = false)
    public static class CrclClientUIServlet extends VaadinServlet {
    }
}
