package delilah.personal.inumapus.Info;

public class BuildingListInfo {
    public String buildingTitle;
    public String buildingNumber;

    public BuildingListInfo(String title, String number){
        this.buildingTitle = title;
        this.buildingNumber = number;
    }

    public String getBuildingTitle() {
        return buildingTitle;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }
}
