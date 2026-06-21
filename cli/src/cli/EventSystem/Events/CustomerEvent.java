package cli.EventSystem.Events;

public class CustomerEvent extends EventObject {
    private String name;
    public CustomerEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
