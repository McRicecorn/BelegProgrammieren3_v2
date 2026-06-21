package cli.EventSystem.Handler;

import administration.Customer;
import cli.EventSystem.Events.CustomerEvent;

import cli.EventSystem.Interfaces.IReadCustomerEventHandler;
import cli.EventSystem.Interfaces.IReadCustomerEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadCustomerEventHandler implements IReadCustomerEventHandler {
    private List<IReadCustomerEventListener> listenerList = new ArrayList<>();

        public void addListener(IReadCustomerEventListener listener){
            listenerList.add(listener);
        }
        public void removeListener(IReadCustomerEventListener listener){
            listenerList.remove(listener);
        }

        @Override
        public List<? extends Customer> handle(CustomerEvent e) {
            for (IReadCustomerEventListener listener : listenerList) {

                IReadCustomerEventListener readListener = listener;
                List<? extends Customer> result = readListener.handle(e);
                if (result != null) {
                    return result;
                }

            }

            return List.of();
        }
}
