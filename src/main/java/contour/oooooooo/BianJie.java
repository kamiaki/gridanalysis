package contour.oooooooo;

import com.google.gson.Gson;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class BianJie {
    /**
     * new 获取上下左右 经纬度坐标
     * @param areaArr
     * @return
     * @throws Exception
     */
    public double[][] getTopBottomLeftRight(String[] areaArr) throws Exception {
        Geometry boundary = new BianJie().getBoundary(areaArr);
        Envelope envelopeInternal = boundary.getEnvelopeInternal();
        double left = envelopeInternal.getMinX();
        double right = envelopeInternal.getMaxX();
        double bottom = envelopeInternal.getMinY();
        double top = envelopeInternal.getMaxY();
        double[][] bounds = {{left, bottom}, {right, top}};
        return bounds;
    }

    /**
     * new 获取分好类的 边界
     * @param areaArr
     * @return
     * @throws Exception
     */
    public LinkedList<Geometry> getBoundarys(String[] areaArr) throws Exception {
        String geojsonCodes = array2String(areaArr);
        //开始获取边界信息
        String[] geojsonCodeArr = geojsonCodes.split(",");
        LinkedList<Geometry> geometries = new LinkedList<>();
        GeometryData temp = null;
        for (String geoCode : geojsonCodeArr) {
            if (isPostCodes(geoCode)) {
                //---------------------------------------
                //是邮政编码
                //---------------------------------------
                //获取json文件名
                String[] re = new String[2];//re[0]为文件名 re[1]为区域编号
                String head = geoCode.substring(0, 2);
                String middle = geoCode.substring(2, 4);
                String end = geoCode.substring(4, 6);
                if ("00".equals(end)) {
                    if ("00".equals(middle)) {
                        re[0] = "china.json";
                        re[1] = head;
                    } else {
                        re[0] = geoCode.substring(0, 2) + ".json";
                        re[1] = head + middle;
                    }
                } else {
                    re[0] = geoCode.substring(0, 4) + "00.json";
                    re[1] = geoCode;
                }
                //读取文件
                String fileStr = fileRead("/" + re[0]);
                Gson gson = new Gson();
                ProvMapdata provMapdata = gson.fromJson(fileStr, ProvMapdata.class);
                //根据邮编查找地区
                List<Feature> features = provMapdata.getFeatures();
                for (Feature feature : features) {
                    Properties properties = feature.getProperties();
                    String provid = properties.getId();
                    if (provid != null && provid.equals(re[1])) {
                        temp = feature.getGeometry();
                    }
                }
            } else if (isAllowGetCountry(geoCode)) {
                //---------------------------------------
                //是获取国家
                //---------------------------------------
                String str = fileRead("/world.json");
                Gson gson = new Gson();
                ProvMapdata provMapdata = gson.fromJson(str, ProvMapdata.class);
                //根据国家名称查找边界数据
                List<Feature> features = provMapdata.getFeatures();
                for (Feature feature : features) {
                    Properties properties = feature.getProperties();
                    String provid = properties.getNAME();
                    if (provid != null && provid.equals(geoCode)) {
                        GeometryData geometry = feature.getGeometry();
                        temp = geometry;
                    }
                }
            } else {
                throw new RuntimeException("边界错了");
            }
            if (temp != null) {
                geometries.add(temp.toGeometry());
            }
        }
        return geometries;
    }



    /**
     * 数组转字符串
     * @param strings
     * @return
     */
    private String array2String(String[] strings){
        if(strings == null || strings.length <= 0)return "";
        String array = Arrays.asList(strings)
                .toString().trim().replace(" ","")
                .replace("[","").replace("]","");
        return array;
    }

    /**
     * 判断是不是区域编号
     *
     * @param str
     * @return
     */
    private boolean isPostCodes(String str) {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        return pattern.matcher(str).matches();
    }

    public Geometry getBoundary(String[] areaArr) throws Exception {
        String geojsonCodes = array2String(areaArr);
        //如果缓存有直接返回
        Geometry geometryCache = null;
        if (geometryCache != null)return geometryCache;
        //开始获取边界信息
        String[] geojsonCodeArr = geojsonCodes.split(",");
        LinkedList<Geometry> geometries = new LinkedList<>();
        GeometryData temp = null;
        for (String geoCode : geojsonCodeArr) {
            if (isPostCodes(geoCode)) {
                //---------------------------------------
                //是邮政编码
                //---------------------------------------
                //获取json文件名
                String[] re = new String[2];//re[0]为文件名 re[1]为区域编号
                String head = geoCode.substring(0, 2);
                String middle = geoCode.substring(2, 4);
                String end = geoCode.substring(4, 6);
                if ("00".equals(end)) {
                    if ("00".equals(middle)) {
                        re[0] = "china.json";
                        re[1] = head;
                    } else {
                        re[0] = geoCode.substring(0, 2) + ".json";
                        re[1] = head + middle;
                    }
                } else {
                    re[0] = geoCode.substring(0, 4) + "00.json";
                    re[1] = geoCode;
                }
                //读取文件
                String fileStr = fileRead("/" + re[0]);
                Gson gson = new Gson();
                ProvMapdata provMapdata = gson.fromJson(fileStr, ProvMapdata.class);
                //根据邮编查找地区
                List<Feature> features = provMapdata.getFeatures();
                for (Feature feature : features) {
                    Properties properties = feature.getProperties();
                    String provid = properties.getId();
                    if (provid != null && provid.equals(re[1])) {
                        temp = feature.getGeometry();
                    }
                }
            } else if (isAllowGetCountry(geoCode)) {
                //---------------------------------------
                //是获取国家
                //---------------------------------------
                String str = fileRead("/world.json");
                Gson gson = new Gson();
                ProvMapdata provMapdata = gson.fromJson(str, ProvMapdata.class);
                //根据国家名称查找边界数据
                List<Feature> features = provMapdata.getFeatures();
                for (Feature feature : features) {
                    Properties properties = feature.getProperties();
                    String provid = properties.getNAME();
                    if (provid != null && provid.equals(geoCode)) {
                        GeometryData geometry = feature.getGeometry();
                        temp = geometry;
                    }
                }
            } else {
                throw new RuntimeException("边界错了");
            }
            if (temp != null) {
                geometries.add(temp.toGeometry());
            }
        }
        //边界合并 相邻边界做合并处理 嵌套边界做嵌套处理
        //todo 相邻边界做合并处理
        Geometry re = null;
        Iterator<Geometry> iterator = geometries.iterator();
        Geometry tempG;
        while (iterator.hasNext()) {
            tempG = iterator.next();
            if (re == null) {
                re = tempG;
            } else {
                re = re.union(tempG);
            }
        }
        return re;
    }

    /**
     * 读取文件到字符串
     *
     * @throws Exception
     */
    private String fileRead(String filePath) throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream(filePath);
//        InputStream inputStream= new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s;
        while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s);//将读取的字符串添加换行符后累加存放在缓存中
        }
        bReader.close();
        return sb.toString();
    }

    /**
     * 判断输入的国家名称是否允许被获得
     *
     * @param str
     * @return
     */
    private boolean isAllowGetCountry(String str) {
        String[] allowGetCountryArray = new String[]{"China"};
        for (String s : allowGetCountryArray) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

}
