package contour.oooooooo;


import org.apache.commons.lang3.builder.ToStringBuilder;


public class Feature {

    private String type;

    private Properties properties;

    //geojson 地图 边界
    private GeometryData geometry;


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public GeometryData getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryData geometry) {
        this.geometry = geometry;
    }


    public Properties getProperties() {
        return properties;
    }


    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
