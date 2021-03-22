public class EndUser implements Observer {

    private String name;

    public EndUser(String name, SubjectLibrary subject) {
        this.name = name;
        subject.subscribeObserver(this);
    }

    public void update(String avail) {
        System.out.println("Hello " + this.name + ", we are glad to notify you that your book is now:" + avail);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
