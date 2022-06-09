package pack;
import javax.swing.*;
import java.awt.*;

public class GUI {

    private static JFrame m_frame;
    private static JButton m_button;
    private static JTextField m_field;
    private static JPanel m_panel;


    private static void drawAuthenticationWindow()
    {
        m_panel = new JPanel();
        m_button = new JButton("Log in");
        m_field = new JTextField("Please enter your ID",20);

        m_panel.setLayout(new GridBagLayout());                         //this layout puts the authentication window
        m_panel.add(m_button);                                          //in the centre of the frame
        m_panel.add(m_field);
        m_frame.getContentPane().add(BorderLayout.CENTER,m_panel);


        m_button.addActionListener(x ->
        {
            String user_input = m_field.getText();
            if (StockMarket.authenticateTrader(user_input))        //if the user inputs an appropriate ID
            {
                m_panel.removeAll();                    //remove everything from the panel and update it
                m_panel.repaint();
            }
            else
            { m_field.setText("Try another ID"); }
        });
    }

    public static void buildGUI()
    {
        m_frame = new JFrame();
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.setSize(400,400);

        drawAuthenticationWindow();
        m_frame.setVisible(true);
    }


}
