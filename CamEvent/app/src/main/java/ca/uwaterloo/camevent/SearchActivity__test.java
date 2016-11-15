package ca.uwaterloo.camevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class SearchActivity__test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity__test);
        EventDBHandler eventDB=new EventDBHandler(this);
        String locationname="SLC";
        String date="2016-11-07";
        String title="Science";
        List<Eventinfo> eventinfos1=eventDB.getAllEventsbysearch(locationname,null,null);
        List<Eventinfo> eventinfos2=eventDB.getAllEventsbysearch(null,title,null);
        List<Eventinfo> eventinfos3=eventDB.getAllEventsbysearch(null,null,date);
        List<Eventinfo> eventinfos4=eventDB.getAllEventsbysearch(locationname,title,null);
        List<Eventinfo> eventinfos5=eventDB.getAllEventsbysearch(locationname,null,date);
        List<Eventinfo> eventinfos6=eventDB.getAllEventsbysearch(null,title,date);
        List<Eventinfo> eventinfos7=eventDB.getAllEventsbysearch(locationname,title,date);
        Log.d("Search Test", String.valueOf(eventinfos1.size()));
        Log.d("Search Test", String.valueOf(eventinfos2.size()));
        Log.d("Search Test", String.valueOf(eventinfos3.size()));
        Log.d("Search Test", String.valueOf(eventinfos4.size()));
        Log.d("Search Test", String.valueOf(eventinfos5.size()));
        Log.d("Search Test", String.valueOf(eventinfos6.size()));
        Log.d("Search Test", String.valueOf(eventinfos7.size()));
    }
}
