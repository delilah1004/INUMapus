package delilah.personal.inumapus.model;

import com.google.gson.annotations.SerializedName;

public class EmployeeModel {
    @SerializedName("id")
    public int id;

    @SerializedName("detailOrgan") // 소속 - ex.총장실
    public String detailOrgan;

    @SerializedName("position") // 직위 - ex.총장
    public String position;

    @SerializedName("name") // 직원 이름 - ex.조동성
    public String name;

    @SerializedName("telephone") // 전화번호
    public String telephone;

    @SerializedName("email") // e-mail
    public String email;

    @SerializedName("officeId")
    public String officeId;
}
