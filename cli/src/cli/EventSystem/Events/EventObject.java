package cli.EventSystem.Events;

import java.io.Serializable;

public class EventObject implements Serializable {
    private static final long serialVersionUID = 1L;
    protected transient Object source;
    public EventObject(Object source) {
        if (source == null) {
            throw new IllegalArgumentException("null source");
        }
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

        public String toString() {
            return getClass().getName() + "[source=" + source + "]";
        }

}
