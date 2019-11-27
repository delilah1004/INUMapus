package delilah.personal.inumapus.Info;

public class BuildingListInfo {
    public String buildingTitle;
    public int builingNumber;

    public BuildingListInfo(String title, int number){
        this.buildingTitle = title;
        this.builingNumber = number;
    }

    public String getBuildingTitle() {
        return buildingTitle;
    }

    public int getBuilingNumber() {
        return builingNumber;
    }
}
