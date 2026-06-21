package cli.EventSystem;

import administration.Customer;
import cli.Events.CustomerEvent;

import java.util.List;

public interface IReadCustomerEventHandler {
    List<? extends Customer> handle(CustomerEvent e);
}
