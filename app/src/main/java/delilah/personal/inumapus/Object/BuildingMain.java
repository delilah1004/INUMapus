package delilah.personal.inumapus.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuildingMain {
    @SerializedName("buildingId")
    @Expose
    private String buildingId;
    @SerializedName("buildingName")
    @Expose
    private String buildingName;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("log")
    @Expose
    private Double log;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLog() {
        return log;
    }

    public void setLog(Double log) {
        this.log = log;
    }
}
