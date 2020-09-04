package contour.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import contour.draw.spatial.PointD;
import contour.oooooooo.BianJie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 读取地图数据
 */
public class MapUtils {
    private static final BianJie bianJie = new BianJie();

    public static void main(String[] args) throws Exception {
        LinkedList<Geometry> geometries = new LinkedList<>();
        Geometry boundary = bianJie.getBoundary(new String[]{"China"});
        System.out.println(boundary);
    }

    public static List<List<PointD>> readMapData(String[] areaArr) {
        areaArr = new String[]{"China"};
//        areaArr = new String[]{"340000", "350000", "620000", "630000", "640000"};
        List<List<PointD>> _clipLines = new ArrayList<>();

        try {
            if (areaArr[0] == "China") {
                areaArr = new String[]{"110000", "120000", "130000", "140000", "150000",
                        "210000", "220000", "230000", "310000", "320000",
                        "330000", "340000", "350000", "360000", "370000",
                        "410000", "420000", "430000", "440000", "450000",
                        "460000", "500000", "510000", "520000", "530000",
                        "540000", "610000", "620000", "630000", "640000",
                        "650000", "710000", "810000", "820000"};
            }
            LinkedList<Geometry> geometries = bianJie.getBoundaryFen(areaArr);
            for (Geometry geometry : geometries) {
                Coordinate[] coordinates = geometry.getCoordinates();
                List<PointD> pointDS = new ArrayList<>();
                for (Coordinate coordinate : coordinates) {
                    pointDS.add(new PointD(coordinate.x, coordinate.y));
                }
                _clipLines.add(pointDS);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return _clipLines;
    }

    /* 解析地图数据
     * @param list
     * @return
     * @throws Exception
     */
    private static List<List<PointD>> parseMapData(List<Map<String, String>> list) throws Exception {
        List<List<PointD>> clipLines = new ArrayList<>();
        for (int i = 0, size = list.size(); i < size; i++) {
            Map<String, String> dataMap = list.get(i);
            String[] spots = dataMap.get("REGION").split(",");
            List<PointD> spotPoints = new ArrayList<>();
            for (String s : spots) {
                PointD aPoint = new PointD();
                String horizontal = s.split("\\s+")[0];
                String vertical = s.split("\\s+")[1];
                aPoint.X = Double.valueOf(horizontal);
                aPoint.Y = Double.valueOf(vertical);
                spotPoints.add(aPoint);
            }
            clipLines.add(spotPoints);
        }
        return clipLines;
    }

}
