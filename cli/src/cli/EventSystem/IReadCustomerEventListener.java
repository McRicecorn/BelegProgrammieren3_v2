package cli.EventSystem;

import administration.Customer;

import cli.Events.CustomerEvent;

import java.util.List;

public interface IReadCustomerEventListener {
    List<? extends Customer> handle(CustomerEvent e);
}
