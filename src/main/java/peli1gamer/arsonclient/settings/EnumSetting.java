package peli1gamer.arsonclient.settings;
public final class EnumSetting<T extends Enum<T>> extends Setting<T> { private final Class<T> type; public EnumSetting(String id,String name,T value,Class<T> type){super(id,name,value);this.type=type;} public T[] values(){return type.getEnumConstants();} }
