package pack;
import java.util.*;

public class Trader {
    private final int m_id;
    private double m_money;                          //the amount of money on a trader's account
    private List<Investment> m_arr_investments;

    public Trader()
    {
        m_id = 0;
        m_money = 5000;
        m_arr_investments = new ArrayList<>();
    }

    public Trader(double money, int id)
    {
        m_id = id;
        m_money = money;
        m_arr_investments = new ArrayList<>();
    }

    public int getID() { return m_id; }

    public void invest(int ind_obj,int number)
    {
        Investment invest_obj = StockMarket.getArrAllInvestments().get(ind_obj);
        double investments_price = invest_obj.getPrice() * number;
        if (m_money - investments_price > 0)
        {
            if (invest_obj.getAmount() > number)
            {
                try
                {
                    Investment temp = (Investment) invest_obj.clone();

                    int amount_before = invest_obj.getAmount();             //amount of investments
                                                                            // that were available before the purchase

                    int amount_after = invest_obj.getAmount() - number;     //that are available after the purchase

                    invest_obj.setAmount(amount_after);
                    temp.setAmount(amount_before - amount_after);
                    m_arr_investments.add(temp);
                }
                catch (CloneNotSupportedException exep)
                { exep.printStackTrace(); }
            }
            else
            {
                //add GUI notification
            }
        }
            else
        {
            //add GUI notification
        }
    }

    public void sellInvestment(int ind_obj, int number)
    {
        Investment invest_obj = m_arr_investments.get(ind_obj);
        if (invest_obj instanceof Stock)
        {

        }
    }

}
