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
        Geometry boundary = bianJie.getBoundary(new String[]{"China"});
        System.out.println(boundary);
    }

    public static List<List<PointD>> readMapData(String[] areaArr) {
        List<List<PointD>> _clipLines = new ArrayList<>();
        try {
            LinkedList<Geometry> geometries = bianJie.getBoundaryFen(areaArr);
            for (Geometry geometry : geometries) {
                Coordinate[] coordinates = geometry.getCoordinates();
                List<PointD> pointDS = new ArrayList<>();
                for (Coordinate coordinate : coordinates) {
                    pointDS.add(new PointD(coordinate.x, coordinate.y));
                }
                _clipLines.add(pointDS);
            }
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
