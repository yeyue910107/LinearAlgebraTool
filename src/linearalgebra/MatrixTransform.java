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

public abstract class MatrixTransform extends JPanel{
    protected static final int MAX = 10, P_HEIGHT = 28, P_WIDTH = 30, ACCURACY_SELECTED = 1;
    protected String[] stringAccuracy = {"1", "0.1", "0.01", "0.001", "0.0001","0.00001", "0.000001"};
    protected JPanel panel, panel1, panel2, panel3, matrixPanel, buttonPanel;
    protected JPanel message1, message3;
    protected JPanel jpMatrix1, jpMatrix;
    protected JLabel jlSize1, jlAccuracy1, jlSize, jlAccuracy; 
    protected JTextField jtfSize1, jtfSize, jtfAccuracy; 
    protected JComboBox jcbAccuracy1;
    protected JButton set, reset, start;
    protected JTextField[] jtfMatrix1 = new JTextField[MAX * MAX];
    protected JTextField[] jtfMatrix2 = new JTextField[MAX * MAX];
    protected JTextField[] jtfMatrix = new JTextField[MAX * MAX];
    protected JTextArea jtaMessage;
    protected Matrix matrix1, matrix;
    protected boolean flagSet = true, flagReset = false, flagStart = false;
}

class SimilarDiagPanel extends MatrixTransform implements ActionListener{
    private JPanel[] jpEigenvalue = new JPanel[MAX];
    private JLabel[] jlEigenvalue = new JLabel[MAX];
    private JTextField[] jtfEigenvalue = new JTextField[MAX];
    private JButton[] jbEigenvalue = new JButton[MAX];
    private JButton jbDiagonalize = new JButton("对角方阵");
    private JButton jbReverse = new JButton("可逆方阵");
    private SimilarDiagonalize similarDiag = new SimilarDiagonalize();
    private Eigenvalue eigenvalue = new Eigenvalue();
    
    public SimilarDiagPanel(){
        this.setLayout(new BorderLayout());
        jlSize1 = new JLabel("阶数");
        jlAccuracy1 = new JLabel("精度");
        jtfSize1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
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
        jtfSize1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new FlowLayout());
        message1.add(jlSize1);
        message1.add(jtfSize1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message3.add(jbDiagonalize);
        message3.add(jbReverse);
        
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel();
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵"));
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
        jbDiagonalize.addActionListener(this);
        jbReverse.addActionListener(this);
        
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfSize1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfSize1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if(Integer.parseInt(jtfSize1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵阶数需为正整数");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfSize1.setEditable(false);
                
                int i;
                panel2.setLayout(new GridLayout(Integer.parseInt(jtfSize1.getText()), 3));
                for(i = 0; i < Integer.parseInt(jtfSize1.getText()); i++){
                    jpEigenvalue[i] = new JPanel();
                    jpEigenvalue[i].setLayout(new GridLayout(1, 3));
                    jlEigenvalue[i] = new JLabel();
                    jlEigenvalue[i].setText("特征值"+ (i + 1));
                    jtfEigenvalue[i] = new JTextField(2);
                    jbEigenvalue[i] = new JButton("特征矩阵");
                    jbEigenvalue[i].addActionListener(this);
                    jpEigenvalue[i].add(jlEigenvalue[i]);
                    jpEigenvalue[i].add(jtfEigenvalue[i]);
                    jpEigenvalue[i].add(jbEigenvalue[i]);
                    panel2.add(jpEigenvalue[i]);
                    //message3.add(jpEigenvalue[i]);
                    validate();
                }
                for(i = 0; i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfSize1.getText()), Integer.parseInt(jtfSize1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfSize1.getText()) * P_WIDTH) / (Integer.parseInt(jtfSize1.getText()) + 1), (jpMatrix1.getHeight() - Integer.parseInt(jtfSize1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfSize1.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfSize1.getText()), Integer.parseInt(jtfSize1.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfSize1.getText()) * P_WIDTH) / (Integer.parseInt(jtfSize1.getText()) + 1), (jpMatrix.getHeight() - Integer.parseInt(jtfSize1.getText()) * (P_HEIGHT - 5)) / (Integer.parseInt(jtfSize1.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
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
                jtfSize1.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfSize1.getText());i++){
                    panel2.remove(jpEigenvalue[i]);
                    doLayout();
                    validate();
                }
                validate();
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                jtfSize1.setText("");
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
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfSize1.getText()),Integer.parseInt(jtfSize1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵的各个元素是否输入完全");
                }
                else{
                    flagStart = true;
                    int i;
                    DecimalFormat df = new DecimalFormat(String.valueOf(matrix1.getAccuracy()).replace('1', '0'));
                    similarDiag.setSourceMatrix(SquareMatrix.matrixToSquare(matrix1));
                    similarDiag.findDiagonalMatrix(SquareMatrix.matrixToSquare(matrix1));
                    similarDiag.findMatrixP(SquareMatrix.matrixToSquare(matrix1));
                    eigenvalue.findEigenvalue(SquareMatrix.matrixToSquare(matrix1));
                    for(i = 0;i < Integer.parseInt(jtfSize1.getText());i++){
                        jtfEigenvalue[i].setText(String.valueOf(df.format(eigenvalue.getElement(i).getEigenvalue())));
                    }
                }
            }
        }
        else if(e.getSource() == jbDiagonalize){
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                Matrix.matrixToPanel(jtfMatrix, similarDiag.getDiagonalMatrix());
            }
        }
        else if(e.getSource() == jbReverse){
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                Matrix.matrixToPanel(jtfMatrix, similarDiag.getMatrixP());
            }
        }
        else{
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                int i;
                for(i = 0;i < Integer.parseInt(jtfSize1.getText());i++)
                    if(e.getSource() == jbEigenvalue[i])
                        break;
                Matrix.matrixToPanel(jtfMatrix, eigenvalue.getElement(i).getEigenMatrix());
            }
        }
    }
}

