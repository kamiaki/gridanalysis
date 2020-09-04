import com.google.gson.Gson;
import contour.tools.IDWMaker;
import contour.tools.KrigingMaker;
import contour.utils.CsvParser;

import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        //拿到数据
        String s = "[[105.12,29.62,9.4375],[110.012,39.815,16.133333206176758],[113.15,40.45,37.962501525878906],[121.24,41.518,7.625],[121.251,41.327,15.833333015441895],[109.033,39.1,11.074999809265137],[108.517,41.567,17.450000762939453],[108.641,40.729,6.824999809265137],[107.05,41.067,18.28333282470703],[118.833,45.717,18.704166412353516],[106.65,39.433,4.241666793823242],[106.803,39.796,11.608333587646484],[102.392,36.481,14.375],[111.937,43.63,13.291666984558105],[111.917,37.033,27.366666793823242],[81.326,43.941,11.716666221618652],[125.317,43.353,27.891666412353516],[109.715,39.562,15.529166221618652],[76.777,39.622,20.200000762939453],[130.305,46.786,30.366666793823242],[113.476,35.238,31.320833206176758],[101.867,25.733,41.32083511352539],[117.49,43.264,9.804166793823242],[124.774,43.509,24.97916603088379],[105.233,36.967,25.9375],[120.633,40.617,26.87826156616211],[125.15,44.383,22.10416603088379],[111.214,39.844,12.4375],[112.475,40.524,21.08333396911621],[121.362,41.138,19.766666412353516],[117.546,32.856,32.733333587646484],[124.522,45.06,14.524999618530273],[107.88,28.63,8.612500190734863],[115.378,37.373,17.049999237060547],[82.203,44.896,17.799999237060547],[113.943,35.126,14.383333206176758],[123.533,43.5,19.587499618530273],[77.414,37.857,25.28333282470703],[105.75,39.783,3.1041667461395264],[128.96,42.51,20.616666793823242],[118.7,41.933,18.34166717529297],[119.767,41.117,25.174999237060547],[102.433,28.3,24.21666717529297],[111.65,41.55,10.112500190734863],[110.096,41.031,20.112499237060547],[110.529,41.555,17.008333206176758],[111.116,40.715,22.733333587646484],[111.167,40.717,28.02916717529297],[121.372,46.29,33.30416488647461],[115.267,41.883,14.608333587646484],[120.65,42.85,17.04166603088379],[105.404,37.886,15.324999809265137],[112.976,38.495,17.20833396911621],[120.704,42.341,17.024999618530273],[119.3,44.45,17.15833282470703],[112.62,41.294,14.358333587646484],[112.619,41.293,12.433333396911621],[102.028,35.942,30.64583396911621],[118.417,42.583,16.1875],[104.8,40.167,3.237499952316284],[120.333,45.067,12.1875],[120.767,31.65,38.55833435058594],[107.683,23.4,28.704166412353516],[118.2,28.433,17.83333396911621],[119.517,32.417,29.049999237060547],[121.765,42.833,28.08333396911621],[119.6,41.383,16.487499237060547],[124.046,42.527,50.09166717529297],[121.293,43.604,8.375],[122.55,42.383,16.608333587646484],[122.533,42.383,23.912500381469727],[116.873,30.682,31.662500381469727],[120.9,44.567,24.195833206176758],[102.367,41.367,4.316666603088379],[119.944,42.294,21.504348754882812],[123.538,42.03,36.954166412353516],[125.149,41.653,32.32083511352539],[116.81,48.678,13.095833778381348],[122.836,41.943,5.929166793823242],[114.975,32.737,26.14583396911621],[113.6,24.683,9.770833015441895],[114.688,34.581,14.195833206176758],[108.714,39.811,13.6875],[118.834,42.308,19.516666412353516],[118.036,43.632,20.837499618530273],[36.429,94.91,14.869872093200684],[113.088,32.529,32.15833282470703],[125.317,41.267,11.462499618530273],[126.767,42.983,39.16666793823242],[107.779,30.684,6.224999904632568],[124.3,43.35,28.46666717529297],[126.517,44.85,22.191667556762695],[116.0,42.233,9.925000190734863],[111.461,41.076,16.179166793823242],[126.518,43.681,33.287498474121094],[114.3,26.967,28.950000762939453],[116.629,40.731,26.379167556762695],[108.717,37.85,16.454166412353516],[123.348,42.61,18.554166793823242],[106.4,41.4,8.933333396911621],[122.749,40.88,18.620832443237305],[124.821,42.031,48.76250076293945],[111.659,39.923,19.02083396911621],[120.048,30.864,22.991666793823242],[110.123,42.523,12.054166793823242],[119.233,34.25,32.545833587646484],[123.067,41.467,21.379167556762695],[110.674,34.342,29.133333206176758],[122.15,40.177,16.870832443237305],[110.167,22.65,16.70833396911621],[109.967,41.767,19.141666412353516],[97.95,24.7,38.99166488647461],[122.383,40.348,20.799999237060547],[122.176,41.43,11.441666603088379],[105.18,34.17,21.575000762939453],[108.452,34.488,18.22916603088379],[121.478,45.056,15.512499809265137],[122.248,43.573,15.720833778381348],[109.863,40.58,19.4375],[113.26,39.17,14.208333015441895],[120.117,40.183,21.662500381469727],[110.217,37.5,16.975000381469727],[120.35,41.183,21.608333587646484],[119.017,42.917,11.612500190734863],[122.074,46.711,37.60416793823242],[122.017,44.033,32.19583511352539],[113.589,33.46,26.66666603088379],[108.311,29.014,10.908333778381348],[112.633,42.75,10.9375],[113.633,43.85,11.966666221618652],[120.384,36.722,36.29999923706055],[105.882,30.979,28.370832443237305],[117.242,28.312,17.59166717529297],[111.727,40.656,15.875],[122.75,41.493,15.283333778381348],[123.033,41.217,18.70833396911621],[110.051,40.38,22.620832443237305],[110.041,40.406,18.975000381469727],[110.441,41.706,15.004166603088379],[120.96,40.873,38.44583511352539],[114.15,44.617,12.466666221618652],[115.948,35.667,29.512500762939453],[107.967,39.083,10.0625],[115.491,35.583,15.354166984558105],[119.539,31.71,32.36249923706055],[123.783,42.233,31.84583282470703],[116.117,43.95,6.650000095367432],[123.167,45.85,22.866666793823242],[113.833,42.233,31.96666717529297],[125.217,43.9,25.120832443237305],[117.513,36.822,19.3125],[116.167,37.819,21.84791660308838],[115.003,44.017,10.883333206176758],[105.569,39.032,10.858333587646484],[102.783,39.417,8.033333778381348],[113.134,41.443,19.60416603088379],[119.65,45.55,31.274999618530273],[121.067,42.4,18.483333587646484],[133.994,46.793,42.43333435058594],[121.817,44.9,9.758333206176758],[114.316,35.715,21.14583396911621],[130.196,47.338,32.02916717529297],[113.37,36.514,21.725000381469727],[122.001,41.729,26.22916603088379],[120.333,41.5,23.587499618530273]]";
        Double[][] doubles = new Gson().fromJson(s, Double[][].class);
        double[][] doubles1 = DtodoubleArr(doubles);
        //获取边界尺寸
        double left = 60.42;
        double right = 152.48;
        double bottom = 10.01;
        double top = 57.35;
        double[][] bounds = {{left, bottom}, {right, top}};
        //缩放比例
        int zoom = 4;
        //输出路径
        String outPutPath = "D:/tmp/country";

        IDWMaker.testCountry(doubles1, bounds, zoom, outPutPath);
//        KrigingMaker.testZhangzhouCity2();
    }

    /**
     * Double包装类数组 转 double数组
     *
     * @param matrix
     * @return
     */
    public static double[][] DtodoubleArr(Double[][] matrix) {
        double[][] matrixArr = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == null) {
                    matrixArr[i][j] = 0;
                } else {
                    matrixArr[i][j] = matrix[i][j];
                }
            }
        }
        return matrixArr;
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