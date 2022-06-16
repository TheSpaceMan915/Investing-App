package pack;
import javax.swing.*;
import java.util.*;

public class Trader {
    private final int m_id;
    private double m_money;                          //the amount of money on a trader's account
    private final List<Investment> m_arr_investments;

    public Trader()
    { this(0,-1); }

    public Trader(double money, int id)
    {
        m_id = id;
        m_money = money;
        m_arr_investments = new ArrayList<>();
    }

    public int getID() { return m_id; }

    public double getMoney() { return m_money; }

    public List<Investment> getArrInvestments() { return m_arr_investments; }

    private void setMoney(double money) { m_money = money; }

    public void buyInvestment(String key, int number)
    {
        Investment investment_on_market = Stock.findInvestmentOnMarket(key);
        double investments_price = 0.0;

        try
        { investments_price = investment_on_market.getPrice() * number; }
        catch (NullPointerException exep)
        {
            JOptionPane.showMessageDialog(GUI.getFrame(),"There is no stock like this on the stock market");
            exep.printStackTrace();
        }

        if (m_money - investments_price > 0)
        {
            if (investment_on_market.getAmount() >= number)
            {
                //amount of investments that were available before the purchase
                int amount_before = investment_on_market.getAmount();

                //amount of investments that are available after the purchase
                int amount_after = investment_on_market.getAmount() - number;
                investment_on_market.setAmount(amount_after);


                //if the trader has this type of investment object in his portfolio, add to that object new investments
                Investment trader_investment = Stock.checkTraderInvestments(key);
                if (trader_investment != null)
                { trader_investment.setAmount(trader_investment.getAmount() + number); }
                else
                {
                    Investment temp = null;
                    try
                    {
                        //make a clone of the object on the market for trader's portfolio
                        temp = (Investment) investment_on_market.clone();
                    }
                    catch (CloneNotSupportedException exep)
                        { exep.printStackTrace(); }

                    temp.setAmount(number);
                    m_arr_investments.add(temp);
                }

                m_money -= investments_price;
            }
            else
            { JOptionPane.showMessageDialog(GUI.getFrame(),"There are not enough stocks on the market. We only have " +  investment_on_market.getAmount() + " of them"); }
        }
        else
        { JOptionPane.showMessageDialog(GUI.getFrame(),"You don't have enough money on your account"); }
    }

    public void sellInvestment(String key, int number)
    {
        Investment investment_on_market = Stock.findInvestmentOnMarket(key);                 //the investment on the StockMarket
        Investment trader_investment = Stock.checkTraderInvestments(key);                  //the investment in trader's portfolio

        if (trader_investment != null)             //if the investment has been found in the trader's portfolio
        {
            investment_on_market.setAmount(investment_on_market.getAmount() + number);
            trader_investment.setAmount(trader_investment.getAmount() - number);

            double investments_price = investment_on_market.getPrice() * number;;
            StockMarket.getCurrentTrader().setMoney(StockMarket.getCurrentTrader().getMoney() + investments_price);

            if (trader_investment.getAmount() == 0)
            { StockMarket.getCurrentTrader().getArrInvestments().remove(trader_investment); }
        }
        else
        { JOptionPane.showMessageDialog(GUI.getFrame(),"There is no stock like this in your portfolio"); }
    }

}