class JordanPanel extends MatrixTransform implements ActionListener{
    private Jordan jordan = new Jordan();
    private JPanel[] jpEigenvalue = new JPanel[MAX];
    private JLabel[] jlEigenvalue = new JLabel[MAX];
    private JTextField[] jtfEigenvalue = new JTextField[MAX];
    private JButton[] jbEigenvalue = new JButton[MAX];
    private JButton jbJordan = new JButton("Jordan标准形");
    private Eigenvalue eigenvalue = new Eigenvalue();
    
    public JordanPanel(){
        this.setLayout(new BorderLayout());
        jlSize1 = new JLabel("阶数");
        jlAccuracy1 = new JLabel("精度");
        jtfSize1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
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
        jtfSize1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new FlowLayout());
        message1.add(jlSize1);
        message1.add(jtfSize1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message3.add(jbJordan);
        
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel();
        panel3 = new JPanel(new BorderLayout());
        panel1.add(message1, BorderLayout.NORTH);
        panel1.add(jpMatrix1, BorderLayout.CENTER);
        panel1.setBorder(new TitledBorder("矩阵"));
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
        jbJordan.addActionListener(this);
        
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfSize1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfSize1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if(Integer.parseInt(jtfSize1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵阶数需为正整数");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfSize1.setEditable(false);
                
                int i;
                panel2.setLayout(new GridLayout(Integer.parseInt(jtfSize1.getText()), 3));
                for(i = 0; i < Integer.parseInt(jtfSize1.getText()); i++){
                    jpEigenvalue[i] = new JPanel();
                    jpEigenvalue[i].setLayout(new GridLayout(1, 3));
                    jlEigenvalue[i] = new JLabel();
                    jlEigenvalue[i].setText("特征值"+ (i + 1));
                    jtfEigenvalue[i] = new JTextField(2);
                    jbEigenvalue[i] = new JButton("特征矩阵");
                    jbEigenvalue[i].addActionListener(this);
                    jpEigenvalue[i].add(jlEigenvalue[i]);
                    jpEigenvalue[i].add(jtfEigenvalue[i]);
                    jpEigenvalue[i].add(jbEigenvalue[i]);
                    panel2.add(jpEigenvalue[i]);
                    //message3.add(jpEigenvalue[i]);
                    validate();
                }
                for(i = 0; i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfSize1.getText()), Integer.parseInt(jtfSize1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfSize1.getText()) * P_WIDTH) / (Integer.parseInt(jtfSize1.getText()) + 1), (jpMatrix1.getHeight() - Integer.parseInt(jtfSize1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfSize1.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfSize1.getText()), Integer.parseInt(jtfSize1.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfSize1.getText()) * P_WIDTH) / (Integer.parseInt(jtfSize1.getText()) + 1), (jpMatrix.getHeight() - Integer.parseInt(jtfSize1.getText()) * (P_HEIGHT - 5)) / (Integer.parseInt(jtfSize1.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++)
                    jpMatrix1.add(jtfMatrix1[i]);
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
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
                jtfSize1.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfSize1.getText());i++){
                    panel2.remove(jpEigenvalue[i]);
                    doLayout();
                    validate();
                }
                validate();
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                jtfSize1.setText("");
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
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfSize1.getText()),Integer.parseInt(jtfSize1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵的各个元素是否输入完全");
                }
                else{
                    flagStart = true;
                    int i;
                    DecimalFormat df = new DecimalFormat(String.valueOf(matrix1.getAccuracy()).replace('1', '0'));
                    jordan.jordan(SquareMatrix.matrixToSquare(matrix1));
                    eigenvalue = jordan.getEigenvalue();
                    for(i = 0;i < Integer.parseInt(jtfSize1.getText());i++){
                        jtfEigenvalue[i].setText(String.valueOf(df.format(eigenvalue.getElement(i).getEigenvalue())));
                    }
                }
            }
        }
        else if(e.getSource() == jbJordan){
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                Matrix.matrixToPanel(jtfMatrix, jordan.getJordanMatrix());
            }
        }
        else{
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                int i;
                for(i = 0;i < Integer.parseInt(jtfSize1.getText());i++)
                    if(e.getSource() == jbEigenvalue[i])
                        break;
                Matrix.matrixToPanel(jtfMatrix, eigenvalue.getElement(i).getEigenMatrix());
            }
        }
    }
}

