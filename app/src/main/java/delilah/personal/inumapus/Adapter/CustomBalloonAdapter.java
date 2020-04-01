package delilah.personal.inumapus.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;

import java.util.ArrayList;

import delilah.personal.inumapus.R;

public class CustomBalloonAdapter implements CalloutBalloonAdapter {
    private final View customBalloon;
    private ArrayList<String> items;

    public CustomBalloonAdapter(Context context, ArrayList<String> items) {
        this.items = items;
        customBalloon = LayoutInflater.from(context).inflate(R.layout.balloon, null);
    }

    @Override
    public View getCalloutBalloon(MapPOIItem poiItem) {
        ((TextView) customBalloon.findViewById(R.id.balloonTitle)).setText(poiItem.getItemName());

        /*
        String office = "";

        for(String item : items) {
            office = office + item;
            Log.d("확인", office);
        }
         */

        return customBalloon;
    }

    @Override
    public View getPressedCalloutBalloon(MapPOIItem poiItem) {
        return null;
    }
}