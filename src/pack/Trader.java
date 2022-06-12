package pack;
import javax.swing.*;
import java.util.*;

public class Trader {
    private final String m_id;
    private double m_money;                          //the amount of money on a trader's account
    private List<Stock> m_arr_stocks;

    public Trader()
    {
        this(0,"5124");
    }

    public Trader(double money, String id)
    {
        m_id = id;
        m_money = money;
        m_arr_stocks = new ArrayList<>();
    }

    public String getID() { return m_id; }

    public double getMoney() { return m_money; }

    public List<Stock> getArrStocks() { return m_arr_stocks; }

    private void setMoney(double money) { m_money = money; }

    public void buyStock(String company, int number)
    {
        Stock stock_on_market = Stock.findStockOnMarket(company);
        double investments_price = 0.0;

        try
        { investments_price = stock_on_market.getPrice() * number; }
        catch (NullPointerException exep)
        {
            JOptionPane.showMessageDialog(GUI.getFrame(),"There is no stock like this on the stock market");
            exep.printStackTrace();
        }

        if (m_money - investments_price > 0)
        {
            if (stock_on_market.getAmount() >= number)
            {
                try
                {
                    //make a clone of the object on the market for trader's portfolio
                    Stock temp = (Stock) stock_on_market.clone();

                    //amount of investments that were available before the purchase
                    int amount_before = stock_on_market.getAmount();

                    //amount of investments that are available after the purchase
                    int amount_after = stock_on_market.getAmount() - number;
                    stock_on_market.setAmount(amount_after);
                    temp.setAmount(amount_before - amount_after);

                                                                                //if the trader has this type of stock
                    Stock trader_stock = Stock.checkTraderStocks(company);       //add to that object new stocks
                    if (trader_stock != null)
                    { trader_stock.setAmount(trader_stock.getAmount() + number); }
                    else
                    { m_arr_stocks.add(temp); }

                    m_money -= investments_price;
                }
                catch (CloneNotSupportedException exep)
                { exep.printStackTrace(); }
            }
            else
            { JOptionPane.showMessageDialog(GUI.getFrame(),"There are not enough stocks on the market. We only have " +  stock_on_market.getAmount() + " of them"); }
        }
            else
        { JOptionPane.showMessageDialog(GUI.getFrame(),"You don't have enough money on your account"); }
    }

    public void sellStock(String company, int number)
    {
        Stock stock_on_market = Stock.findStockOnMarket(company);                 //the stock on the StockMarket
        Stock trader_stock = Stock.checkTraderStocks(company);                  //the stock in trader's portfolio

        if (trader_stock != null)             //if the stock has been found in the trader's portfolio
        {
            stock_on_market.setAmount(stock_on_market.getAmount() + number);
            trader_stock.setAmount(trader_stock.getAmount() - number);

            double investments_price = stock_on_market.getPrice() * number;;
            StockMarket.getCurrentTrader().setMoney(StockMarket.getCurrentTrader().getMoney() + investments_price);

            if (trader_stock.getAmount() == 0)
            { StockMarket.getCurrentTrader().getArrStocks().remove(trader_stock); }
        }
        else
        {
            JOptionPane.showMessageDialog(GUI.getFrame(),"There is no stock like this in your portfolio");
        }

    }

}
