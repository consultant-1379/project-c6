import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, String> nameMap = new HashMap();
        String test = "{'Johnny Urosevic': 47.38, 'johnnyurosevic': 31.6, 'Bao Pham': 3.34, 'Christie Ruales': 6.44, 'wleeym08': 1.41, 'Rutvik Parekh': 0.04, 'RileyChampion': 1.52, 'Celia Zhou': 8.27} ";

        test = test.replaceAll("[{'}]", "");

        String data[] = test.split(",");

        for (String d : data) {
            String[] d2 = d.split(":");

            for (String i : d2) {
                nameMap.put(d2[0], d2[1]);
            }
        }

        System.out.println(nameMap.entrySet());
    }
}
