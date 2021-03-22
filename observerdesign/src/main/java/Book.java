import java.util.ArrayList;
import java.util.List;

public class Book implements SubjectLibrary {

    private String name;
    private String author;
    private String type;
    private double price;
    private String inStock;
    private List<Observer> observers = new ArrayList<Observer>();

    public Book(String name, String author, String type, double price, String inStock) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.price = price;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
        System.out.println("Availability changed from Sold out to Back in stock \n");
        notifyObserver();
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void subscribeObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void unsubscribeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObserver() {
        System.out.println(
                        "Book name: " + this.name +
                        ", Book type: " + this.type +
                        ", Book price: " + this.price +
                        ", Book author: " + this.author +
                        " is now: " + this.inStock + ". So, notify all users! \n"
        );
        for (Observer observer : this.observers) {
            observer.update(inStock);
        }
    }
}
