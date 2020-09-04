package contour.tools;

import com.google.gson.Gson;
import contour.bean.Tuple5;
import contour.draw.IDWImage;
import contour.utils.CsvParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IDWMaker
 */
public class IDWMaker {
    public static void testCountry(double[][] dataArr, double[][] bounds, int zoom, String outPutPath) {
        Map<String, Object> crsParams = new HashMap();
        crsParams.put("mapCenter", new double[]{0, 0});
        crsParams.put("clientWidth", 0D);
        crsParams.put("clientHeight", 0D);
        crsParams.put("zoom", zoom);

        List<Tuple5<Double, Double, Integer, Integer, Integer>> colors = getColors("contour/country/color.csv");
//        IDWImage idwImage = new IDWImage(dataArr, colors, bounds, outPutPath, new String[]{"China"}, crsParams);
        IDWImage idwImage = new IDWImage(dataArr, colors, bounds, outPutPath, new String[]{"China"}, crsParams);
        idwImage.draw();

    }

    private static List<Tuple5<Double, Double, Integer, Integer, Integer>> getColors(String path) {
        String colorPath = IDWMaker.class.getClassLoader().getResource(path).getPath();
        List<Map<String, String>> colorList = CsvParser.parse(colorPath);
        List<Tuple5<Double, Double, Integer, Integer, Integer>> retList = new ArrayList();
        for (Map<String, String> map : colorList) {
            Double value_min = Double.parseDouble(map.get("VALUE_MIN").trim());
            Double value_max = Double.parseDouble(map.get("VALUE_MAX").trim());
            int r = Integer.parseInt(map.get("R").trim());
            int g = Integer.parseInt(map.get("G").trim());
            int b = Integer.parseInt(map.get("B").trim());
            retList.add(new Tuple5(value_min, value_max, r, g, b));
        }
        return retList;
    }
}
