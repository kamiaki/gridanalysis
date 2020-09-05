package contour.utils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import contour.draw.spatial.PointD;
import contour.oooooooo.BianJie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 读取地图数据
 */
public class MapUtils {
    public static List<List<PointD>> readMapData(String[] areaArr) {
        List<List<PointD>> _clipLines = new ArrayList<>();
        try {
            LinkedList<Geometry> geometries = new BianJie().getBoundarys(areaArr);
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
}
