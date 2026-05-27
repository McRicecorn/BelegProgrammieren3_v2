package domainLogic;

public class Customer implements administration.Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
