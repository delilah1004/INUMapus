package delilah.personal.inumapus.network;

import java.util.ArrayList;

import delilah.personal.inumapus.model.BuildingModel;
import delilah.personal.inumapus.model.EmployeeModel;
import delilah.personal.inumapus.model.FilterModel;
import delilah.personal.inumapus.model.OfficeModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkInterface {

    @GET("dbRouter/building")
    Call<ArrayList<BuildingModel>> getBuildingInfo();

    @GET("dbRouter/employee")
    Call<ArrayList<EmployeeModel>> getEmployeeInfo();

    @GET("dbRouter/filter")
    Call<ArrayList<FilterModel>> getFilterInfo();

    @GET("dbRouter/office")
    Call<ArrayList<OfficeModel>> getOfficeInfo();

    @GET("listRouter/officeListByBuilding/{id}")
    Call<ArrayList<OfficeModel>> getOfficeListByBuilding(@Path("id") int buildingNumber);

}
