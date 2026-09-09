package peli1gamer.arsonclient.settings;

import java.util.Objects;

public abstract class Setting<T> {
    private final String id;
    private final String name;
    private final T defaultValue;
    private T value;

    protected Setting(String id, String name, T defaultValue) {
        this.id = Objects.requireNonNull(id); this.name = Objects.requireNonNull(name);
        this.defaultValue = Objects.requireNonNull(defaultValue); this.value = defaultValue;
    }
    public String id() { return id; }
    public String name() { return name; }
    public T get() { return value; }
    public void set(T value) { this.value = Objects.requireNonNull(value); }
    public T defaultValue() { return defaultValue; }
    public void reset() { value = defaultValue; }
}
