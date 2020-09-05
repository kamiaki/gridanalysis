package contour.tools;

import contour.bean.Tuple5;
import contour.draw.KrigingImage;
import contour.oooooooo.BianJie;

import java.util.List;
import java.util.Map;

/**
 * KrigingTest
 */
public class KrigingMaker extends Maker {
    public void createKrigingImage(double[][] dataArr, String[] areaArr, String outPutPath) throws Exception {
        //处理省
        areaArr = processingProvince(areaArr);
        //设置颜色
        List<Tuple5<Double, Double, Integer, Integer, Integer>> colors = getColors("coloroption/color.csv");
        //获取边界尺寸
        double[][] bounds = new BianJie().getNewBoundary(areaArr);
        //精度计算
        Map<String, Object> crsParams = calculationAccuracy(areaArr);
        //创建画图类
        KrigingImage krigingImage = new KrigingImage(dataArr, colors, bounds, outPutPath, areaArr, crsParams);
        //画图
        krigingImage.draw();
    }
}
