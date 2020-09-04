package contour.tools;

import com.google.gson.Gson;
import contour.bean.Tuple5;
import contour.draw.KrigingImage;
import contour.utils.CsvParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * KrigingTest
 */
public class KrigingMaker {
    public static void testZhangzhouCity2() {
        double[] mapCenter = {117.661801, 24.510897};
        int zoom = 10;
        double clientWidth = 1536d;
        double clientHeight = 731d;

        Map<String, Object> crsParams = new HashMap<>();
        crsParams.put("mapCenter", mapCenter);
        crsParams.put("zoom", zoom);
        crsParams.put("clientWidth", clientWidth);
        crsParams.put("clientHeight", clientHeight);
        crsParams.put("zoom", zoom);

        double left = 116.760922;
        double right = 118.364926;
        double bottom = 23.391427;
        double top = 25.402349;

        String filePath = "contour/city/zhangzhou/";
        String timestamp = "2020-05-25-0900";
        double[][] bounds = {{left, bottom}, {right, top}};
        List<Tuple5<Double, Double, Integer, Integer, Integer>> colors = getColors(filePath);
        double[][] rawdata = getData(filePath, timestamp);

        String pathBeiFen = "contour/city/zhangzhou/border.csv";
        KrigingImage krigingImage = new KrigingImage(rawdata, colors, bounds, "D:/tmp/zhangzhou-" + timestamp + "_k", new String[]{"11","12"}, crsParams);
        krigingImage.draw();
    }

    private static double[][] getData(String path, String timestamp) {
        String _path = "";
        if (!"".equals(timestamp) && timestamp != null) {
            _path = path + "data-" + timestamp + ".csv";
        } else {
            _path = path + "data.csv";
        }
        System.out.println(_path);
        String dataPath = KrigingMaker.class.getClassLoader()
                .getResource(_path).getPath();
        List<Map<String, String>> dataList = CsvParser.parse(dataPath);
        double[][] retList = new double[dataList.size()][3];
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, String> map = dataList.get(i);
            Double lon = Double.parseDouble(map.get("LON").trim());
            Double lat = Double.parseDouble(map.get("LAT").trim());
            Double value = Double.parseDouble(map.get("VALUE").trim());
            retList[i][0] = lon;
            retList[i][1] = lat;
            retList[i][2] = value;
        }
        return retList;
    }

    private static List<Tuple5<Double, Double, Integer, Integer, Integer>> getColors(String path) {
        String colorPath = KrigingMaker.class.getClassLoader().getResource(path + "color.csv").getPath();
        List<Map<String, String>> colorList = CsvParser.parse(colorPath);
        List<Tuple5<Double, Double, Integer, Integer, Integer>> retList = new ArrayList<>();
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
