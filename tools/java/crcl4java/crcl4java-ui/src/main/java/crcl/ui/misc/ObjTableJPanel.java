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
import crcl.utils.XpathUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.xml.sax.SAXException;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 * @param <T> Type of object to be shown/modified.
 */
@SuppressWarnings("nullness")
public class ObjTableJPanel<T> extends javax.swing.JPanel {

    /**
     * Creates new form CmdTableJPanel
     */
    @SuppressWarnings("initialization")
    public ObjTableJPanel() {
        initComponents();
        setupTableSelection();
    }

    transient private XpathUtils xpu = null;
    private File[] schemaFiles = null;
    private String defaultDocumentation = null;

    private void setupTableSelection() {

        ListSelectionModel cellSelectionModel = this.jTable1.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    jButtonNew.setEnabled(false);
                    jButtonDelete.setEnabled(false);
                    jButtonAddToList.setEnabled(false);
                    jButtonRemoveFromList.setEnabled(false);
                    jButtonEditMultiLineText.setEnabled(false);
                    int row = jTable1.getSelectedRow();
                    DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
                    if (row < 0 || row > tm.getRowCount()) {
                        return;
                    }
                    String type = (String) tm.getValueAt(row, 0);
                    String name = (String) tm.getValueAt(row, 1);
                    String typenoparams = removeTypeParams(type);
                    Class clss = null;
                    if (typenoparams.equals("boolean")) {
                        clss = boolean.class;
                    } else {
                        clss = Class.forName(typenoparams);
                    }
                    String documentation = null;
                    String doctype = typenoparams;
                    Object cur_obj = getObject(name);
                    if (null != cur_obj) {
                        doctype = cur_obj.getClass().getCanonicalName();
                    }
                    int pindex = doctype.lastIndexOf('.');
                    if (pindex > 0) {
                        doctype = doctype.substring(pindex + 1);
                    }
                    if (null != xpu && null != schemaFiles) {
                        try {
                            documentation = xpu.getDocumentation(schemaFiles, doctype);
                        } catch (SAXException | IOException | XPathExpressionException | ParserConfigurationException ex) {
                            Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (null != documentation) {
                            jTextPane1.setText(documentation);
                        } else if (null != defaultDocumentation) {
                            jTextPane1.setText(defaultDocumentation);
                        }
                        jTextPane1.setCaretPosition(0);
                        jScrollPaneDocumentation.getVerticalScrollBar().setValue(0);
                    }
                    if (name.equals("this") || row == 0) {
                        return;
                    }
                    if (isList(getParentObject(name).getClass())) {
                        jButtonRemoveFromList.setEnabled(true);
                        return;
                    }
                    if (isCompound(clss)) {
                        jButtonNew.setEnabled(true);
                        if (null != getObject(name)) {
                            jButtonDelete.setEnabled(true);
                        }
                        return;
                    }
                    if (isList(clss)) {
                        jButtonAddToList.setEnabled(true);
                        return;
                    }
                    if (clss.equals(String.class)) {
                        jButtonEditMultiLineText.setEnabled(true);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private final Map<Integer, TableCellEditor> editorMap = new HashMap<>();
    private final Map<Integer, DefaultTableCellRenderer> rendererMap = new HashMap<>();
    private final Map<Integer, Color> colorMap = new HashMap<>();
    private final Set<Integer> noneditableSet = new HashSet<>();

    private JTable createJTable() {
        return new JTable() {

            @Override
            public Component prepareRenderer(
                    TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (this.getSelectedRow() == row) {
                    return c;
                }
                Color color = colorMap.get(row);
                if (color != null) {
                    c.setBackground(color);
                }
                return c;
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 2 && editorMap.containsKey(row)) {
                    return editorMap.get(row);
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 2 && rendererMap.containsKey(row)) {
                    return rendererMap.get(row);
                }
                return super.getCellRenderer(row, column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                boolean returnvar = false;
                returnvar = super.isCellEditable(row, column);
                if (returnvar && column == 2 && noneditableSet.contains(row)) {
                    return false;
                }
                return returnvar;
            }

        };
    }

    private class NewDeletePanel extends JPanel {

        private final JButton jButtonNew = new JButton("New");
        private final JButton jButtonDelete = new JButton("Delete");

        NewDeletePanel() {
            super();

//                this.setPreferredSize(new Dimension(400,400));
//                this.setSize(new Dimension(400,400));
//                this.setMinimumSize(new Dimension(400,400));
            jButtonNew.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setNewTableItem();
                }
            });
            jButtonDelete.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //setNewTableItem();
                    System.err.println("FIXME: implement delete");
                }
            });
            this.add(jButtonNew);
            this.add(jButtonDelete);
        }
    };

    Map<Integer, NewDeletePanel> pnlMap = new HashMap<>();

