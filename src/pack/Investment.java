package pack;

public abstract class Investment implements Cloneable{
    private double m_price;                 //all prices are in USD
    private int m_amount;                   //amount of stocks, currency and e.t.

    public Investment()
    {
        m_price = 100;
        m_amount = 5;
    }

    public Investment(double price, int number)
    {
        m_price = price;
        m_amount = number;
    }

    public void setPrice(double price)
    {
        if (price > 0) m_price = price;
    }

    public void setAmount(int number)
    {
        if (number > 0) m_amount = number;
    }

    public double getPrice() { return m_price; }

    public int getAmount() { return m_amount; }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
