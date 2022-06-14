package pack;

public class Stock extends Investment {

    public Stock(double price, int number,String key)
    { super(price, number,key); }

    public static Investment findInvestmentOnMarket(String key)
    {
        Investment stock_ref = null;                                     //find the stock on the market
        for (Investment temp : StockMarket.getArrAllInvestments())            //that a trader wants to buy
        {
            if (temp.getKey().equals(key))
            {
                stock_ref = temp;
                break;
            }
        }
        return stock_ref;
    }
    public static Investment checkTraderInvestments(String key)
    {
        Investment stock_ref = null;                                                 //find out if the trader has
        for (Investment temp : StockMarket.getCurrentTrader().getArrInvestments())        //this stock in his portfolio
        {
            if (temp.getKey().equals(key))
            {
                stock_ref = temp;
                break;
            }
        }

        return stock_ref;
    }
}
