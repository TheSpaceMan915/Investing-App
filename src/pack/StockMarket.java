package pack;
import java.util.ArrayList;
import java.util.List;

public class StockMarket {

    private static List<Investment> m_arr_all_investments;
    private static List<Trader> m_arr_all_traders;
    private static Trader m_current_trader;

     static
    {
        m_arr_all_investments = new ArrayList<>();
        m_arr_all_traders = new ArrayList<>();

        //get the data for investors and investments from the database
        DataBaseManager.fillStockMarket();
    }

    public static List<Investment> getArrAllInvestments() { return m_arr_all_investments; }

    public static List<Trader> getArrAllTraders() { return m_arr_all_traders; }

    public static Trader getCurrentTrader() { return m_current_trader; }

    public static void setCurrentTrader(Trader trader_obj) { m_current_trader = trader_obj; }

    public static void main(String[] args)
    {
        GUI.buildGUI();


    }
}