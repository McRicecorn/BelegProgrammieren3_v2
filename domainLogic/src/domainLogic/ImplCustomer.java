package domainLogic;

import administration.Customer;

public class ImplCustomer implements Customer {
    private String name;

    public ImplCustomer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }
}
