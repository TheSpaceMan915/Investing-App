package pack;
import java.util.ArrayList;
import java.util.List;

public class StockMarket {

    private static List<Investment> m_arr_all_investments;
    private static List<Trader> m_arr_all_traders;
    private static Trader m_current_trader;

    public StockMarket()
    {
        m_arr_all_investments = new ArrayList<>();
        m_arr_all_traders = new ArrayList<>();              //get the data for investors and investments from the database
    }

    public static List<Investment> getArrAllInvestments() { return m_arr_all_investments; }

    public static List<Trader> getArrAllTraders() { return m_arr_all_traders; }

    public static Trader getCurrentTrader() { return m_current_trader; }

    public static boolean authenticateTrader(String id)
    {
        boolean res = false;

        for (Trader temp : m_arr_all_traders)
        {
            if (id.equals(temp.getID()))                                //if an id from database equals the user input
            {                                                           //identify the current user
                res = true;
                m_current_trader = temp;                                //set the current trader
                break;
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        GUI.buildGUI();
    }
}