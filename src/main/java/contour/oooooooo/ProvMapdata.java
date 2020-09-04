package contour.oooooooo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class ProvMapdata {


    private String type;

    private List<Feature> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
