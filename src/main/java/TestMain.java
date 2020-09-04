import contour.tools.IDWMaker;
import contour.tools.KrigingMaker;
import contour.utils.CsvParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        //拿到数据
        String filePath = "contour/country/";
        double[][] dataArr = getData(filePath, "");
        //获取边界尺寸
        double left = 60.42;
        double right = 152.48;
        double bottom = 10.01;
        double top = 57.35;
        double[][] bounds = {{left, bottom}, {right, top}};
        //缩放比例
        int zoom = 4;


        String outPutPath = "D:/tmp/country";
        IDWMaker.testCountry(dataArr, bounds, zoom, outPutPath);
//        KrigingMaker.testZhangzhouCity2();
    }

    private static double[][] getData(String path, String timestamp) {
        String _path;
        if (!"".equals(timestamp) && timestamp != null) {
            _path = path + timestamp + ".csv";
        } else {
            _path = path + "data.csv";
        }
        System.out.println(_path);
        String dataPath = IDWMaker.class.getClassLoader().getResource(_path).getPath();
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
}