class CongruentDiagPanel extends MatrixTransform implements ActionListener{
    private JButton jbDiagonalize = new JButton("对角方阵");
    private JButton jbReverse = new JButton("可逆方阵");
    private JButton jbSDiagonalize = new JButton("规范形");
    private JButton jbSReverse = new JButton("规范形可逆方阵");
    private CongruentDiagonalize congruentDiag = new CongruentDiagonalize();
    
    public CongruentDiagPanel(){
        this.setLayout(new BorderLayout());
        jlSize1 = new JLabel("阶数");
        jlAccuracy1 = new JLabel("精度");
        jtfSize1 = new JTextField(2);
        jcbAccuracy1 = new JComboBox(stringAccuracy);
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
        jtfSize1.setText("");
        jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
        message1 = new JPanel(new GridLayout(1, 6));
        message3 = new JPanel(new FlowLayout());
        message1.add(jlSize1);
        message1.add(jtfSize1);
        message1.add(jlAccuracy1);
        message1.add(jcbAccuracy1);
        message3.add(jbDiagonalize);
        message3.add(jbReverse);
        message3.add(jbSDiagonalize);
        message3.add(jbSReverse);
        
        panel1 = new JPanel(new BorderLayout());
        //panel2 = new JPanel();
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
        //matrixPanel.add(panel2);
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
        jbDiagonalize.addActionListener(this);
        jbReverse.addActionListener(this);
        jbSDiagonalize.addActionListener(this);
        jbSReverse.addActionListener(this);
        
        jtaMessage = new JTextArea(10, 20);
        jtaMessage.setBorder(new TitledBorder("信息"));
        jtaMessage.setEditable(false);
        this.add(panel, BorderLayout.CENTER);
        this.add(jtaMessage, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == set){
            jtaMessage.setText("");
            if(jtfSize1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else if(Integer.parseInt(jtfSize1.getText()) > MAX){
                JOptionPane.showMessageDialog(null, "超出运算范围！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否超出运算范围，本运算工具只支持10阶以下矩阵运算");
            }
            else if(Integer.parseInt(jtfSize1.getText()) < 1){
                JOptionPane.showMessageDialog(null, "参数设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查您所设置的参数是否正确，矩阵阶数需为正整数");
            }
            else if(flagSet){
                flagSet = false;
                flagReset = true;
                jtfSize1.setEditable(false);
                
                int i;
                for(i = 0; i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText()); i++)
                    jtfMatrix1[i] = new JTextField(2);
                for(i = 0; i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText()); i++)
                    jtfMatrix[i] = new JTextField(2);
                jpMatrix1.setLayout(new GridLayout(Integer.parseInt(jtfSize1.getText()), Integer.parseInt(jtfSize1.getText()), (jpMatrix1.getWidth() - Integer.parseInt(jtfSize1.getText()) * P_WIDTH) / (Integer.parseInt(jtfSize1.getText()) + 1), (jpMatrix1.getHeight() - Integer.parseInt(jtfSize1.getText()) * P_HEIGHT) / (Integer.parseInt(jtfSize1.getText()) + 1)));
                jpMatrix.setLayout(new GridLayout(Integer.parseInt(jtfSize1.getText()), Integer.parseInt(jtfSize1.getText()), (jpMatrix.getWidth() - Integer.parseInt(jtfSize1.getText()) * P_WIDTH) / (Integer.parseInt(jtfSize1.getText()) + 1), (jpMatrix.getHeight() - Integer.parseInt(jtfSize1.getText()) * (P_HEIGHT - 5)) / (Integer.parseInt(jtfSize1.getText()) + 1)));
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
                    jpMatrix1.add(jtfMatrix1[i]);
                    if((i % Integer.parseInt(jtfSize1.getText())) < (i / Integer.parseInt(jtfSize1.getText())))
                        jtfMatrix1[i].setEditable(false);
                }
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
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
                jtfSize1.setEditable(true);
                int i;
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
                    jpMatrix1.remove(jtfMatrix1[i]);
                    validate();
                }
                for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++){
                    jpMatrix.remove(jtfMatrix[i]);
                    validate();
                }
                jtfSize1.setText("");
                jcbAccuracy1.setSelectedIndex(ACCURACY_SELECTED);
            }
        }
        else if(e.getSource() == start){
            jtaMessage.setText("");
            int i;
            for(i = 0;i < Integer.parseInt(jtfSize1.getText()) * Integer.parseInt(jtfSize1.getText());i++)
                if((i % Integer.parseInt(jtfSize1.getText())) < (i / Integer.parseInt(jtfSize1.getText())))
                    jtfMatrix1[i].setText(jtfMatrix1[(i % Integer.parseInt(jtfSize1.getText())) * Integer.parseInt(jtfSize1.getText()) + i / Integer.parseInt(jtfSize1.getText())].getText());  
            if(flagSet){
                JOptionPane.showMessageDialog(null, "未设置矩阵参数！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数是否设置");
            }
            else{
                matrix1 = Matrix.panelToMatrix(jtfMatrix1, Integer.parseInt(jtfSize1.getText()),Integer.parseInt(jtfSize1.getText()),Double.parseDouble(stringAccuracy[jcbAccuracy1.getSelectedIndex()]));
                if(matrix1 == null){
                    JOptionPane.showMessageDialog(null, "矩阵元素设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                    jtaMessage.setText("请检查矩阵的各个元素是否输入完全");
                }
                else{
                    flagStart = true;
                    congruentDiag.congruent(SquareMatrix.matrixToSquare(matrix1));
                }
            }
        }
        else if(e.getSource() == jbDiagonalize){
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                Matrix.matrixToPanel(jtfMatrix, congruentDiag.getDiagonalMatrix());
            }
        }
        else if(e.getSource() == jbReverse){
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                Matrix.matrixToPanel(jtfMatrix, congruentDiag.getMatrixPofDiagonal());
            }
        }
        else if(e.getSource() == jbSDiagonalize){
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                Matrix.matrixToPanel(jtfMatrix, congruentDiag.getStandardMatrix());
            }
        }
        else if(e.getSource() == jbSReverse){
            if(!flagStart){
                JOptionPane.showMessageDialog(null, "矩阵参数和元素未设置或设置错误！", "错误信息", JOptionPane.ERROR_MESSAGE);
                jtaMessage.setText("请检查矩阵的各个参数元素是否输入完全");
            }
            else{
                Matrix.matrixToPanel(jtfMatrix, congruentDiag.getMatrixPofStandard());
            }
        }
    }
}