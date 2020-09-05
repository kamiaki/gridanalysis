package contour.tools;

import contour.bean.Tuple5;
import contour.draw.IDWImage;
import contour.oooooooo.BianJie;
import contour.utils.CsvParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IDWMaker
 */
public class IDWMaker {
    public static void testCountry(double[][] dataArr, String[] areaArr, String outPutPath) throws Exception {
        //设置颜色
        List<Tuple5<Double, Double, Integer, Integer, Integer>> colors = getColors("coloroption/color.csv");
        //获取边界尺寸
        double[][] bounds = new BianJie().getNewBoundary(areaArr);
        //精度 计算
        // 34 ~ 1    x
        // 4 ~ 10    y
        // 10 - x/34 * 6
        int zoom = 10 - (int) Math.round((double) areaArr.length / 34) * 6;
        Map<String, Object> crsParams = new HashMap();
        crsParams.put("mapCenter", new double[]{0, 0});
        crsParams.put("clientWidth", 0D);
        crsParams.put("clientHeight", 0D);
        crsParams.put("zoom", zoom);
        //创建画图类
        IDWImage idwImage = new IDWImage(dataArr, colors, bounds, outPutPath, areaArr, crsParams);
        //画图
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
