package pack;
import java.util.ArrayList;
import java.util.List;

public class StockMarket {

    private static List<Stock> m_arr_all_stocks = new ArrayList<>();
    private static List<Trader> m_arr_all_traders = new ArrayList<>();
    private static Trader m_current_trader = new Trader(1200,"11");

    public StockMarket()
    {
        m_arr_all_stocks = new ArrayList<>();
        m_arr_all_traders = new ArrayList<>();              //get the data for investors and investments from the database
    }

    public static List<Stock> getArrAllStocks() { return m_arr_all_stocks; }

    public static List<Trader> getArrAllTraders() { return m_arr_all_traders; }

    public static Trader getCurrentTrader() { return m_current_trader; }

    public static boolean authenticateTrader(String id)
    {
        boolean res = false;

        for (Trader temp : m_arr_all_traders)
        {
            if (id.equals(temp.getID()))        //if an id from database equals the user input
            {                                                     //identify the current user
                res = true;
                m_current_trader = temp;                          //set the current trader
                break;
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        GUI.buildGUI();

        Stock stock_obj1 = new Stock(140.4,2,"Tesla");
        Stock stock_obj2 = new Stock(170.5,3,"Apple");

        StockMarket.getArrAllStocks().add(stock_obj1);
        StockMarket.getArrAllStocks().add(stock_obj2);
        StockMarket.getArrAllTraders().add(m_current_trader);

        m_current_trader.buyStock("Apple",2);
        m_current_trader.buyStock("Apple",1);
        m_current_trader.buyStock("Tesla",1);
        m_current_trader.sellStock("Apple",1);
    }
}