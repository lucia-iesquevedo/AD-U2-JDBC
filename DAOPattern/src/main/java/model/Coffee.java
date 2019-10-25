
package model;

public class Coffee {
    String name;
    int price;
    int sales;
    int total;
    
    public Coffee(String name, int price, int sales, int total) {
        this.name = name;
        this.price = price;
        this.sales = sales;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "Coffee{" + "name=" + name + ", price=" + price + ", sales=" + sales + ", total=" + total + '}';
    }
}