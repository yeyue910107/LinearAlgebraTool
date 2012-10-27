/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package linearalgebra;

/**
 *
 * @author 叶玥
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.text.*;

public abstract class MatrixCalculate extends JPanel{
    protected static final int MAX = 10, P_HEIGHT = 28, P_WIDTH = 30, ACCURACY_SELECTED = 1;
    protected String[] stringAccuracy = {"1", "0.1", "0.01", "0.001", "0.0001","0.00001", "0.000001"};
    protected JPanel panel, panel1, panel2, panel3, matrixPanel, buttonPanel;
    protected JPanel message1, message2, message3;
    protected JPanel jpMatrix1, jpMatrix2, jpMatrix;
    protected JLabel jlLine1, jlRow1, jlAccuracy1, jlLine2, jlRow2, jlAccuracy2, jlLine, jlRow, jlAccuracy; 
    protected JTextField jtfLine1, jtfRow1, jtfLine2, jtfRow2, jtfLine, jtfRow, jtfAccuracy; 
    protected JComboBox jcbAccuracy1, jcbAccuracy2;
    protected JButton set, reset, start;
    protected JTextField[] jtfMatrix1 = new JTextField[MAX * MAX];
    protected JTextField[] jtfMatrix2 = new JTextField[MAX * MAX];
    protected JTextField[] jtfMatrix = new JTextField[MAX * MAX];
    protected JTextArea jtaMessage;
    protected Matrix matrix1, matrix2, matrix;
    protected boolean flagSet = true, flagReset = false;
}

class AddPanel extends MatrixCalculate implements ActionListener {
    
    public AddPanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("行数");
        jlLine2 = new JLabel("行数");
        jlLine = new JLabel("行数");
        jlRow1 = new JLabel("列数");
        jlRow2 = new JLabel("列数");
        jlRow = new JLabel("列数");
        jlAccuracy1 = new JLabel("精度");
        jlAccuracy2 = new JLabel("精度");
        jlAccuracy = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jtfLine2 = new JTextField(2);
        jtfRow2 = new JTextField(2);
        jcbAccuracy2 = new JComboBox(stringAccuracy);
        jtfLine = new JTextField(2);
        jtfLine.setEditable(false);
        jtfRow = new JTextField(2);
        jtfRow.setEditable(false);
        jtfAccuracy = new JTextField(2);
        jtfAccuracy.setEditable(false);
        jpMatrix1 = new JPanel();
        jpMatrix2 = new JPanel();
        jpMatrix = new JPanel();
        set = new JButton("设置(S)");
        set.setMnemonic('s');
        set.setToolTipText("设置矩阵参数 Alt+S");
        reset = new JButton("清除(R)");
        reset.setMnemonic('r');
        reset.setToolTipText("清除矩阵参数 Alt+R");
        start = new JButton("计算(C)");
        start.setMnemonic('c');
        start.setToolTipText("计算结果 Alt+C");
        
        jpMatrix1.setBackground(Color.WHITE);
        jpMatrix2.setBackground(Color.WHITE);
        jpMatrix.setBackground(Color.WHITE);
        jtfLine.setText("");
        jtfRow.setText("");
        jtfLine1.setText("");
        jtfRow1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        jtfLine2.setText("");
        jtfRow2.setText("");
        jcbAccuracy2.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message2 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new GridLayout(1, 6));
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message2.add(jlLine2);
        message2.add(jtfLine2);
        message2.add(jlRow2);
        message2.add(jtfRow2);
        message2.add(jlAccuracy2);
        message2.add(jcbAccuracy2);
        message3.add(jlLine);
        message3.add(jtfLine);
        message3.add(jlRow);
        message3.add(jtfRow);
        message3.add(jlAccuracy);
        message3.add(jtfAccuracy);
        
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵1"));
        panel2.add(message2, BorderLayout.NORTH);
        panel2.add(jpMatrix2, BorderLayout.CENTER);
        panel2.setBorder(new TitledBorder("矩阵2"));
        panel3.add(message3, BorderLayout.NORTH);
        panel3.add(jpMatrix, BorderLayout.CENTER);
        panel3.setBorder(new TitledBorder("结果"));
        
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 3, 10, 10));
        matrixPanel.add(panel1);
        matrixPanel.add(panel2);
        matrixPanel.add(panel3);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(set);
        buttonPanel.add(reset);
        buttonPanel.add(start);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(matrixPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        set.addActionListener(this);
        reset.addActionListener(this);
        start.addActionListener(this);
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfLine1.getText().equals("") || jtfRow1.getText().equals("") || jtfLine2.getText().equals("") || jtfRow2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵1和矩阵2的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX || Integer.parseInt(jtfLine2.getText()) > MAX || Integer.parseInt(jtfRow2.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if((Integer.parseInt(jtfLine1.getText()) != Integer.parseInt(jtfLine2.getText())) || (Integer.parseInt(jtfRow1.getText()) != Integer.parseInt(jtfRow2.getText())) || Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1 || Integer.parseInt(jtfLine2.getText()) < 1 || Integer.parseInt(jtfRow2.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵行数和列数是正整数，矩阵1和矩阵2的行数和列数对应相等");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfLine1.setEditable(false);
                jtfRow1.setEditable(false);
                jtfLine2.setEditable(false);
                jtfRow2.setEditable(false);
                int i;
                jtfLine.setText(jtfLine1.getText());
                jtfRow.setText(jtfRow1.getText());
                jtfAccuracy.setText(String.valueOf(Math.max(Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]), Double.parseDouble(stringAccuracy[jcbAccuracy2.getSelectedIndex()]))));
                for(i = 0; i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText()); i++){
                    jtfMatrix1[i] = new JTextField(2);
                }
                for(i = 0; i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText()); i++)
                    jtfMatrix2[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfLine1.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 1), (jpMatrix1.getHeight() - Integer.parseInt(jtfLine1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine1.getText()) + 1)));
                jpMatrix2.setLayout(new GridLayout(Integer.parseInt(jtfLine2.getText()), Integer.parseInt(jtfRow2.getText()), (jpMatrix2.getWidth() - Integer.parseInt(jtfRow2.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow2.getText()) + 1), (jpMatrix2.getHeight() - Integer.parseInt(jtfLine2.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine2.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfLine.getText()), Integer.parseInt(jtfRow.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfRow.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow.getText()) + 1), (jpMatrix.getHeight() - Integer.parseInt(jtfLine.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                for(i = 0;i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText());i++)
                    jpMatrix2.add(jtfMatrix2[i]);
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.add(jtfMatrix[i]);
                    jtfMatrix[i].setEditable(false);
                }
                validate();
            }
        }
        else if(e.getSource() == reset){
            jtaMessage.setText("");
            if(flagReset){
                flagReset = false;
                flagSet = true;
                jtfLine1.setEditable(true);
                jtfRow1.setEditable(true);
                jtfLine2.setEditable(true);
                jtfRow2.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText());i++){
                    jpMatrix2.remove(jtfMatrix2[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                jtfLine.setText("");
                jtfRow.setText("");
                jtfAccuracy.setText("");
                jtfLine1.setText("");
                jtfRow1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
                jtfLine2.setText("");
                jtfRow2.setText("");
                jcbAccuracy2.setSelectedIndex(ACCURACY_SELECTED);
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵1和矩阵2的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                matrix2 = Matrix.panelToMatrix(jtfMatrix2, Integer.parseInt(jtfLine2.getText()),Integer.parseInt(jtfRow2.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy2.getSelectedIndex()]));
                if(matrix1 == null || matrix2 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵1和矩阵2的各个元素是否输入完全");
                }
                else{
                    matrix = Matrix.matrixAdd(matrix1, matrix2);
                    Matrix.matrixToPanel(jtfMatrix, matrix);
                }
            }
        }
    } 
}

class SubstractPanel extends MatrixCalculate implements ActionListener {
    
    public SubstractPanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("行数");
        jlLine2 = new JLabel("行数");
        jlLine = new JLabel("行数");
        jlRow1 = new JLabel("列数");
        jlRow2 = new JLabel("列数");
        jlRow = new JLabel("列数");
        jlAccuracy1 = new JLabel("精度");
        jlAccuracy2 = new JLabel("精度");
        jlAccuracy = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jtfLine2 = new JTextField(2);
        jtfRow2 = new JTextField(2);
        jcbAccuracy2 = new JComboBox(stringAccuracy);
        jtfLine = new JTextField(2);
        jtfLine.setEditable(false);
        jtfRow = new JTextField(2);
        jtfRow.setEditable(false);
        jtfAccuracy = new JTextField(2);
        jtfAccuracy.setEditable(false);
        jpMatrix1 = new JPanel();
        jpMatrix2 = new JPanel();
        jpMatrix = new JPanel();
        set = new JButton("设置(S)");
        set.setMnemonic('s');
        set.setToolTipText("设置矩阵参数 Alt+S");
        reset = new JButton("清除(R)");
        reset.setMnemonic('r');
        reset.setToolTipText("清除矩阵参数 Alt+R");
        start = new JButton("计算(C)");
        start.setMnemonic('c');
        start.setToolTipText("计算结果 Alt+C");
        
        jpMatrix1.setBackground(Color.WHITE);
        jpMatrix2.setBackground(Color.WHITE);
        jpMatrix.setBackground(Color.WHITE);
        jtfLine.setText("");
        jtfRow.setText("");
        jtfLine1.setText("");
        jtfRow1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        jtfLine2.setText("");
        jtfRow2.setText("");
        jcbAccuracy2.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message2 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new GridLayout(1, 6));
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message2.add(jlLine2);
        message2.add(jtfLine2);
        message2.add(jlRow2);
        message2.add(jtfRow2);
        message2.add(jlAccuracy2);
        message2.add(jcbAccuracy2);
        message3.add(jlLine);
        message3.add(jtfLine);
        message3.add(jlRow);
        message3.add(jtfRow);
        message3.add(jlAccuracy);
        message3.add(jtfAccuracy);
        
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵1"));
        panel2.add(message2, BorderLayout.NORTH);
        panel2.add(jpMatrix2, BorderLayout.CENTER);
        panel2.setBorder(new TitledBorder("矩阵2"));
        panel3.add(message3, BorderLayout.NORTH);
        panel3.add(jpMatrix, BorderLayout.CENTER);
        panel3.setBorder(new TitledBorder("结果"));
        
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 3, 10, 10));
        matrixPanel.add(panel1);
        matrixPanel.add(panel2);
        matrixPanel.add(panel3);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(set);
        buttonPanel.add(reset);
        buttonPanel.add(start);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(matrixPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        set.addActionListener(this);
        reset.addActionListener(this);
        start.addActionListener(this);
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfLine1.getText().equals("") || jtfRow1.getText().equals("") || jtfLine2.getText().equals("") || jtfRow2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵1和矩阵2的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX || Integer.parseInt(jtfLine2.getText()) > MAX || Integer.parseInt(jtfRow2.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if((Integer.parseInt(jtfLine1.getText()) != Integer.parseInt(jtfLine2.getText())) || (Integer.parseInt(jtfRow1.getText()) != Integer.parseInt(jtfRow2.getText())) || Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1 || Integer.parseInt(jtfLine2.getText()) < 1 || Integer.parseInt(jtfRow2.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵行数和列数是正整数，矩阵1和矩阵2的行数和列数对应相等");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfLine1.setEditable(false);
                jtfRow1.setEditable(false);
                jtfLine2.setEditable(false);
                jtfRow2.setEditable(false);
                int i;
                jtfLine.setText(jtfLine1.getText());
                jtfRow.setText(jtfRow1.getText());
                jtfAccuracy.setText(String.valueOf(Math.max(Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]), Double.parseDouble(stringAccuracy[jcbAccuracy2.getSelectedIndex()]))));
                for(i = 0; i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText()); i++){
                    jtfMatrix1[i] = new JTextField(2);
                }
                for(i = 0; i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText()); i++)
                    jtfMatrix2[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfLine1.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 1), (jpMatrix1.getHeight() - Integer.parseInt(jtfLine1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine1.getText()) + 1)));
                jpMatrix2.setLayout(new GridLayout(Integer.parseInt(jtfLine2.getText()), Integer.parseInt(jtfRow2.getText()), (jpMatrix2.getWidth() - Integer.parseInt(jtfRow2.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow2.getText()) + 1), (jpMatrix2.getHeight() - Integer.parseInt(jtfLine2.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine2.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfLine.getText()), Integer.parseInt(jtfRow.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfRow.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow.getText()) + 1), (jpMatrix.getHeight() - Integer.parseInt(jtfLine.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                for(i = 0;i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText());i++)
                    jpMatrix2.add(jtfMatrix2[i]);
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.add(jtfMatrix[i]);
                    jtfMatrix[i].setEditable(false);
                }
                validate();
            }
        }
        else if(e.getSource() == reset){
            jtaMessage.setText("");
            if(flagReset){
                flagReset = false;
                flagSet = true;
                jtfLine1.setEditable(true);
                jtfRow1.setEditable(true);
                jtfLine2.setEditable(true);
                jtfRow2.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText());i++){
                    jpMatrix2.remove(jtfMatrix2[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                jtfLine.setText("");
                jtfRow.setText("");
                jtfAccuracy.setText("");
                jtfLine1.setText("");
                jtfRow1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
                jtfLine2.setText("");
                jtfRow2.setText("");
                jcbAccuracy2.setSelectedIndex(ACCURACY_SELECTED);
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵1和矩阵2的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                matrix2 = Matrix.panelToMatrix(jtfMatrix2, Integer.parseInt(jtfLine2.getText()),Integer.parseInt(jtfRow2.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy2.getSelectedIndex()]));
                if(matrix1 == null || matrix2 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵1和矩阵2的各个元素是否输入完全");
                }
                else{
                    matrix = Matrix.matrixMinus(matrix1, matrix2);
                    Matrix.matrixToPanel(jtfMatrix, matrix);
                }
            }
        }
    } 
}

class MultiplyPanel extends MatrixCalculate implements ActionListener{
   
    public MultiplyPanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("行数");
        jlLine2 = new JLabel("行数");
        jlLine = new JLabel("行数");
        jlRow1 = new JLabel("列数");
        jlRow2 = new JLabel("列数");
        jlRow = new JLabel("列数");
        jlAccuracy1 = new JLabel("精度");
        jlAccuracy2 = new JLabel("精度");
        jlAccuracy = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jtfLine2 = new JTextField(2);
        jtfRow2 = new JTextField(2);
        jcbAccuracy2 = new JComboBox(stringAccuracy);
        jtfLine = new JTextField(2);
        jtfLine.setEditable(false);
        jtfRow = new JTextField(2);
        jtfRow.setEditable(false);
        jtfAccuracy = new JTextField(2);
        jtfAccuracy.setEditable(false);
        jpMatrix1 = new JPanel();
        jpMatrix2 = new JPanel();
        jpMatrix = new JPanel();
        set = new JButton("设置(S)");
        set.setMnemonic('s');
        set.setToolTipText("设置矩阵参数 Alt+S");
        reset = new JButton("清除(R)");
        reset.setMnemonic('r');
        reset.setToolTipText("清除矩阵参数 Alt+R");
        start = new JButton("计算(C)");
        start.setMnemonic('c');
        start.setToolTipText("计算结果 Alt+C");
        
        jpMatrix1.setBackground(Color.WHITE);
        jpMatrix2.setBackground(Color.WHITE);
        jpMatrix.setBackground(Color.WHITE);
        jtfLine.setText("");
        jtfRow.setText("");
        jtfLine1.setText("");
        jtfRow1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        jtfLine2.setText("");
        jtfRow2.setText("");
        jcbAccuracy2.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message2 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new GridLayout(1, 6));
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message2.add(jlLine2);
        message2.add(jtfLine2);
        message2.add(jlRow2);
        message2.add(jtfRow2);
        message2.add(jlAccuracy2);
        message2.add(jcbAccuracy2);
        message3.add(jlLine);
        message3.add(jtfLine);
        message3.add(jlRow);
        message3.add(jtfRow);
        message3.add(jlAccuracy);
        message3.add(jtfAccuracy);
        
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵1"));
        panel2.add(message2, BorderLayout.NORTH);
        panel2.add(jpMatrix2, BorderLayout.CENTER);
        panel2.setBorder(new TitledBorder("矩阵2"));
        panel3.add(message3, BorderLayout.NORTH);
        panel3.add(jpMatrix, BorderLayout.CENTER);
        panel3.setBorder(new TitledBorder("结果"));
        
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 3, 10, 10));
        matrixPanel.add(panel1);
        matrixPanel.add(panel2);
        matrixPanel.add(panel3);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(set);
        buttonPanel.add(reset);
        buttonPanel.add(start);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(matrixPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        set.addActionListener(this);
        reset.addActionListener(this);
        start.addActionListener(this);
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfLine1.getText().equals("") || jtfRow1.getText().equals("") || jtfLine2.getText().equals("") || jtfRow2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵1和矩阵2的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX || Integer.parseInt(jtfLine2.getText()) > MAX || Integer.parseInt(jtfRow2.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if((Integer.parseInt(jtfRow1.getText()) != Integer.parseInt(jtfLine2.getText())) || Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1 || Integer.parseInt(jtfLine2.getText()) < 1 || Integer.parseInt(jtfRow2.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵行数和列数是正整数，矩阵1的列数和矩阵2的行数应相等");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfLine1.setEditable(false);
                jtfRow1.setEditable(false);
                jtfLine2.setEditable(false);
                jtfRow2.setEditable(false);
                int i;
                jtfLine.setText(jtfLine1.getText());
                jtfRow.setText(jtfRow2.getText());
                jtfAccuracy.setText(String.valueOf(Math.max(Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]), Double.parseDouble(stringAccuracy[jcbAccuracy2.getSelectedIndex()]))));
                for(i = 0; i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText()); i++)
                    jtfMatrix2[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfLine1.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 1), (jpMatrix1.getHeight() - Integer.parseInt(jtfLine1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine1.getText()) + 1)));
                jpMatrix2.setLayout(new GridLayout(Integer.parseInt(jtfLine2.getText()), Integer.parseInt(jtfRow2.getText()), (jpMatrix2.getWidth() - Integer.parseInt(jtfRow2.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow2.getText()) + 1), (jpMatrix2.getHeight() - Integer.parseInt(jtfLine2.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine2.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfLine.getText()), Integer.parseInt(jtfRow.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfRow.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow.getText()) + 1), (jpMatrix.getHeight() - Integer.parseInt(jtfLine.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                for(i = 0;i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText());i++)
                    jpMatrix2.add(jtfMatrix2[i]);
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.add(jtfMatrix[i]);
                    jtfMatrix[i].setEditable(false);
                }
                validate();
            }
        }
        else if(e.getSource() == reset){
            jtaMessage.setText("");
            if(flagReset){
                flagReset = false;
                flagSet = true;
                jtfLine1.setEditable(true);
                jtfRow1.setEditable(true);
                jtfLine2.setEditable(true);
                jtfRow2.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                jpMatrix1.removeAll();
                doLayout();
                validate();
                for(i = 0;i < Integer.parseInt(jtfLine2.getText()) * Integer.parseInt(jtfRow2.getText());i++){
                    jpMatrix2.remove(jtfMatrix2[i]);
                    validate();
                }
                jpMatrix2.removeAll();
                doLayout();
                validate();
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                doLayout();
                validate();
                jtfLine.setText("");
                jtfRow.setText("");
                jtfAccuracy.setText("");
                jtfLine1.setText("");
                jtfRow1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
                jtfLine2.setText("");
                jtfRow2.setText("");
                jcbAccuracy2.setSelectedIndex(ACCURACY_SELECTED);
                validate();
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵1和矩阵2的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                matrix2 = Matrix.panelToMatrix(jtfMatrix2, Integer.parseInt(jtfLine2.getText()),Integer.parseInt(jtfRow2.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy2.getSelectedIndex()]));
                if(matrix1 == null || matrix2 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵1和矩阵2的各个元素是否输入完全");
                }
                else{
                    matrix = Matrix.matrixMultiply(matrix1, matrix2);
                    Matrix.matrixToPanel(jtfMatrix, matrix);
                }
            }
        }
    }
}

class PowerPanel extends MatrixCalculate implements ActionListener{
    private JLabel jlPower; 
    private JTextField jtfPower;
    
    public PowerPanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("行数");
        jlLine = new JLabel("行数");
        jlRow1 = new JLabel("列数");
        jlRow = new JLabel("列数");
        jlAccuracy1 = new JLabel("精度");
        jlAccuracy = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jlPower = new JLabel("幂数");
        jtfPower = new JTextField(2);
        jtfLine = new JTextField(2);
        jtfLine.setEditable(false);
        jtfRow = new JTextField(2);
        jtfRow.setEditable(false);
        jtfAccuracy = new JTextField(2);
        jtfAccuracy.setEditable(false);
        jpMatrix1 = new JPanel();
        jpMatrix = new JPanel();
        set = new JButton("设置(S)");
        set.setMnemonic('s');
        set.setToolTipText("设置矩阵参数 Alt+S");
        reset = new JButton("清除(R)");
        reset.setMnemonic('r');
        reset.setToolTipText("清除矩阵参数 Alt+R");
        start = new JButton("计算(C)");
        start.setMnemonic('c');
        start.setToolTipText("计算结果 Alt+C");
        
        jpMatrix1.setBackground(Color.WHITE);
        jpMatrix.setBackground(Color.WHITE);
        jtfLine.setText("");
        jtfRow.setText("");
        jtfLine1.setText("");
        jtfRow1.setText("");
        jtfPower.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 8));
        message3 = new JPanel(new GridLayout(1, 6));
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message1.add(jlPower);
        message1.add(jtfPower);
        message3.add(jlLine);
        message3.add(jtfLine);
        message3.add(jlRow);
        message3.add(jtfRow);
        message3.add(jlAccuracy);
        message3.add(jtfAccuracy);
        
        panel1 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵"));
        panel3.add(message3, BorderLayout.NORTH);
        panel3.add(jpMatrix, BorderLayout.CENTER);
        panel3.setBorder(new TitledBorder("结果"));
        
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 2, 10, 10));
        matrixPanel.add(panel1);
        matrixPanel.add(panel3);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(set);
        buttonPanel.add(reset);
        buttonPanel.add(start);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(matrixPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        set.addActionListener(this);
        reset.addActionListener(this);
        start.addActionListener(this);
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfLine1.getText().equals("") || jtfRow1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if(Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵行数和列数需为正整数");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfLine1.setEditable(false);
                jtfRow1.setEditable(false);
                int i;
                jtfLine.setText(jtfLine1.getText());
                jtfRow.setText(jtfRow1.getText());
                jtfAccuracy.setText(String.valueOf(Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()])));
                for(i = 0; i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfLine1.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 2), (jpMatrix1.getHeight() - Integer.parseInt(jtfLine1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine1.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfLine.getText()), Integer.parseInt(jtfRow.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfRow.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow.getText()) + 2), (jpMatrix.getHeight() - Integer.parseInt(jtfLine.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.add(jtfMatrix[i]);
                    jtfMatrix[i].setEditable(false);
                }
                validate();
            }
        }
        else if(e.getSource() == reset){
            jtaMessage.setText("");
            if(flagReset){
                flagReset = false;
                flagSet = true;
                jtfLine1.setEditable(true);
                jtfRow1.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                jtfLine.setText("");
                jtfRow.setText("");
                jtfAccuracy.setText("");
                jtfLine1.setText("");
                jtfRow1.setText("");
                jtfPower.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(jtfPower.getText().equals("") || Integer.parseInt(jtfPower.getText()) < 1){
                JOptionPane.showMessageDialog(null, "矩阵幂数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的幂数是否正确设置，幂数需为正整数");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵的各个元素是否输入完全");
                }
                else{
                    matrix = matrix1.matrixPower(Integer.parseInt(jtfPower.getText()));
                    Matrix.matrixToPanel(jtfMatrix, matrix);
                }
            }
        }
    }
}

class ReversePanel extends MatrixCalculate implements ActionListener{
    
    public ReversePanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("行数");
        jlLine = new JLabel("行数");
        jlRow1 = new JLabel("列数");
        jlRow = new JLabel("列数");
        jlAccuracy1 = new JLabel("精度");
        jlAccuracy = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jtfLine = new JTextField(2);
        jtfLine.setEditable(false);
        jtfRow = new JTextField(2);
        jtfRow.setEditable(false);
        jtfAccuracy = new JTextField(2);
        jtfAccuracy.setEditable(false);
        jpMatrix1 = new JPanel();
        jpMatrix = new JPanel();
        set = new JButton("设置(S)");
        set.setMnemonic('s');
        set.setToolTipText("设置矩阵参数 Alt+S");
        reset = new JButton("清除(R)");
        reset.setMnemonic('r');
        reset.setToolTipText("清除矩阵参数 Alt+R");
        start = new JButton("计算(C)");
        start.setMnemonic('c');
        start.setToolTipText("计算结果 Alt+C");
        
        jpMatrix1.setBackground(Color.WHITE);
        jpMatrix.setBackground(Color.WHITE);
        jtfLine.setText("");
        jtfRow.setText("");
        jtfLine1.setText("");
        jtfRow1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new GridLayout(1, 6));
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message3.add(jlLine);
        message3.add(jtfLine);
        message3.add(jlRow);
        message3.add(jtfRow);
        message3.add(jlAccuracy);
        message3.add(jtfAccuracy);
        
        panel1 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵"));
        panel3.add(message3, BorderLayout.NORTH);
        panel3.add(jpMatrix, BorderLayout.CENTER);
        panel3.setBorder(new TitledBorder("结果"));
        
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 2, 10, 10));
        matrixPanel.add(panel1);
        matrixPanel.add(panel3);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(set);
        buttonPanel.add(reset);
        buttonPanel.add(start);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(matrixPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        set.addActionListener(this);
        reset.addActionListener(this);
        start.addActionListener(this);
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfLine1.getText().equals("") || jtfRow1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if(Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵行数和列数需为正整数");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfLine1.setEditable(false);
                jtfRow1.setEditable(false);
                int i;
                jtfLine.setText(jtfLine1.getText());
                jtfRow.setText(jtfRow1.getText());
                jtfAccuracy.setText(String.valueOf(Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()])));
                for(i = 0; i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfLine1.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 2), (jpMatrix1.getHeight() - Integer.parseInt(jtfLine1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine1.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfLine.getText()), Integer.parseInt(jtfRow.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfRow.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow.getText()) + 2), (jpMatrix.getHeight() - Integer.parseInt(jtfLine.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.add(jtfMatrix[i]);
                    jtfMatrix[i].setEditable(false);
                }
                validate();
            }
        }
        else if(e.getSource() == reset){
            jtaMessage.setText("");
            if(flagReset){
                flagReset = false;
                flagSet = true;
                jtfLine1.setEditable(true);
                jtfRow1.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                jtfLine.setText("");
                jtfRow.setText("");
                jtfAccuracy.setText("");
                jtfLine1.setText("");
                jtfRow1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵的各个元素是否输入完全");
                }
                else{
                    matrix = matrix1.matrixReverse();
                    Matrix.matrixToPanel(jtfMatrix, matrix);
                }
            }
        }
    }
}

class RankPanel extends MatrixCalculate implements ActionListener{
    
    public RankPanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("行数");
        jlLine = new JLabel("行数");
        jlRow1 = new JLabel("列数");
        jlAccuracy1 = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jpMatrix1 = new JPanel();
        set = new JButton("设置(S)");
        set.setMnemonic('s');
        set.setToolTipText("设置矩阵参数 Alt+S");
        reset = new JButton("清除(R)");
        reset.setMnemonic('r');
        reset.setToolTipText("清除矩阵参数 Alt+R");
        start = new JButton("计算(C)");
        start.setMnemonic('c');
        start.setToolTipText("计算结果 Alt+C");
        
        jpMatrix1.setBackground(Color.WHITE);
        jtfLine1.setText("");
        jtfRow1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new GridLayout());
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        
        panel1 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵"));
        jtfMatrix[0] = new JTextField(2);
        jtfMatrix[0].setEditable(false);
        panel3.add(jtfMatrix[0], BorderLayout.CENTER);
        panel3.setBorder(new TitledBorder("结果"));
        
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 2, 10, 10));
        matrixPanel.add(panel1);
        matrixPanel.add(panel3);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(set);
        buttonPanel.add(reset);
        buttonPanel.add(start);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(matrixPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        set.addActionListener(this);
        reset.addActionListener(this);
        start.addActionListener(this);
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfLine1.getText().equals("") || jtfRow1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if(Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵行数和列数需为正整数");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfLine1.setEditable(false);
                jtfRow1.setEditable(false);
                int i;
                for(i = 0; i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);  
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfLine1.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 2), (jpMatrix1.getHeight() - Integer.parseInt(jtfLine1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine1.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                validate();
            }
        }
        else if(e.getSource() == reset){
            jtaMessage.setText("");
            if(flagReset){
                flagReset = false;
                flagSet = true;
                jtfLine1.setEditable(true);
                jtfRow1.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                jtfMatrix[0].setText("");
                validate();
                jtfLine1.setText("");
                jtfRow1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵的各个元素是否输入完全");
                }
                else if(matrix1.determinantValue() == 0){
                    JOptionPane.showMessageDialog(null, "计算错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查该矩阵是否满足可逆条件，其对应行列式的值可能为0");
                }
                else{
                    int rank;
                    rank = matrix1.matrixRank();
                    jtfMatrix[0].setText("该矩阵的秩为： " + rank);
                }
            }
        }
    }
}

class DeterminantPanel extends MatrixCalculate implements ActionListener{
    
    public DeterminantPanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("行数");
        jlLine = new JLabel("行数");
        jlRow1 = new JLabel("列数");
        jlAccuracy1 = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jpMatrix1 = new JPanel();
        set = new JButton("设置(S)");
        set.setMnemonic('s');
        set.setToolTipText("设置矩阵参数 Alt+S");
        reset = new JButton("清除(R)");
        reset.setMnemonic('r');
        reset.setToolTipText("清除矩阵参数 Alt+R");
        start = new JButton("计算(C)");
        start.setMnemonic('c');
        start.setToolTipText("计算结果 Alt+C");
        
        jpMatrix1.setBackground(Color.WHITE);
        jtfLine1.setText("");
        jtfRow1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new GridLayout());
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        
        panel1 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵"));
        jtfMatrix[0] = new JTextField(2);
        jtfMatrix[0].setEditable(false);
        panel3.add(jtfMatrix[0], BorderLayout.CENTER);
        panel3.setBorder(new TitledBorder("结果"));
        
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 2, 10, 10));
        matrixPanel.add(panel1);
        matrixPanel.add(panel3);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(set);
        buttonPanel.add(reset);
        buttonPanel.add(start);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(matrixPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        set.addActionListener(this);
        reset.addActionListener(this);
        start.addActionListener(this);
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfLine1.getText().equals("") || jtfRow1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if((Integer.parseInt(jtfLine1.getText()) != Integer.parseInt(jtfRow1.getText())) || Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵行数和列数需为正整数，且该矩阵需为方阵即行数和列数需相等");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfLine1.setEditable(false);
                jtfRow1.setEditable(false);
                int i;
                for(i = 0; i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);                 
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfLine1.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 2), (jpMatrix1.getHeight() - Integer.parseInt(jtfLine1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine1.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                validate();
            }
        }
        else if(e.getSource() == reset){
            jtaMessage.setText("");
            if(flagReset){
                flagReset = false;
                flagSet = true;
                jtfLine1.setEditable(true);
                jtfRow1.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                jtfMatrix[0].setText("");
                doLayout();
                validate();
                jtfLine1.setText("");
                jtfRow1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵的各个元素是否输入完全");
                }
                else{
                    DecimalFormat df = new DecimalFormat(String.valueOf(matrix1.getAccuracy()).replace('1', '0'));
                    double value;
                    value = matrix1.determinantValue();
                    jtfMatrix[0].setText("该矩阵对应行列式的值为： " + df.format(value));
                    
                }
            }
        }
    }
}

class SimplifyPanel extends MatrixCalculate implements ActionListener{
    
    public SimplifyPanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("行数");
        jlLine = new JLabel("行数");
        jlRow1 = new JLabel("列数");
        jlRow = new JLabel("列数");
        jlAccuracy1 = new JLabel("精度");
        jlAccuracy = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jtfLine = new JTextField(2);
        jtfLine.setEditable(false);
        jtfRow = new JTextField(2);
        jtfRow.setEditable(false);
        jtfAccuracy = new JTextField(2);
        jtfAccuracy.setEditable(false);
        jpMatrix1 = new JPanel();
        jpMatrix = new JPanel();
        set = new JButton("设置(S)");
        set.setMnemonic('s');
        set.setToolTipText("设置矩阵参数 Alt+S");
        reset = new JButton("清除(R)");
        reset.setMnemonic('r');
        reset.setToolTipText("清除矩阵参数 Alt+R");
        start = new JButton("计算(C)");
        start.setMnemonic('c');
        start.setToolTipText("计算结果 Alt+C");
        
        jpMatrix1.setBackground(Color.WHITE);
        jpMatrix.setBackground(Color.WHITE);
        jtfLine.setText("");
        jtfRow.setText("");
        jtfLine1.setText("");
        jtfRow1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new GridLayout(1, 6));
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message3.add(jlLine);
        message3.add(jtfLine);
        message3.add(jlRow);
        message3.add(jtfRow);
        message3.add(jlAccuracy);
        
        panel1 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵"));
        panel3.add(message3, BorderLayout.NORTH);
        panel3.add(jpMatrix, BorderLayout.CENTER);
        panel3.setBorder(new TitledBorder("结果"));
        
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 2, 10, 10));
        matrixPanel.add(panel1);
        matrixPanel.add(panel3);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(set);
        buttonPanel.add(reset);
        buttonPanel.add(start);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(matrixPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        set.addActionListener(this);
        reset.addActionListener(this);
        start.addActionListener(this);
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfLine1.getText().equals("") || jtfRow1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if(Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵行数和列数需为正整数");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfLine1.setEditable(false);
                jtfRow1.setEditable(false);
                int i;
                jtfLine.setText(jtfLine1.getText());
                jtfRow.setText(jtfRow1.getText());
                jtfAccuracy.setText(String.valueOf(Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()])));
                for(i = 0; i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfLine1.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 2), (jpMatrix1.getHeight() - Integer.parseInt(jtfLine1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine1.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfLine.getText()), Integer.parseInt(jtfRow.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfRow.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow.getText()) + 2), (jpMatrix.getHeight() - Integer.parseInt(jtfLine.getText()) * P_HEIGHT) / (Integer.parseInt(jtfLine.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.add(jtfMatrix[i]);
                    jtfMatrix[i].setEditable(false);
                }
                validate();
            }
        }
        else if(e.getSource() == reset){
            jtaMessage.setText("");
            if(flagReset){
                flagReset = false;
                flagSet = true;
                jtfLine1.setEditable(true);
                jtfRow1.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfLine1.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfLine.getText()) * Integer.parseInt(jtfRow.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                jtfLine.setText("");
                jtfRow.setText("");
                jtfAccuracy.setText("");
                jtfLine1.setText("");
                jtfRow1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵的各个元素是否输入完全");
                }
                else{
                    matrix = matrix1.matrixLineSimplify();
                    Matrix.matrixToPanel(jtfMatrix, matrix);
                }
            }
        }
    }
}