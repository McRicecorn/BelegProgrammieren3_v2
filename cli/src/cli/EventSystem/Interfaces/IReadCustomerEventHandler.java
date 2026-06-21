package cli.EventSystem.Interfaces;

import administration.Customer;
import cli.EventSystem.Events.CustomerEvent;

import java.util.List;

public interface IReadCustomerEventHandler {
    List<? extends Customer> handle(CustomerEvent e);
}
