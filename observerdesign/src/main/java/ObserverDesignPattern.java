public class ObserverDesignPattern {

    public static void main(String[] args) {

        Book book = new Book("The silence", "Science", "Xyz", 200, "SoldOut");
        EndUser user1 = new EndUser("July", book);
        EndUser user2 = new EndUser("Stephan", book);

        System.out.println(book.getInStock());

        book.setInStock("In stock");
    }
}
