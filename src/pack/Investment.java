package pack;

public abstract class Investment implements Cloneable{
    private double m_price;                 //all prices are in USD
    private int m_amount;                   //amount of stocks, currency and e.t.
    private String m_key;                   //name of a company, currency name and e.t.

    public Investment()
    {
        m_price = 100;
        m_amount = 5;
        m_key = "";
    }

    public Investment(double price, int number, String key)
    {
        m_price = price;
        m_amount = number;
        m_key = key;
    }

    public void setPrice(double price)
    {
         m_price = price;
    }

    public void setAmount(int number)
    {
         m_amount = number;
    }

    public double getPrice() { return m_price; }

    public int getAmount() { return m_amount; }

    public String getKey() { return m_key; }

    public void setKey(String key) { m_key = key; }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        Investment temp = (Investment) super.clone();
        temp.setKey(temp.getKey());
        return temp;
    }
}
