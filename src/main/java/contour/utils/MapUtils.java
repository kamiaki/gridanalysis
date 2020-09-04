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
        Geometry boundary = bianJie.getBoundary(new String[]{"China"}, geometries);
        System.out.println(boundary);
    }

    public static List<List<PointD>> readMapData(String[] areaArr) {
        areaArr = new String[]{"China"};
//        areaArr = new String[]{"110000,120000,130000"};
        List<List<PointD>> _clipLines = new ArrayList<>();
        LinkedList<Geometry> geometries = new LinkedList<>();

        try {
            String borderPath = MapUtils.class.getClassLoader().getResource("contour/country/border.csv").getPath();
            List<Map<String, String>> borderList = CsvParser.parse(borderPath);
            List<List<PointD>> _clipLines2 = parseMapData(borderList);
            System.out.println(_clipLines2);

            Geometry boundary = bianJie.getBoundary(areaArr, geometries);
            Coordinate[] coordinates = boundary.getCoordinates();
            List<PointD> pointDS = new ArrayList<>();
            for (Coordinate coordinate : coordinates) {
                double x = new BigDecimal(coordinate.x).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                double y = new BigDecimal(coordinate.y).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                pointDS.add(new PointD(x, y));
            }
            _clipLines.add(pointDS);
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
