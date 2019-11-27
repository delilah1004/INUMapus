package delilah.personal.inumapus.network;

import android.app.Application;

import delilah.personal.inumapus.StaticData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController extends Application {
    public static NetworkController networkController;
    public String mainURL = StaticData.mainURL;
    public NetworkInterface networkInterface = null;

    public static NetworkController getInstance(){
        return networkController;
    }

    public void retrofitBuild() {
        this.networkInterface = (NetworkInterface) new Retrofit.Builder()
                .baseUrl(mainURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkInterface.class);
    }

    public NetworkInterface getNetworkInterface(){
        return this.networkInterface;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        networkController = this;
        retrofitBuild();
    }
}