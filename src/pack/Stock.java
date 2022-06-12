package pack;

public class Stock extends Investment {
    private String m_company;                   //name of a company

    public Stock(double price, int number,String company)
    {
        super(price, number);
        m_company = company;
    }

    public String getCompany() { return m_company; }

    public static Stock findStockOnMarket(String company)
    {
        Stock stock_ref = null;                                     //find the stock on the market
        for (Stock temp : StockMarket.getArrAllStocks())            //that a trader wants to buy
        {
            if (temp.getCompany().equals(company))
            {
                stock_ref = temp;
                break;
            }
        }
        return stock_ref;
    }
    public static Stock checkTraderStocks(String company)
    {
        Stock stock_ref = null;                                                 //find out if the trader has
        for (Stock temp : StockMarket.getCurrentTrader().getArrStocks())        //this stock in his portfolio
        {
            if (temp.getCompany().equals(company))
            {
                stock_ref = temp;
                break;
            }
        }
        return stock_ref;
    }
}
