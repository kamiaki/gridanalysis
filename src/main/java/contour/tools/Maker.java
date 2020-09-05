package contour.tools;

import contour.bean.Tuple5;
import contour.utils.CsvParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Maker {
    /**
     * 获取颜色
     * @param path
     * @return
     */
    protected List<Tuple5<Double, Double, Integer, Integer, Integer>> getColors(String path) {
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

    /**
     * 处理省
     *
     * @param areaArr
     */
    protected String[] processingProvince(String[] areaArr) {
        if (areaArr[0] == "China") {
            return new String[]{"110000", "120000", "130000", "140000", "150000",
                    "210000", "220000", "230000", "310000", "320000",
                    "330000", "340000", "350000", "360000", "370000",
                    "410000", "420000", "430000", "440000", "450000",
                    "460000", "500000", "510000", "520000", "530000",
                    "540000", "610000", "620000", "630000", "640000",
                    "650000", "710000", "810000", "820000"};
        } else {
            return areaArr;
        }
    }

    /**
     * 计算精度
     * @param areaArr
     * @return
     */
    protected Map<String, Object> calculationAccuracy(String[] areaArr){
        // 34 ~ 1    x
        // 4 ~ 10    y
        // 10 - x/34 * 6
        int zoom = 10 - (int) Math.round((double) areaArr.length / 34) * 6;
        Map<String, Object> crsParams = new HashMap();
        crsParams.put("mapCenter", new double[]{0, 0});
        crsParams.put("clientWidth", 0D);
        crsParams.put("clientHeight", 0D);
        crsParams.put("zoom", zoom);
        return crsParams;
    }
}
