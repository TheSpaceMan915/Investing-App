package pack;
import javax.swing.*;
import java.awt.*;

public class GUI {

    private static JFrame m_frame  = new JFrame();

    public static JFrame getFrame() { return m_frame; }

    private static void drawAuthenticationWindow()
    {
        JPanel panel = new JPanel();
        JButton button = new JButton("Log in");
        JTextField field = new JTextField("Please enter your ID",20);

        panel.setLayout(new GridBagLayout());                         //this layout puts the authentication window
        panel.add(field);                                          //in the centre of the frame
        panel.add(button);
        m_frame.add(BorderLayout.CENTER,panel);


        button.addActionListener(x ->
        {
            String user_input = field.getText();
            if (StockMarket.authenticateTrader(user_input))        //if the user inputs an appropriate ID
            {
                m_frame.remove(panel);
                m_frame.getContentPane().repaint();          //repaint() tells Swing that an area of the window is dirty after a component has been removed
                m_frame.getContentPane().revalidate();      //revalidate() tells the layout manager (of the frame?) to recalculate the layout

                drawTheMainWindow();
            }
            else
            { field.setText("Try another ID"); }
        });
    }

    private static void drawTheMainWindow()
    {
        JPanel panel = new JPanel(new GridLayout(3,1));
        JButton trader_info_button = new JButton("Trader's info");
        JButton buy_button = new JButton("Buy investments");
        JButton sell_button = new JButton("Sell investments");


        JButton return_button = new JButton("Go back");         //the button to return to the main window

        return_button.addActionListener(event1 ->
        {
            m_frame.getContentPane().removeAll();
            m_frame.getContentPane().repaint();
            m_frame.getContentPane().revalidate();
            drawTheMainWindow();
        });

        panel.add(trader_info_button);
        panel.add(buy_button);
        panel.add(sell_button);
        m_frame.add(BorderLayout.CENTER,panel);

        trader_info_button.addActionListener(event2 ->
        {
            m_frame.remove(panel);                              //clear the frame
            m_frame.getContentPane().repaint();
            m_frame.getContentPane().revalidate();

            //set up the panel on the left containing these labels
            Box box_panel = new Box(BoxLayout.Y_AXIS);
            JLabel id_label = new JLabel("ID: "+ StockMarket.getCurrentTrader().getID());
            JLabel money_label = new JLabel("Amount of money: "+ StockMarket.getCurrentTrader().getMoney());
            JLabel stocks_label = new JLabel("Your investments: ");

            Font font_obj = new Font("Serif",Font.PLAIN,24);
            id_label.setFont(font_obj);
            money_label.setFont(font_obj);
            stocks_label.setFont(font_obj);

            box_panel.add(Box.createRigidArea(new Dimension(0,20)));
            box_panel.add(id_label);
            box_panel.add(Box.createRigidArea(new Dimension(0,20)));
            box_panel.add(money_label);
            box_panel.add(Box.createRigidArea(new Dimension(0,20)));
            box_panel.add(stocks_label);

            //create a JTable to show trader's stocks
            int arr_size = StockMarket.getCurrentTrader().getArrInvestments().size();
            String[] column_name = {"Investment", "Amount", "Price"};
            Object[][] data_arr = new Object[arr_size][3];

            for (int i = 0; i < arr_size; i++)
            {
               data_arr[i][0] = StockMarket.getCurrentTrader().getArrInvestments().get(i).getKey();
               data_arr[i][1] = StockMarket.getCurrentTrader().getArrInvestments().get(i).getAmount();
               data_arr[i][2] = StockMarket.getCurrentTrader().getArrInvestments().get(i).getPrice();
            }

            JTable data_table = new JTable(data_arr,column_name);
            JScrollPane scroller = new JScrollPane(data_table);
            scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            box_panel.add(scroller);
            m_frame.add(BorderLayout.CENTER,box_panel);
            m_frame.add(BorderLayout.SOUTH,return_button);
        });

        buy_button.addActionListener(event3 ->
        {
            m_frame.remove(panel);                                  //clear the frame
            m_frame.getContentPane().repaint();
            m_frame.getContentPane().revalidate();

            JPanel buy_panel = new JPanel();                        //set GridBagLayout for buy_panel
            GridBagLayout layout_obj = new GridBagLayout();
            buy_panel.setLayout(layout_obj);
            JLabel company_label = new JLabel("Enter investment ");
            JLabel number_label = new JLabel("Enter amount ");
            JTextField company_field = new JTextField(20);
            JTextField number_field = new JTextField(20);
            JButton buy_button2 = new JButton("Buy");

            Font font_obj = new Font("Serif",Font.PLAIN,24);
            company_label.setFont(font_obj);
            number_label.setFont(font_obj);

            buy_button2.addActionListener(event4 ->
            {
                try
                {
                    StockMarket.getCurrentTrader().buyInvestment(company_field.getText(), Integer.parseInt(number_field.getText()));
                    JOptionPane.showMessageDialog(m_frame,"Your purchase has been confirmed!");
                }
                catch(Exception exep)
                {
                    company_field.setText("You should enter a name of the investment here");
                    number_field.setText("And amount here");
                }
            });

            buy_panel.add(company_label);
            buy_panel.add(company_field);
            buy_panel.add(number_label);
            buy_panel.add(number_field);
            buy_panel.add(buy_button2);

            m_frame.add(BorderLayout.CENTER,buy_panel);
            m_frame.add(BorderLayout.SOUTH,return_button);
        });

        sell_button.addActionListener(event4 ->
        {
            m_frame.remove(panel);                                  //clear the frame
            m_frame.getContentPane().repaint();
            m_frame.getContentPane().revalidate();

            JPanel sell_panel = new JPanel();
            JLabel company_label = new JLabel("Enter investment ");
            JLabel number_label = new JLabel("Enter amount ");
            JTextField company_field = new JTextField(20);
            JTextField number_field = new JTextField(20);
            JButton sell_button2 = new JButton("Sell");

            Font font_obj = new Font("Serif",Font.PLAIN,24);
            company_label.setFont(font_obj);
            number_label.setFont(font_obj);

            sell_button2.addActionListener(event5 ->
            {
                try
                {
                    StockMarket.getCurrentTrader().sellInvestment(company_field.getText(), Integer.parseInt(number_field.getText()));
                    JOptionPane.showMessageDialog(m_frame,"Success!");
                }
                catch(Exception exep)
                {
                    company_field.setText("You should enter a name of the investment here");
                    number_field.setText("And amount here");
                }
            });

            sell_panel.add(company_label);
            sell_panel.add(company_field);
            sell_panel.add(number_label);
            sell_panel.add(number_field);
            sell_panel.add(sell_button2);

            m_frame.add(BorderLayout.CENTER,sell_panel);
            m_frame.add(BorderLayout.SOUTH,return_button);
        });
    }

    //the function is used for adding components to a JPanel with GridBagLayout manager
    private static void addComponentsToPanel(Component comp_obj, Container cont_obj, GridBagLayout manager_obj, GridBagConstraints constrains_obj, int gridx, int gridy, int gridwidth, int gridheigth)
    {
        constrains_obj.gridx = gridx;
        constrains_obj.gridy = gridy;

        constrains_obj.gridwidth = gridwidth;
        constrains_obj.gridheight = gridheigth;

        manager_obj.setConstraints(comp_obj,constrains_obj);
        cont_obj.add(comp_obj);
    }

    public static void buildGUI()
    {
        m_frame.setTitle("Investing App");
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.setSize(600,600);

        drawAuthenticationWindow();
        m_frame.setVisible(true);
    }


}
