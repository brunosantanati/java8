package me.brunosantana.java8.flatmap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyClass {
    private List<Map<String, String>> result;

    public List<Map<String, String>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, String>> result) {
        this.result = result;
    }
}

public class FlatMapExemplo3 {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Map<String, String> map2 = new HashMap<>();
        map2.put("key4", "value4");
        map2.put("key5", "value5");
        map2.put("key6", "value6");
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        list.add(map2);
        myClass.setResult(list);

        System.out.println(myClass.getResult()); //output: [{key1=value1, key2=value2, key3=value3}, {key5=value5, key6=value6, key4=value4}]

        Map<String, String> allMapsTogether = myClass.getResult().stream() //Stream<Map<String, String>>
                .flatMap(m -> m.entrySet().stream()) //Stream<Map.Entry<String, String>>
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> replacement)); //explanation of this line below:
        /*
        Let's break down the components:
        1- Map.Entry::getKey: This is a method reference to the getKey method of the Map.Entry interface. It represents a function that extracts the key from a Map.Entry instance.
        2 - Map.Entry::getValue: Similar to the first part, this is a method reference to the getValue method of the Map.Entry interface. It represents a function that extracts the value from a Map.Entry instance.
        3- (existing, replacement) -> replacement: This is a merge function. When the collector encounters two entries with the same key (which would be a conflict when merging into a single map), this function is called to resolve the conflict. In this case, it says that if there is a conflict, choose the replacement value. The existing parameter represents the value already present in the map, and the replacement parameter represents the new value.
        Putting it all together, the Collectors.toMap collector is used to accumulate elements into a Map. In this case, it takes the key and value from each Map.Entry in the stream and uses the provided merge function to resolve conflicts if there are duplicate keys. This way, you end up with a merged map that contains all the entries from the original maps.
         */

        System.out.println(allMapsTogether); //output: {key1=value1, key2=value2, key5=value5, key6=value6, key3=value3, key4=value4}
        System.out.println(allMapsTogether.get("key1")); //output: value1

        //############################################

        Optional<String> key5 = myClass.getResult().stream()
                .flatMap(m -> m.entrySet().stream())
                .filter(entry -> entry.getKey().equals("key5"))
                .map(Map.Entry::getValue)
                .findFirst();

        System.out.println(key5.get()); //output: value5
    }

}
