package pack;
import java.sql.*;

public class DataBaseManager {

    private final static String m_URL = "jdbc:mysql://localhost:3306/Investing";

    private final static String m_user = "root";

    private final static String m_password = "Unfada!07ble";


    public static void fillStockMarket()
    {
        try
        {
            Connection connection_obj = DriverManager.getConnection(m_URL, m_user, m_password);

            Statement statement_obj = connection_obj.createStatement();

            ResultSet res_obj = statement_obj.executeQuery("SELECT * FROM traders");
            int id;
            double money;

            while (res_obj.next())
            {
                id = res_obj.getInt("trader_id");
                money = res_obj.getDouble("money");

                StockMarket.getArrAllTraders().add(new Trader(money,id));
            }

            res_obj = statement_obj.executeQuery("SELECT * FROM stocks");
            String company;
            int amount;
            double price;

            while (res_obj.next())
            {
                company = res_obj.getString("company_name");
                amount = res_obj.getInt("amount");
                price = res_obj.getDouble("price");

                StockMarket.getArrAllInvestments().add(new Stock(price,amount,company));
            }
        }
        catch (SQLException exep)
        { exep.printStackTrace(); }

    }
}
