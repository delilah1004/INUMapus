package delilah.personal.inumapus.model;

import com.google.gson.annotations.SerializedName;

public class FilterModel {
    @SerializedName("id") // 숫자
    public int id;

    @SerializedName("title") // 필터 이름 - ex.카페,식당 등
    public String title;

    @SerializedName("buildingId") // 숫자
    public String buildingId;

    @SerializedName("filterId") // 필터 이름 - ex.카페,식당 등
    public int filterId;

    @SerializedName("lat")
    public double lat;

    @SerializedName("log")
    public double log;
}
