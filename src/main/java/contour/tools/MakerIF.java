package contour.tools;

public interface MakerIF {
    /**
     * 创建图片
     * @param dataArr
     * @param areaArr
     * @param outPutPath
     * @throws Exception
     */
    void createImage(double[][] dataArr, String[] areaArr, String outPutPath) throws Exception;
}
