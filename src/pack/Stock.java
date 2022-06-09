package pack;

public class Stock extends Investment {
    private String m_company;                   //name of a company

    public Stock(double price, int number,String company)
    {
        super(price, number);
        m_company = company;
    }
}
