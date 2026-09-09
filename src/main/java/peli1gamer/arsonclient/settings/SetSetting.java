package peli1gamer.arsonclient.settings;
import java.util.*;
/** Searchable multi-select setting used by entity and block filters. */
public final class SetSetting<T> extends Setting<Set<T>> {
 private final Set<T> allowed;
 public SetSetting(String id,String name,Collection<T> allowed,Collection<T> defaults){super(id,name,new LinkedHashSet<>(defaults));this.allowed=Set.copyOf(allowed);}
 @Override public void set(Set<T> value){LinkedHashSet<T> copy=new LinkedHashSet<>(value);copy.retainAll(allowed);super.set(copy);}
 public Set<T> allowed(){return allowed;} public boolean contains(T value){return get().contains(value);} public void toggle(T value){if(!allowed.contains(value))return;Set<T> next=new LinkedHashSet<>(get());if(!next.add(value))next.remove(value);set(next);} public List<T> search(String query){String q=query==null?"":query.toLowerCase(Locale.ROOT);return allowed.stream().filter(v->String.valueOf(v).toLowerCase(Locale.ROOT).contains(q)).toList();}
}
