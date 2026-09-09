package peli1gamer.arsonclient.settings;
public final class IntSetting extends Setting<Integer> { private final int min,max; public IntSetting(String id,String name,int value,int min,int max){super(id,name,value);this.min=min;this.max=max;} @Override public void set(Integer value){super.set(Math.max(min,Math.min(max,value)));} public int min(){return min;} public int max(){return max;} }