//    private class MyTableModel extends DefaultTableModel {
//
//        final private DefaultTableModel orig;
//
//        public MyTableModel(final DefaultTableModel _orig) {
//            super();
//            this.orig = _orig;
//        }
//
//
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            return column != 2 && null == pnlMap.get(row);
//        }
//
//        @Override
//        public int getRowCount() {
//            if(null == orig) {
//                return 0;
//            }
//            return orig.getRowCount();
//        }
//
//        @Override
//        public int getColumnCount() {
//            if(null == orig) {
//                return 3;
//            }
//            return orig.getColumnCount();
//        }
//
//        @Override
//        public String getColumnName(int columnIndex) {
//           return orig.getColumnName(columnIndex);
//        }
//
//        @Override
//        public Class<?> getColumnClass(int columnIndex) {
//           return orig.getColumnClass(columnIndex);
//        }
//
//        @Override
//        public Object getValueAt(int rowIndex, int columnIndex) {
//           return orig.getValueAt(rowIndex, columnIndex);
//        }
//
//        @Override
//        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//                 orig.setValueAt(aValue, rowIndex, columnIndex);
//        }
//
//        @Override
//        public void addTableModelListener(TableModelListener l) {
//            orig.addTableModelListener(l);
//        }
//
//        @Override
//        public void removeTableModelListener(TableModelListener l) {
//            orig.removeTableModelListener(l);
//        }
//
//    };
    private class MyTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component returnvar = null;
            if (column == 2) {
                try {
                    DefaultTableModel tm = (DefaultTableModel) table.getModel();
                    String type = (String) tm.getValueAt(row, 0);
                    String typenoparams = removeTypeParams(type);
                    Class clss = Class.forName(typenoparams);
                    pnlMap.remove(row);
                    if (isCompound(clss)) {
                        NewDeletePanel pnl = new NewDeletePanel();
                        pnlMap.put(row, pnl);
                        int rheight = table.getRowHeight(row);
                        table.setRowHeight(row, Math.max(rheight, pnl.getPreferredSize().height));
                        return pnl;
                    }
//                    if (isList(clss)) {
//                        jButtonAddToList.setEnabled(true);
//                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            returnvar = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return returnvar;
        }
    };

    private static String removeTypeParams(String type) {
        String typenoparams = type;
        int ltindex = typenoparams.indexOf('<');
        if (ltindex > 0) {
            typenoparams = typenoparams.substring(0, ltindex);
        }
        return typenoparams;
    }

    private static String getTypeParams(String type) {
        String params = "";
        int ltindex = type.indexOf('<');
        int gtindex = type.indexOf('>');
        if (gtindex > 0 && ltindex > 0) {
            params = type.substring(ltindex + 1, gtindex);
        }
        return params;
    }

    private static boolean isList(Class clss) {
        return List.class.isAssignableFrom(clss);
    }

    private static boolean isCompound(Class clss) {
        if (clss.isPrimitive()) {
            return false;
        }
        if (List.class.isAssignableFrom(clss)) {
            return false;
        }
        if (String.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Boolean.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Float.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Double.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Short.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Integer.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Long.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.math.BigDecimal.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.math.BigInteger.class.isAssignableFrom(clss)) {
            return false;
        }
        if (clss.isEnum()) {
            return false;
        }
        if (null == classes) {
            classes = getClasses();
        }
        List<Class> availClasses = getAssignableClasses(clss, classes);
        return availClasses.size() > 0;
    }

    private T obj;

    /**
     * Get the value of obj
     *
     * @return the value of obj
     */
    @Nullable public T getObj() {
        return obj;
    }

    private void updateObjFromTable() {
        DefaultTableModel tm = (DefaultTableModel) this.jTable1.getModel();
        final int row_count = tm.getRowCount();
        for (int i = 0; i < row_count; i++) {
            try {
                String type = (String) tm.getValueAt(i, 0);
                String name = (String) tm.getValueAt(i, 1);
                Object o = tm.getValueAt(i, 2);
                this.setObjectForName(type, name, o);

            } catch (SecurityException | IllegalArgumentException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    final private Color myBlue = new Color(150, 150, 255);

    private Object getDefaultForClass(Class clss) throws InstantiationException, IllegalAccessException {
        if (String.class.isAssignableFrom(clss)) {
            return "";
        }
        if (java.math.BigDecimal.class.isAssignableFrom(clss)) {
            return BigDecimal.ZERO;
        }
        if (java.math.BigInteger.class.isAssignableFrom(clss)) {
            return BigInteger.ONE;
        }
        if (Boolean.class.isAssignableFrom(clss)) {
            return Boolean.FALSE;
        }
        if (Double.class.isAssignableFrom(clss)) {
            return 0.0;
        }
        if (Float.class.isAssignableFrom(clss)) {
            return 0.0f;
        }
        if (Short.class.isAssignableFrom(clss)) {
            return (short) 0;
        }
        if (Integer.class.isAssignableFrom(clss)) {
            return 0;
        }
        if (Long.class.isAssignableFrom(clss)) {
            return Long.valueOf(0);
        }
        if (clss.isEnum()) {
            return clss.getEnumConstants()[0];
        }
        return clss.newInstance();
    }

    private Field getField(Class clss, String name) {
        Field f = null;
        if (clss == null) {
            return null;
        }
        try {
            f = clss.getField(name);
        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.FINEST, "exception normally ignored", ex);
        }
        if (null != f) {
            return f;
        }
        try {
            f = clss.getDeclaredField(name);
        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.FINEST, "exception normally ignored", ex);
        }
        if (null != f) {
            return f;
        }
        return getField(clss.getSuperclass(), name);
    }

    @SuppressWarnings("unchecked")
    private void addObjectToTable(String name_prefix,
            DefaultTableModel tm, Object o, Class<?> clss) {
        Class<?> oclss = clss;
        if (null != o) {
            oclss = o.getClass();
        }
        if (null != defaultDocumentation) {
            jTextPane1.setText(defaultDocumentation);
            jTextPane1.setCaretPosition(0);
            jScrollPaneDocumentation.getVerticalScrollBar().setValue(0);
        }

        Method ma[] = oclss.getMethods();
        SortedSet<String> names = new TreeSet<>();
        Map<String, Method> map = new HashMap<>();
        for (Method m : ma) {
            try {
                if (m.getName().startsWith("get") && m.getParameterCount() == 0) {
                    String mname = m.getName().substring(3);
                    if (mname.equals("Class")) {
                        continue;
                    }
                    names.add(mname);
                    map.put(mname, m);
                }
            } catch (Exception e) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.FINEST, "exception normally ignored", e);
            }
            try {
                if (m.getName().startsWith("is") && m.getParameterCount() == 0) {
                    String mname = m.getName().substring(2);
                    if (mname.equals("Class")) {
                        continue;
                    }
                    names.add(mname);
                    map.put(mname, m);
                }
            } catch (Exception e) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.FINEST, "exception normally ignored", e);
            }
        }
        for (String name : names) {
            try {
                Method m = map.get(name);
                Class mclss = null;
                if (m.getParameterCount() == 1) {
                    mclss = m.getParameterTypes()[0];
                } else {
                    mclss = m.getReturnType();
                }
                Method mget = null;
                try {
                    mget = oclss.getMethod("get" + name);

                } catch (NoSuchMethodException noSuchMethodException) {
                } catch (SecurityException securityException) {
                }
                try {
                    mget = clss.getMethod("is" + name);

                } catch (NoSuchMethodException noSuchMethodException) {
                } catch (SecurityException securityException) {
                }
                Object mo = null;
                if (null != o && null != mget) {
                    try {
                        mo = mget.invoke(o);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Method subma[] = mclss.getMethods();
                int numsetters = 0;
                for (Method subm : subma) {
                    if (subm.getName().startsWith("set") && subm.getParameterCount() == 1) {
                        numsetters++;
                    }
                }
                String type = mclss.getCanonicalName();
                String list_item_type = null;
                if (List.class.isAssignableFrom(mclss) && null != mget) {
                    try {
                        ParameterizedType prt = (ParameterizedType) mget.getGenericReturnType();
                        list_item_type = prt.getActualTypeArguments()[0].getTypeName();
                        type += "<" + list_item_type + ">";
                    } catch (Exception e) {
                    }
                }
                this.colorMap.put(tm.getRowCount(), Color.LIGHT_GRAY);
                if (mclss.isEnum()) {
                    JComboBox comboBox = new JComboBox();
                    for (Object oc : mclss.getEnumConstants()) {
                        comboBox.addItem(oc);
                    }
                    this.editorMap.put(tm.getRowCount(), new DefaultCellEditor(comboBox));
                }
                if (mclss.equals(boolean.class)) {
                    if (mo == null) {
                        mo = Boolean.FALSE;
                    }
                    final JCheckBox jc = new JCheckBox("false");
                    jc.setBackground(Color.LIGHT_GRAY);
                    jc.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (jc.isSelected()) {
                                jc.setText("true");
                            } else {
                                jc.setText("false");
                            }
                        }
                    });
                    this.rendererMap.put(tm.getRowCount(), new DefaultTableCellRenderer() {

                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            return jc;
                        }
                    });
                    this.editorMap.put(tm.getRowCount(), new DefaultCellEditor(jc) {

                        @Override
                        public Object getCellEditorValue() {
                            if (jc.isSelected()) {
                                return Boolean.TRUE;
                            }
                            return Boolean.FALSE;
                        }

                    });

                }
                String fieldName = name.substring(0, 1).toLowerCase() + name.substring(1);
                if (null == mo) {
                    Field field = this.getField(clss, fieldName);
                    if (null != field) {
                        Annotation annotations[] = field.getDeclaredAnnotations();
                        for (Annotation a : annotations) {
                            if (XmlElement.class.isAssignableFrom(a.annotationType())) {
                                XmlElement xe = (XmlElement) a;
                                if (xe.required()) {
                                    try {
                                        mo = this.getDefaultForClass(mclss);
                                        if (null != mo) {
                                            Method mset = oclss.getMethod("set" + name, mclss);
                                            mset.invoke(o, mo);
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
                Object oa[] = new Object[]{type, name_prefix + name, mo};
                tm.addRow(oa);
                if (isCompound(mclss)) {
                    this.noneditableSet.add(tm.getRowCount() - 1);
                }
                if (mclss.equals(String.class) && mo != null
                        && mo.toString().contains("\n")) {
                    final JTextArea jta = new JTextArea();
                    this.noneditableSet.add(tm.getRowCount() - 1);
                    this.rendererMap.put(tm.getRowCount() - 1, new DefaultTableCellRenderer() {

                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            jta.setText(value.toString());
                            table.setRowHeight(row, Math.max(table.getRowHeight(row), jta.getPreferredSize().height));
                            return jta;
                        }
                    });
                }
                if (List.class.isAssignableFrom(mclss) && null != mget && null != mo) {
                    this.noneditableSet.add(tm.getRowCount() - 1);
                    this.colorMap.put(tm.getRowCount() - 1, Color.green.brighter());
                    List l = (List) mo;
                    Class lclss = Object.class;
                    try {
                        ParameterizedType prt = (ParameterizedType) mget.getGenericReturnType();
                        lclss = Class.forName(prt.getActualTypeArguments()[0].getTypeName());
                    } catch (Exception e) {
                    }
                    for (int i = 0; i < l.size(); i++) {
                        Object lo = l.get(i);
                        if (null != lo) {
                            String item_name = name_prefix + name + ".get(" + i + ")";
                            oa = new Object[]{list_item_type, item_name, lo};
                            this.colorMap.put(tm.getRowCount(), Color.yellow);
                            tm.addRow(oa);
                            addObjectToTable(item_name + ".", tm, lo, lclss);
                        }
                    }
                } else if (numsetters > 0 && mo != null
                        && !mclss.equals(java.math.BigDecimal.class)
                        && !mclss.equals(java.math.BigInteger.class)) {
                    this.noneditableSet.add(tm.getRowCount() - 1);
                    this.colorMap.put(tm.getRowCount() - 1, myBlue);
                    addObjectToTable(name_prefix + name + ".", tm, mo, mclss);
                }
            } catch (SecurityException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Set the value of obj
     *
     * @param obj new value of obj
     */
    public void setObj(T obj) {
        this.obj = obj;
        updateTableFromObject();
    }

    private void updateTableFromObject() {
        DefaultTableModel tm = (DefaultTableModel) this.jTable1.getModel();
        tm.setRowCount(0);
        Class clss = obj.getClass();
        rendererMap.clear();
        editorMap.clear();
        colorMap.clear();
        noneditableSet.clear();
        Object oa[] = new Object[]{obj.getClass().getCanonicalName(), "this", obj};
        tm.addRow(oa);
        colorMap.put(0, myBlue);
        addObjectToTable("", tm, obj, clss);
    }

    private JDialog dialog = null;
    private boolean cancelled = false;
    transient private Function<T,XFuture<Boolean>>  isValid = null;

    private static <T> T editObjectPriv(JDialog _dialog, T _obj,
            XpathUtils xpu,
            File schemaFiles[],
            Function<T,XFuture<Boolean>> isValid) {
        ObjTableJPanel<T> panel = new ObjTableJPanel<>();
        panel.dialog = _dialog;
        panel.setObj(_obj);
        panel.isValid = isValid;
        String clssname = _obj.getClass().getCanonicalName();
        int pindex = clssname.lastIndexOf('.');
        if (pindex > 0) {
            clssname = clssname.substring(pindex + 1);
        }
        if (null != xpu && null != schemaFiles) {
            String documentation = null;
            try {
                documentation = xpu.getDocumentation(schemaFiles, clssname);
            } catch (SAXException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            panel.xpu = xpu;
            panel.schemaFiles = schemaFiles;
            if (null != documentation) {
                panel.jTextPane1.setText(documentation);
                panel.defaultDocumentation = documentation;
            }
        }
        panel.jTextPane1.setCaretPosition(0);
        panel.jScrollPaneDocumentation.getVerticalScrollBar().setValue(0);
        _dialog.add(panel);
        _dialog.pack();
        _dialog.setVisible(true);
        if (panel.cancelled) {
            return null;
        }
        return panel.getObj();
    }

    public static <T> T editObject(T _obj, Frame _owner, String _title, boolean _modal, XpathUtils xpu, File schemaFiles[], Function<T,XFuture<Boolean>> isValid) {
        JDialog dialog = new JDialog(_owner, _obj.getClass().getCanonicalName() + ":" + _title, _modal);
        return editObjectPriv(dialog, _obj, xpu, schemaFiles, isValid);
    }

    public static <T> T editObject(T _obj, XpathUtils xpu, File schemaFiles[],
            Function<T,XFuture<Boolean>> isValid) {
        JDialog dialog = new JDialog();
        dialog.setTitle(_obj.getClass().getCanonicalName());
        dialog.setModal(true);
        return editObjectPriv(dialog, _obj, xpu, schemaFiles, isValid);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = createJTable();
        jButtonNew = new javax.swing.JButton();
        jButtonAddToList = new javax.swing.JButton();
        jButtonRemoveFromList = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonEditMultiLineText = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPaneDocumentation = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        FormListener formListener = new FormListener();

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(formListener);

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(formListener);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Type", "Name", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButtonNew.setText("New");
        jButtonNew.setEnabled(false);
        jButtonNew.addActionListener(formListener);

        jButtonAddToList.setText("Add To List");
        jButtonAddToList.setEnabled(false);
        jButtonAddToList.addActionListener(formListener);

        jButtonRemoveFromList.setText("Remove From List");
        jButtonRemoveFromList.setEnabled(false);
        jButtonRemoveFromList.addActionListener(formListener);

        jButtonDelete.setText("Delete");
        jButtonDelete.setEnabled(false);
        jButtonDelete.addActionListener(formListener);

        jButtonEditMultiLineText.setText("Edit Multi-line Text");
        jButtonEditMultiLineText.setEnabled(false);
        jButtonEditMultiLineText.addActionListener(formListener);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Color Code"));

        jLabel1.setBackground(new java.awt.Color(150, 150, 255));
        jLabel1.setText("Compound Type");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(0, 255, 0));
        jLabel2.setText("List");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("List Item");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Documentation"));

        jTextPane1.setEditable(false);
        jScrollPaneDocumentation.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneDocumentation)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneDocumentation, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButtonNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddToList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveFromList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEditMultiLineText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                        .addComponent(jButtonOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOK)
                    .addComponent(jButtonNew)
                    .addComponent(jButtonAddToList)
                    .addComponent(jButtonRemoveFromList)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonEditMultiLineText))
                .addContainerGap())
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButtonCancel) {
                ObjTableJPanel.this.jButtonCancelActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonOK) {
                ObjTableJPanel.this.jButtonOKActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonNew) {
                ObjTableJPanel.this.jButtonNewActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonAddToList) {
                ObjTableJPanel.this.jButtonAddToListActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonRemoveFromList) {
                ObjTableJPanel.this.jButtonRemoveFromListActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonDelete) {
                ObjTableJPanel.this.jButtonDeleteActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonEditMultiLineText) {
                ObjTableJPanel.this.jButtonEditMultiLineTextActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.cancelled = true;
        this.dialog.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        this.cancelled = false;
        this.updateObjFromTable();
        if (null != this.isValid) {
            // The predicate is potentially blocking. To avoid blocking it may return
            // a CompleteableFuture<Boolean> which is then scheduled to set the dialogs
            // visible state when/if it completes.
            this.isValid.apply(this.obj)
                    .thenAccept(b -> runOnDispatchThread(() -> this.dialog.setVisible(!b)));
            return;
        }
        this.dialog.setVisible(false);
    }//GEN-LAST:event_jButtonOKActionPerformed

    private static void runOnDispatchThread(final Runnable r) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            javax.swing.SwingUtilities.invokeLater(r);
        }
    }
    static private List<Class> classes = null;

    static private List<Class> addClasses(String prefix, File dir, List<Class> classes) {
        File fa[] = dir.listFiles();
        if (fa == null) {
            return classes;
        }
        for (File f : fa) {
            if (f.isDirectory()) {
                classes = addClasses(prefix + f.getName() + ".", f, classes);
            } else if (f.getName().endsWith(".class")) {
                String clssNameToLookup = "";
                try {
                    String name = f.getName();
                    name = name.substring(0, name.length() - 6);
                    if (name.indexOf('$') >= 0) {
                        continue;
                    }
                    clssNameToLookup = prefix + name;
                    Class clss = Class.forName(clssNameToLookup);
                    if (!Modifier.isAbstract(clss.getModifiers())
                            && !clss.isSynthetic()
                            && !clss.isAnonymousClass()
                            && !clss.isMemberClass()) {
                        classes.add(clss);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ObjTableJPanel.class
                            .getName()).log(Level.SEVERE, "clssNameToLookup={0}", clssNameToLookup);
                    Logger.getLogger(ObjTableJPanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return classes;
    }

    static public List<Class> getClasses() {
        String name = "";
        File jar = null;
        List<Class> classes = new ArrayList<>();
        try {
            for (String classpathEntry : System.getProperty("java.class.path").split(System.getProperty("path.separator"))) {
                if (classpathEntry.endsWith(".jar")
                        && !classpathEntry.contains("vaadin")
                        && !classpathEntry.contains("google")
                        && !classpathEntry.contains("apache")
                        && !classpathEntry.contains("commons-io")
                        && !classpathEntry.contains("commons-math")
                        && !classpathEntry.contains("xerces")
                        && !classpathEntry.contains("exificient")) {
                    JarInputStream is = null;
                    try {
                        jar = new File(classpathEntry);
                        is = new JarInputStream(new FileInputStream(jar));
                        JarEntry entry;
                        while ((entry = is.getNextJarEntry()) != null) {
//                            System.out.println("entry.getName() = " + entry.getName());
                            if (!entry.getName().startsWith("crcl/base/")) {
                                continue;
                            }
                            if (entry.getName().endsWith(".class")) {
                                name = entry.getName();
                                name = name.substring(0, name.length() - 6);
                                name = name.replaceAll("/", ".");
                                if (name.indexOf('$') >= 0) {
                                    continue;
                                }
                                Class clss;
                                try {
                                    clss = Class.forName(name);
                                    if (!Modifier.isAbstract(clss.getModifiers())
                                            && !clss.isSynthetic()
                                            && !clss.isAnonymousClass()
                                            && !clss.isMemberClass()) {
                                        classes.add(clss);

                                    }
                                } catch (Throwable ex) {
                                    System.err.println("entry.getName() = " + entry.getName());
                                    System.err.println("entry.getName().startsWith(\"crcl\") = " + entry.getName().startsWith("crcl"));
                                    System.err.println("entry = " + entry);
                                    System.err.println("name = " + name);
                                    Logger.getLogger(ObjTableJPanel.class
                                            .getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ObjTableJPanel.class
                                .getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            if (null != is) {
                                is.close();
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ObjTableJPanel.class
                                    .getName()).log(Level.SEVERE, "classpathEntry=" + classpathEntry, ex);
                        }
                    }
                } else {
                    File dir = new File(classpathEntry);
                    classes = addClasses("", dir, classes);
                }
            }
        } catch (Throwable t) {
            System.err.println("name = " + name);
            System.err.println("jar = " + jar);
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, t);
        }
        return classes;
    }

    private Object getObject(String name) throws IllegalAccessException, InvocationTargetException {
        int pindex = name.lastIndexOf('.');
        String endname = name;
        if (pindex > 0) {
            endname = name.substring(pindex + 1);
        }
        if (name.equals("this")) {
            return obj;
        }
        return getObject(getParentObject(name), endname);
    }

    private Object getObject(Object pobj, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (pobj instanceof List) {
            List l = (List) pobj;
            String indexString = name.substring(name.lastIndexOf('(') + 1, name.lastIndexOf(')'));
            return l.get(Integer.parseInt(indexString));
        }
        Method mget = null;
        try {
            mget = pobj.getClass().getMethod("get" + name);

        } catch (NoSuchMethodException ex) {
        }
        try {
            if (null == mget) {
                mget = pobj.getClass().getMethod("is" + name);
            }
        } catch (NoSuchMethodException ex) {
        }
        if (mget == null) {
            System.err.println("Method to get object for " + name + " does not exist");
            return null;
        }
        pobj = mget.invoke(pobj);
        return pobj;
    }

    private Object getParentObject(String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object pobj = this.obj;
        String pnames[] = name.split("[.]");
        for (int i = 0; i < pnames.length - 1; i++) {
            pobj = getObject(pobj, pnames[i]);
            if (pobj == null) {
                System.err.println("Parent object for " + pnames[i] + " of " + name + "does not exist");
                return null;
            }
        }
        return pobj;
    }

    private Class stringToClass(String type) throws ClassNotFoundException {
        if (type.equals("boolean")) {
            return boolean.class;
        }
        if (type.equals("int")) {
            return int.class;
        }
        if (type.equals("short")) {
            return short.class;
        }
        if (type.equals("long")) {
            return long.class;
        }
        if (type.equals("float")) {
            return float.class;
        }
        if (type.equals("double")) {
            return double.class;
        }
        return Class.forName(type);
    }

    private Object convertToType(Class<?> tclass, String name, Object o) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        if (tclass.equals(boolean.class)) {
            return Boolean.valueOf(o.toString());
        }
        if (tclass.equals(int.class)) {
            return Integer.valueOf(o.toString());
        }
        if (tclass.equals(short.class)) {
            return Short.valueOf(o.toString());
        }
        if (tclass.equals(long.class)) {
            return Long.valueOf(o.toString());
        }
        if (tclass.equals(float.class)) {
            return Float.valueOf(o.toString());
        }
        if (tclass.equals(double.class)) {
            return Double.valueOf(o.toString());
        }
        Object tobj = o;
        if (null != o && !tclass.isAssignableFrom(o.getClass())) {
            String ostring = o.toString();
            Method valueOf = null;

            try {
                valueOf = tclass.getMethod("valueOf", String.class);
            } catch (NoSuchMethodException ex) {
            } catch (SecurityException ex) {
                Logger.getLogger(ObjTableJPanel.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            if (null != valueOf) {
                tobj = valueOf.invoke(null, new Object[]{ostring});
            } else {
                Constructor constructor = null;

                try {
                    constructor = tclass.getConstructor(String.class);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(ObjTableJPanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(ObjTableJPanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                tobj = constructor.newInstance(ostring);
            }
        }
        return tobj;
    }

    private void setObjectForName(String type, String name, Object o) {
        try {
            if (type.startsWith("java.util.List")) {
                return;
            }
            if (name.endsWith(")")) {
                return;
            }
            Object orig_obj = this.getObject(name);
            if (null != orig_obj && orig_obj.equals(o)) {
                return;
            }
            if (null == orig_obj && null == o) {
                return;
            }
            Class tclass = this.stringToClass(type);
            Object tobj = this.convertToType(tclass, name, o);
            Object pobj = this.getParentObject(name);
            if (pobj instanceof List) {
                return;
            }
            String endname = name;
            int pindex = name.lastIndexOf('.');
            if (pindex > 0) {
                endname = endname.substring(pindex + 1);
            }
            Method mset = null;
            try {
                mset = pobj.getClass().getMethod("set" + endname, tclass);

            } catch (NoSuchMethodException ex) {
            }
            if (mset == null) {
                System.err.println("Method to set " + name + " does not exist");
                return;
            }
            mset.invoke(pobj, new Object[]{tobj});

        } catch (SecurityException ex) {
            System.err.println("Error in setObjectForName(" + type + "," + name + ", " + o + ")");
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            System.err.println("Error in setObjectForName(" + type + "," + name + ", " + o + ")");
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            System.err.println("Error in setObjectForName(" + type + "," + name + ", " + o + ")");
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            System.err.println("Error in setObjectForName(" + type + "," + name + ", " + o + ")");
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Error in setObjectForName(" + type + "," + name + ", " + o + ")");
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            System.err.println("Error in setObjectForName(" + type + "," + name + ", " + o + ")");
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void addObjectToList(String type, String name, Object o) {
        try {
            Class<?> tclass = this.stringToClass(type);
            Object tobj = this.convertToType(tclass, name, o);
            Object pobj = this.getParentObject(name);
            String endname = name;
            int pindex = name.lastIndexOf('.');
            if (pindex > 0) {
                endname = endname.substring(pindex + 1);
            }
            List l = (List) getObject(pobj, endname);
            l.add(tobj);
        } catch (SecurityException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeObjectFromList(String name) {
        try {
            List l = (List) this.getParentObject(name);
            int index1 = name.lastIndexOf(".get(");
            int index2 = name.lastIndexOf(')');
            String indexString = name.substring(index1 + 5, index2);
            int index = Integer.parseInt(indexString);
            l.remove(index);
        } catch (SecurityException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteTableItem() {
        try {
            int row = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            if (row < 0 || row > tm.getRowCount()) {
                jButtonNew.setEnabled(false);
                jButtonDelete.setEnabled(false);
                return;
            }
            this.updateTableFromObject();
        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setNewTableItem() {
        try {
            int row = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            if (row < 0 || row > tm.getRowCount()) {
                jButtonNew.setEnabled(false);
                return;
            }
            String type = (String) tm.getValueAt(row, 0);
            String name = (String) tm.getValueAt(row, 1);
            Class clss = this.stringToClass(type);
            if (null == classes) {
                classes = getClasses();
            }
            List<Class> availClasses = getAssignableClasses(clss, classes);
            Class ca[] = availClasses.toArray(new Class[availClasses.size()]);
            int selected = JOptionPane.showOptionDialog(this.dialog,
                    "Select class of new " + clss.getCanonicalName(),
                    name + " = new " + clss.getCanonicalName(),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    ca,
                    null);
            this.updateObjFromTable();
            Object newo = null;
            newo = ca[selected].newInstance();
            this.setObjectForName(type, name, newo);
            this.updateTableFromObject();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        this.setNewTableItem();
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonAddToListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddToListActionPerformed
        try {
            int row = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            if (row < 0 || row > tm.getRowCount()) {
                jButtonNew.setEnabled(false);
                return;
            }
            String type = (String) tm.getValueAt(row, 0);
            String name = (String) tm.getValueAt(row, 1);
            String typeparams = getTypeParams(type);
            Class clss = Class.forName(typeparams);
            if (null == classes) {
                classes = getClasses();
            }
            List<Class> availClasses = getAssignableClasses(clss, classes);
            Class ca[] = availClasses.toArray(new Class[availClasses.size()]);
            int selected = JOptionPane.showOptionDialog(this.dialog,
                    "Select class of new " + clss.getCanonicalName(),
                    name + " = new " + clss.getCanonicalName(),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    ca,
                    null);
            Object newo = ca[selected].newInstance();
            this.updateObjFromTable();
            this.addObjectToList(typeparams, name, newo);
            this.updateTableFromObject();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAddToListActionPerformed

    private void jButtonRemoveFromListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveFromListActionPerformed
        try {
            int row = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            if (row < 0 || row > tm.getRowCount()) {
                jButtonNew.setEnabled(false);
                return;
            }
            String name = (String) tm.getValueAt(row, 1);
            this.updateObjFromTable();
            this.removeObjectFromList(name);
            this.updateTableFromObject();

        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRemoveFromListActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        this.deleteTableItem();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonEditMultiLineTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditMultiLineTextActionPerformed

        int row = this.jTable1.getSelectedRow();
        if (row < 0 || row >= this.jTable1.getRowCount()) {
            this.jButtonEditMultiLineText.setEnabled(false);
            return;
        }
        String type = this.jTable1.getValueAt(row, 0).toString();
        String name = this.jTable1.getValueAt(row, 1).toString();
        Object oIn = this.jTable1.getValueAt(row, 2);
        String textIn = oIn == null ? "" : oIn.toString();
        String out = MultiLineStringJPanel.editText(textIn, null, name + ":" + type, true);
        if (null != out) {
            this.setObjectForName(type, name, out);
            this.updateTableFromObject();
        }
    }//GEN-LAST:event_jButtonEditMultiLineTextActionPerformed

    public static List<Class> getAssignableClasses(Class<?> baseClss, List<Class> classes) {
        List<Class> assignableClasses = new ArrayList<>();
        for (Class clss : classes) {
            if (baseClss.isAssignableFrom(clss)) {
                assignableClasses.add(clss);
            }
        }
        return assignableClasses;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddToList;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEditMultiLineText;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JButton jButtonRemoveFromList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneDocumentation;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(ObjTableJPanel.class.getName());
}
