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
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;

public abstract class LinearEquation extends JPanel{
    protected static final int MAX = 10, P_HEIGHT = 28, P_WIDTH = 30, ACCURACY_SELECTED = 1;
    protected String[] stringAccuracy = {"1", "0.1", "0.01", "0.001", "0.0001","0.00001", "0.000001"};
    protected JPanel panel, panel1, panel2, panel3, matrixPanel, buttonPanel;
    protected JPanel message1, message3;
    protected JPanel jpMatrix1, jpMatrix;
    protected JLabel jlLine1, jlRow1, jlAccuracy1, jlSize, jlAccuracy; 
    protected JTextField jtfLine1, jtfRow1, jtfSize; 
    protected JComboBox jcbAccuracy1;
    protected JButton set, reset, start;
    protected JTextField[] jtfMatrix1 = new JTextField[MAX * MAX];
    //protected JTextField[] jtfMatrix2 = new JTextField[MAX * MAX];
    protected JTextField[] jtfMatrix = new JTextField[MAX * MAX];
    protected JTextArea jtaMessage;
    protected Matrix matrix1, matrix;
    protected boolean flagSet = true, flagReset = false, flagStart = false;
    protected Equation equation = new Equation();
}

class HomogeneousPanel extends LinearEquation implements ActionListener{
    
    public HomogeneousPanel(){
        this.setLayout(new BorderLayout());
        jlLine1 = new JLabel("方程数");
        jlSize = new JLabel("解个数");
        jlRow1 = new JLabel("未知数");
        jlAccuracy1 = new JLabel("精度");
        jtfLine1 = new JTextField(2);
        jtfRow1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
        jtfSize = new JTextField(2);
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
        jtfLine1.setText("");
        jtfRow1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new FlowLayout());
        message1.add(jlLine1);
        message1.add(jtfLine1);
        message1.add(jlRow1);
        message1.add(jtfRow1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message3.add(jlSize);
        message3.add(jtfSize);
        
        panel1 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("系数矩阵"));
        panel3.add(message3, BorderLayout.NORTH);
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
                JOptionPane.showMessageDialog(null, "未设置系数矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfLine1.getText()) > MAX || Integer.parseInt(jtfRow1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if(Integer.parseInt(jtfLine1.getText()) < 1 || Integer.parseInt(jtfRow1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，系数矩阵行数和列数需为正整数");
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
                if(flagStart){
                    for(i = 0;i < Integer.parseInt(jtfSize.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                        jtfMatrix[i].setText("");
                        jpMatrix.remove(jtfMatrix[i]);
                        validate();
                    }
                }
                jtfLine1.setText("");
                jtfRow1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
                jtfSize.setText("");
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置系数矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查系数矩阵的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfLine1.getText()),Integer.parseInt(jtfRow1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));               if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "系数矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查系数矩阵的各个元素是否输入完全");
                }
                else{
                    equation.homogeneousLinearEquation(matrix1);
                    jtfSize.setText(String.valueOf(equation.getRoot().getRootNum()));
                    
                    jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfSize.getText()), Integer.parseInt(jtfRow1.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfRow1.getText()) * P_WIDTH) / (Integer.parseInt(jtfRow1.getText()) + 1), (jpMatrix.getWidth() - Integer.parseInt(jtfSize.getText()) * P_HEIGHT) / (Integer.parseInt(jtfSize.getText()) + 1)));
                    int i;
                    for(i = 0;i < Integer.parseInt(jtfSize.getText()) * Integer.parseInt(jtfRow1.getText());i++){
                        jtfMatrix[i] = new JTextField(2);
                        jpMatrix.add(jtfMatrix[i]);
                        jtfMatrix[i].setText(String.valueOf(equation.getRoot().getElement(i / Integer.parseInt(jtfRow1.getText()), i % Integer.parseInt(jtfRow1.getText()))));
                        jtfMatrix[i].setEditable(false);
                        validate();
                    }
                    flagStart = true;
                }
            }
        }
    }
}