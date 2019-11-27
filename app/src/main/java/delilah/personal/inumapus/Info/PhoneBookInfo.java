package delilah.personal.inumapus.Info;

public class PhoneBookInfo {
    public String detailOrgan, position, name, phoneNumber;

    public PhoneBookInfo(String organ, String position, String name, String number) {
        this.detailOrgan = organ;
        this.position = position;
        this.name = name;
        this.phoneNumber = number;
    }

    public String getDetailOrgan(){
        return detailOrgan;
    }

    public String getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

}
