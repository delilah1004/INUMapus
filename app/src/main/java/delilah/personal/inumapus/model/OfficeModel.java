package delilah.personal.inumapus.model;

import com.google.gson.annotations.SerializedName;

public class OfficeModel {

    @SerializedName("title") // 사무실 이름 - ex.종합상황실
    public String title;

    @SerializedName("roomId") // 사무실 호수 - ex.B101,101 등등
    public String roomId;
}
