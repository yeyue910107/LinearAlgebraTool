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
import javax.swing.JMenuBar.*;
import java.lang.String.*;
import java.awt.event.*;
import javax.swing.border.*;

public class WelcomeInterface extends JFrame implements ActionListener {
    private JButton enter;
    private JPanel buttonPanel;
    private JLabel image1 = new JLabel(null, new ImageIcon("c:\\Users\\叶玥\\Pictures\\其他\\线性代数1.jpg"), SwingConstants.CENTER);
    private JLabel image2 = new JLabel(null, new ImageIcon("c:\\Users\\Public\\Pictures\\Sample Pictures\\Desert Landscape.jpg"), SwingConstants.CENTER);
    
    public WelcomeInterface(){
        Container container = this.getContentPane();
        JPanel panel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout());
        
        enter = new JButton("Enter");
        image1.setHorizontalAlignment(SwingConstants.CENTER);
        image1.setHorizontalTextPosition(SwingConstants.CENTER);
        image1.setIconTextGap(100);
        buttonPanel.add(enter);
        panel.add(image1, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        container.add(panel);
        this.setResizable(false);
        
        enter.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.enter){
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BorderLayout());
            MainInterface mainInterface = new MainInterface();
            mainInterface.pack();
            mainInterface.setTitle("Linear Algebra Tool 线性代数工具");
            mainInterface.setSize(1000, 600);
            mainInterface.add(image2, BorderLayout.CENTER);
            mainInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int)screenSize.getWidth();
            int screenHeight = (int)screenSize.getHeight();
            int x = (screenWidth - mainInterface.getWidth()) / 2;
            int y = (screenHeight - mainInterface.getHeight()) / 2;
        
            mainInterface.setLocation(x, y);
            mainInterface.setVisible(true);
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        WelcomeInterface welcomeFrame = new WelcomeInterface();
        
        welcomeFrame.pack();
        welcomeFrame.setTitle("Linear Algebra Tool 线性代数工具");
        welcomeFrame.setSize(400, 400);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        int x = (screenWidth - welcomeFrame.getWidth()) / 2;
        int y = (screenHeight - welcomeFrame.getHeight()) / 2;
        
        welcomeFrame.setLocation(x, y);
        welcomeFrame.setVisible(true);
}

    class MainInterface extends JFrame implements ActionListener{
        private JPanel panel;
        private Container container;
        private JMenuBar bar = new JMenuBar();
        private JMenu functionMenu = new JMenu("功能");
        private JMenu helpMenu = new JMenu("帮助");
        private JMenu equationMenu = new JMenu("线性方程组");
        private JMenu matrixCalMenu = new JMenu("矩阵代数运算");
        private JMenu matrixTransMenu = new JMenu("矩阵变换");
        private JMenuItem aboutTool = new JMenuItem("关于工具");
        private JMenuItem aboutFunction = new JMenuItem("关于功能");
        private JMenu basicCal = new JMenu("基本运算");
        private JMenuItem add = new JMenuItem("加法");
        private JMenuItem substract = new JMenuItem("减法");
        private JMenuItem multiply = new JMenuItem("乘法");
        private JMenuItem power = new JMenuItem("乘幂");
        private JMenuItem reverse = new JMenuItem("求逆");
        private JMenuItem rank = new JMenuItem("求秩");
        private JMenuItem determinant = new JMenuItem("行列式");
        private JMenuItem simplify = new JMenuItem("行简化");
        private JMenuItem combine = new JMenuItem("复合运算");
        private JMenu similar = new JMenu("相似变换");
        private JMenu congruent = new JMenu("相合变换");
        private JMenu orthogonal = new JMenu("正交变换");
        private JMenuItem similarDiagonalize = new JMenuItem("相似对角化");
        private JMenuItem jordan = new JMenuItem("Jordan标准形");
        private JMenuItem congruentDiagonalize = new JMenuItem("相合对角化");
        private JMenuItem homogeneousLinearEquation = new JMenuItem("齐次线性方程组");
        private JLabel message = new JLabel();

        public MainInterface(){
                basicCal.add(add);
                basicCal.add(substract);
                basicCal.add(multiply);
                basicCal.add(power);
                basicCal.add(reverse);
                basicCal.add(rank);
                basicCal.add(determinant);
                basicCal.add(simplify);
                
                similar.add(similarDiagonalize);
                similar.add(jordan);
                congruent.add(congruentDiagonalize);

                equationMenu.add(homogeneousLinearEquation);
                matrixCalMenu.add(basicCal);
                matrixCalMenu.add(combine);
                matrixTransMenu.add(similar);
                matrixTransMenu.add(congruent);
                matrixTransMenu.add(orthogonal);
                functionMenu.add(equationMenu);
                functionMenu.addSeparator();
                functionMenu.add(matrixCalMenu);
                functionMenu.addSeparator();
                functionMenu.add(matrixTransMenu);
                helpMenu.add(aboutTool);
                helpMenu.addSeparator();
                helpMenu.add(aboutFunction);

                bar.add(functionMenu);
                bar.add(helpMenu);
                bar.setBackground(Color.LIGHT_GRAY);
                
                this.setJMenuBar(bar);
                this.setBackground(Color.WHITE);
                homogeneousLinearEquation.addActionListener(this);
                add.addActionListener(this);
                substract.addActionListener(this);
                multiply.addActionListener(this);
                power.addActionListener(this);
                reverse.addActionListener(this);
                rank.addActionListener(this);
                determinant.addActionListener(this);
                simplify.addActionListener(this);
                similarDiagonalize.addActionListener(this);
                jordan.addActionListener(this);
                congruentDiagonalize.addActionListener(this);
                aboutTool.addActionListener(this);
                functionMenu.addActionListener(this);
                helpMenu.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
                container = this.getContentPane();
                container.setLayout(new BorderLayout());
                
                if(e.getSource() == aboutTool){
                    //Container aboutContainer = this.getContentPane();
                    JFrame aboutFrame = new JFrame();
                    aboutFrame.pack();
                    aboutFrame.setTitle("关于工具");
                    aboutFrame.setSize(400, 400);
                    //aboutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int screenWidth = (int)screenSize.getWidth();
                    int screenHeight = (int)screenSize.getHeight();
                    int x = (screenWidth - aboutFrame.getWidth()) / 2;
                    int y = (screenHeight - aboutFrame.getHeight()) / 2;
        
                    aboutFrame.setLocation(x, y);
                    aboutFrame.setVisible(true);
                    aboutFrame.setResizable(false);
                    
                    JPanel aboutPanel = new JPanel();
                    aboutPanel.setLayout(new BorderLayout());
                    JLabel image = new JLabel(null, new ImageIcon("c:\\Users\\叶玥\\Pictures\\其他\\线性代数1.jpg"), SwingConstants.CENTER);
                    image.setSize(400, 300);
                    JTextArea jta = new JTextArea();
                    jta.setBorder(new TitledBorder(""));
                    jta.setText("产品名称：Linear Algebra Tool 矩阵运算工具\n" + "开发者：叶玥 Ye Yue\n" + "版本：1.0");
                    jta.setEditable(false);
                    jta.setFont(new Font("Serif", Font.BOLD, 15));
                    jta.setBackground(Color.LIGHT_GRAY);
                    aboutPanel.add(image, BorderLayout.CENTER);
                    aboutPanel.add(jta, BorderLayout.NORTH);
                    //aboutContainer.add(panel);
                    aboutFrame.add(aboutPanel);
                }
                else if(e.getSource() == add){
                    container.removeAll();
                    panel = new AddPanel();
                    message.setText("矩阵代数运算 加法");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == substract){
                    container.removeAll();
                    panel = new SubstractPanel();
                    message.setText("矩阵代数运算 减法");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == multiply){
                    container.removeAll();
                    panel = new MultiplyPanel();
                    message.setText("矩阵代数运算 乘法");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == power){
                    container.removeAll();
                    panel = new PowerPanel();
                    message.setText("矩阵代数运算 乘幂");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == reverse){
                    container.removeAll();
                    panel = new ReversePanel();
                    message.setText("矩阵代数运算 求逆");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == rank){
                    container.removeAll();
                    panel = new RankPanel();
                    message.setText("矩阵代数运算 求秩");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == determinant){
                    container.removeAll();
                    panel = new DeterminantPanel();
                    message.setText("矩阵代数运算 行列式");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == simplify){
                    container.removeAll();
                    panel = new SimplifyPanel();
                    message.setText("矩阵代数运算 行简化");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == similarDiagonalize){
                    container.removeAll();
                    panel = new SimilarDiagPanel();
                    message.setText("矩阵变换 相似变换 相似对角化");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == jordan){
                    container.removeAll();
                    panel = new JordanPanel();
                    message.setText("矩阵变换 相似变换 Jordan标准形");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == congruentDiagonalize){
                    container.removeAll();
                    panel = new CongruentDiagPanel();
                    message.setText("矩阵变换 相合变换 相合对角化");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                else if(e.getSource() == homogeneousLinearEquation){
                    container.removeAll();
                    panel = new HomogeneousPanel();
                    message.setText("线性方程组 齐次");
                    panel.setVisible(true);
                    bar.setLayout(new BorderLayout());
                    bar.add(message, BorderLayout.EAST);
                    this.add(panel, BorderLayout.CENTER);
                }
                doLayout();
                validate();
        }
    }
}
