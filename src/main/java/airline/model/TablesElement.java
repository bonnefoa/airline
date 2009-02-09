package airline.model;

/**
 * User: sora
 * Date: 26 janv. 2009
 * Time: 14:50:42
 */
public class TablesElement<T> {
    private T value;

    public TablesElement(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
