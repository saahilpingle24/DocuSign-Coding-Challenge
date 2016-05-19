import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class DataIngestionHelper {
    public static void defineInputData(String weather, Map<String, HashMap<String, String>> map) {
        List<String> clothes = getWeatherWiseData(weather);
        addData(weather,map, clothes);
    }

    protected static List<String> getWeatherWiseData(String weather) {
        List<String> temp = new ArrayList<>();
        if(weather=="hot") {
            temp.add("sandals");
            temp.add("sun visor");
            temp.add("fail");
            temp.add("t-shirt");
            temp.add("fail");
            temp.add("shorts");
            temp.add("leaving house");
            temp.add("Removing PJs");
        } else {
            temp.add("boots");
            temp.add("hat");
            temp.add("socks");
            temp.add("shirt");
            temp.add("jacket");
            temp.add("pants");
            temp.add("leaving house");
            temp.add("Removing PJs");
        }
        return temp;
    }

    protected static void addData(String weather, Map<String, HashMap<String, String>> map, List<String> clothes) {
        HashMap<String, String> internal_map = new LinkedHashMap<>();
        internal_map.put("1", clothes.get(0));
        internal_map.put("2", clothes.get(1));
        internal_map.put("3", clothes.get(2));
        internal_map.put("4", clothes.get(3));
        internal_map.put("5", clothes.get(4));
        internal_map.put("6", clothes.get(5));
        internal_map.put("7", clothes.get(6));
        internal_map.put("8", clothes.get(7));
        map.put(weather, internal_map);
    }
}
