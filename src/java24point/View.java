package java24point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class View {

    JFrame frame=new JFrame("24点小游戏");
    JPanel mainPanel=new JPanel();
    JTextField pork=new JTextField();
    JLabel res=new JLabel();
    JButton calButton=new JButton("结果");
    JButton reButton=new JButton("发牌");

    public void go(){

        mainPanel.setLayout(null);
        mainPanel.add(pork);
        mainPanel.add(reButton);
        mainPanel.add(res);
        mainPanel.add(calButton);

        pork.setBounds(20,18,150,30);
        reButton.setBounds(190,18,78,30);
        res.setBounds(20,66,150,30);
        calButton.setBounds(190,66,78,30);

        reButton.addActionListener(new ReButtonListener());
        calButton.addActionListener(new CalButtonListener());

        frame.add(mainPanel);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    class ReButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            pork.setText(getRadom()+" "+getRadom()+" "+getRadom()+" "+getRadom());
            res.setText("");
        }

    }

    class CalButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            String s=pork.getText();
            res.setText(new JavaTest().make24Points(s));
            res.repaint();
        }

    }

    private static String getRadom(){
        String[] pork ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        return pork[(int)(Math.random()*13)];
    }

    public static void main(String[] args) {
        new View().go();

    }

}
