package delilah.personal.inumapus.model;

import com.google.gson.annotations.SerializedName;

public class BuildingModel {
    @SerializedName("id") // 숫자 - 호관 번호
    public int id;

    @SerializedName("number") // 숫자 - 호관 번호
    public String number;

    @SerializedName("title") // 건물이름 - ex.대학본부
    public String title;

    @SerializedName("lat") // latitude 경도
    public double lat;

    @SerializedName("log") // longitude 위도
    public double log;
}
