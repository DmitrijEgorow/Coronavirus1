package com.dmitrij.coronavirus1;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    private ContactsViewModel mViewModel; //todo implement
    private Loader<String> mLoader;
    private String locations = ""; //json string
    private TextView textView;
    private V location; //Singleton
    private CustomAdapter mAdapter;
    private List<String> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // todo
        //if(getLoaderManager().getLoader(0)!=null){
        mLoader = getLoaderManager().initLoader(0, null, this);
        //}
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // setRetainInstance(true);
        return inflater.inflate(R.layout.contacts_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = (TextView) view.findViewById(R.id.fragment_result);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CustomAdapter(items);
        recyclerView.setAdapter(mAdapter);



    }


    private void setLocation(String locations) {
        Log.d("gsononsl", this.locations);

        Gson gson = new Gson();
        Log.d("gsonons", locations);
        String str = new String(locations);
        str = str.replaceAll("\"timeline\":\\{\"", "\"timeline\":\\[\"");
        str = str.replaceAll("\\}\\},\"deaths\":", "}]},\"deaths\":");
        str = str.replaceAll("\\}\\},\"recovered\":", "}]},\"recovered\":");
        //str = str.replaceAll("\\[\"2020-\\d\\d-\\d\\dT00:00:00Z", "\\[\\{\"y2020");
        //str = str.replaceAll(",\"2020-\\d\\d-\\d\\dT00:00:00Z", "\\},\\{\"y2020");
        str = str.replaceAll("\\[\"2020-", "\\[\\{\"y21\":\"");
        str = str.replaceAll(",\"2020-", "\\},\\{\"y21\":\"");
        str = str.replaceAll("T00:00:00Z", "\",\"y22");
        //str = str.replaceAll("T00:00:00Z", "\\},\\{\"y2020");


        location = gson.fromJson(str, V.class);
        if (location != null) {
            Log.d("gsononc", location.toString());
        } else {
            str = " {\"location\":{\"coordinates\":{\"latitude\":\"-12.4634\",\"longitude\":\"130.8456\"},\"country\":\"Australia\",\"country_code\":\"AU\",\"id\":10,\"last_updated\":\"2020-03-24T12:26:21.166442Z\",\"latest\":{\"confirmed\":5,\"deaths\":0,\"recovered\":0},\"province\":\"Northern Territory\",\"timelines\":{\"confirmed\":{\"latest\":5,\"timeline\":{\"2020-01-22T00:00:00Z\":0,\"2020-01-23T00:00:00Z\":0,\"2020-01-24T00:00:00Z\":0,\"2020-01-25T00:00:00Z\":0,\"2020-01-26T00:00:00Z\":0,\"2020-01-27T00:00:00Z\":0,\"2020-01-28T00:00:00Z\":0,\"2020-01-29T00:00:00Z\":0,\"2020-01-30T00:00:00Z\":0,\"2020-01-31T00:00:00Z\":0,\"2020-02-01T00:00:00Z\":0,\"2020-02-02T00:00:00Z\":0,\"2020-02-03T00:00:00Z\":0,\"2020-02-04T00:00:00Z\":0,\"2020-02-05T00:00:00Z\":0,\"2020-02-06T00:00:00Z\":0,\"2020-02-07T00:00:00Z\":0,\"2020-02-08T00:00:00Z\":0,\"2020-02-09T00:00:00Z\":0,\"2020-02-10T00:00:00Z\":0,\"2020-02-11T00:00:00Z\":0,\"2020-02-12T00:00:00Z\":0,\"2020-02-13T00:00:00Z\":0,\"2020-02-14T00:00:00Z\":0,\"2020-02-15T00:00:00Z\":0,\"2020-02-16T00:00:00Z\":0,\"2020-02-17T00:00:00Z\":0,\"2020-02-18T00:00:00Z\":0,\"2020-02-19T00:00:00Z\":0,\"2020-02-20T00:00:00Z\":0,\"2020-02-21T00:00:00Z\":0,\"2020-02-22T00:00:00Z\":0,\"2020-02-23T00:00:00Z\":0,\"2020-02-24T00:00:00Z\":0,\"2020-02-25T00:00:00Z\":0,\"2020-02-26T00:00:00Z\":0,\"2020-02-27T00:00:00Z\":0,\"2020-02-28T00:00:00Z\":0,\"2020-02-29T00:00:00Z\":0,\"2020-03-01T00:00:00Z\":0,\"2020-03-02T00:00:00Z\":0,\"2020-03-03T00:00:00Z\":0,\"2020-03-04T00:00:00Z\":1,\"2020-03-05T00:00:00Z\":1,\"2020-03-06T00:00:00Z\":0,\"2020-03-07T00:00:00Z\":0,\"2020-03-08T00:00:00Z\":0,\"2020-03-09T00:00:00Z\":0,\"2020-03-10T00:00:00Z\":1,\"2020-03-11T00:00:00Z\":1,\"2020-03-12T00:00:00Z\":1,\"2020-03-13T00:00:00Z\":1,\"2020-03-14T00:00:00Z\":1,\"2020-03-15T00:00:00Z\":1,\"2020-03-16T00:00:00Z\":1,\"2020-03-17T00:00:00Z\":1,\"2020-03-18T00:00:00Z\":1,\"2020-03-19T00:00:00Z\":1,\"2020-03-20T00:00:00Z\":3,\"2020-03-21T00:00:00Z\":3,\"2020-03-22T00:00:00Z\":5,\"2020-03-23T00:00:00Z\":5}},\"deaths\":{\"latest\":0,\"timeline\":{\"2020-01-22T00:00:00Z\":0,\"2020-01-23T00:00:00Z\":0,\"2020-01-24T00:00:00Z\":0,\"2020-01-25T00:00:00Z\":0,\"2020-01-26T00:00:00Z\":0,\"2020-01-27T00:00:00Z\":0,\"2020-01-28T00:00:00Z\":0,\"2020-01-29T00:00:00Z\":0,\"2020-01-30T00:00:00Z\":0,\"2020-01-31T00:00:00Z\":0,\"2020-02-01T00:00:00Z\":0,\"2020-02-02T00:00:00Z\":0,\"2020-02-03T00:00:00Z\":0,\"2020-02-04T00:00:00Z\":0,\"2020-02-05T00:00:00Z\":0,\"2020-02-06T00:00:00Z\":0,\"2020-02-07T00:00:00Z\":0,\"2020-02-08T00:00:00Z\":0,\"2020-02-09T00:00:00Z\":0,\"2020-02-10T00:00:00Z\":0,\"2020-02-11T00:00:00Z\":0,\"2020-02-12T00:00:00Z\":0,\"2020-02-13T00:00:00Z\":0,\"2020-02-14T00:00:00Z\":0,\"2020-02-15T00:00:00Z\":0,\"2020-02-16T00:00:00Z\":0,\"2020-02-17T00:00:00Z\":0,\"2020-02-18T00:00:00Z\":0,\"2020-02-19T00:00:00Z\":0,\"2020-02-20T00:00:00Z\":0,\"2020-02-21T00:00:00Z\":0,\"2020-02-22T00:00:00Z\":0,\"2020-02-23T00:00:00Z\":0,\"2020-02-24T00:00:00Z\":0,\"2020-02-25T00:00:00Z\":0,\"2020-02-26T00:00:00Z\":0,\"2020-02-27T00:00:00Z\":0,\"2020-02-28T00:00:00Z\":0,\"2020-02-29T00:00:00Z\":0,\"2020-03-01T00:00:00Z\":0,\"2020-03-02T00:00:00Z\":0,\"2020-03-03T00:00:00Z\":0,\"2020-03-04T00:00:00Z\":0,\"2020-03-05T00:00:00Z\":0,\"2020-03-06T00:00:00Z\":0,\"2020-03-07T00:00:00Z\":0,\"2020-03-08T00:00:00Z\":0,\"2020-03-09T00:00:00Z\":0,\"2020-03-10T00:00:00Z\":0,\"2020-03-11T00:00:00Z\":0,\"2020-03-12T00:00:00Z\":0,\"2020-03-13T00:00:00Z\":0,\"2020-03-14T00:00:00Z\":0,\"2020-03-15T00:00:00Z\":0,\"2020-03-16T00:00:00Z\":0,\"2020-03-17T00:00:00Z\":0,\"2020-03-18T00:00:00Z\":0,\"2020-03-19T00:00:00Z\":0,\"2020-03-20T00:00:00Z\":0,\"2020-03-21T00:00:00Z\":0,\"2020-03-22T00:00:00Z\":0,\"2020-03-23T00:00:00Z\":0}},\"recovered\":{\"latest\":0,\"timeline\":{}}}}}";
            location = gson.fromJson(str, V.class);
        }
        for (Y21 e : location.getLocation().getTimelines().getConfirmed().getTimeline()) {
            items.add(e.y21 + " " + e.y22);
        }

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<String> mLoader = null;
        if (id == 0) {
            mLoader = new GetInfo(getActivity(), args);
            Log.d("gsonon", "onCreateLoader");
        }
        return mLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("gsonon", "onLoadFinished");

        switch (loader.getId()) {
            case 0:
                // Данные загружены
                setLocation(data);
                textView.setText(data);
                break;
            default:
                setLocation(" {\"location\":{\"coordinates\":{\"latitude\":\"-12.4634\",\"longitude\":\"130.8456\"},\"country\":\"Australia\",\"country_code\":\"AU\",\"id\":10,\"last_updated\":\"2020-03-24T12:26:21.166442Z\",\"latest\":{\"confirmed\":5,\"deaths\":0,\"recovered\":0},\"province\":\"Northern Territory\",\"timelines\":{\"confirmed\":{\"latest\":5,\"timeline\":{\"2020-01-22T00:00:00Z\":0,\"2020-01-23T00:00:00Z\":0,\"2020-01-24T00:00:00Z\":0,\"2020-01-25T00:00:00Z\":0,\"2020-01-26T00:00:00Z\":0,\"2020-01-27T00:00:00Z\":0,\"2020-01-28T00:00:00Z\":0,\"2020-01-29T00:00:00Z\":0,\"2020-01-30T00:00:00Z\":0,\"2020-01-31T00:00:00Z\":0,\"2020-02-01T00:00:00Z\":0,\"2020-02-02T00:00:00Z\":0,\"2020-02-03T00:00:00Z\":0,\"2020-02-04T00:00:00Z\":0,\"2020-02-05T00:00:00Z\":0,\"2020-02-06T00:00:00Z\":0,\"2020-02-07T00:00:00Z\":0,\"2020-02-08T00:00:00Z\":0,\"2020-02-09T00:00:00Z\":0,\"2020-02-10T00:00:00Z\":0,\"2020-02-11T00:00:00Z\":0,\"2020-02-12T00:00:00Z\":0,\"2020-02-13T00:00:00Z\":0,\"2020-02-14T00:00:00Z\":0,\"2020-02-15T00:00:00Z\":0,\"2020-02-16T00:00:00Z\":0,\"2020-02-17T00:00:00Z\":0,\"2020-02-18T00:00:00Z\":0,\"2020-02-19T00:00:00Z\":0,\"2020-02-20T00:00:00Z\":0,\"2020-02-21T00:00:00Z\":0,\"2020-02-22T00:00:00Z\":0,\"2020-02-23T00:00:00Z\":0,\"2020-02-24T00:00:00Z\":0,\"2020-02-25T00:00:00Z\":0,\"2020-02-26T00:00:00Z\":0,\"2020-02-27T00:00:00Z\":0,\"2020-02-28T00:00:00Z\":0,\"2020-02-29T00:00:00Z\":0,\"2020-03-01T00:00:00Z\":0,\"2020-03-02T00:00:00Z\":0,\"2020-03-03T00:00:00Z\":0,\"2020-03-04T00:00:00Z\":1,\"2020-03-05T00:00:00Z\":1,\"2020-03-06T00:00:00Z\":0,\"2020-03-07T00:00:00Z\":0,\"2020-03-08T00:00:00Z\":0,\"2020-03-09T00:00:00Z\":0,\"2020-03-10T00:00:00Z\":1,\"2020-03-11T00:00:00Z\":1,\"2020-03-12T00:00:00Z\":1,\"2020-03-13T00:00:00Z\":1,\"2020-03-14T00:00:00Z\":1,\"2020-03-15T00:00:00Z\":1,\"2020-03-16T00:00:00Z\":1,\"2020-03-17T00:00:00Z\":1,\"2020-03-18T00:00:00Z\":1,\"2020-03-19T00:00:00Z\":1,\"2020-03-20T00:00:00Z\":3,\"2020-03-21T00:00:00Z\":3,\"2020-03-22T00:00:00Z\":5,\"2020-03-23T00:00:00Z\":5}},\"deaths\":{\"latest\":0,\"timeline\":{\"2020-01-22T00:00:00Z\":0,\"2020-01-23T00:00:00Z\":0,\"2020-01-24T00:00:00Z\":0,\"2020-01-25T00:00:00Z\":0,\"2020-01-26T00:00:00Z\":0,\"2020-01-27T00:00:00Z\":0,\"2020-01-28T00:00:00Z\":0,\"2020-01-29T00:00:00Z\":0,\"2020-01-30T00:00:00Z\":0,\"2020-01-31T00:00:00Z\":0,\"2020-02-01T00:00:00Z\":0,\"2020-02-02T00:00:00Z\":0,\"2020-02-03T00:00:00Z\":0,\"2020-02-04T00:00:00Z\":0,\"2020-02-05T00:00:00Z\":0,\"2020-02-06T00:00:00Z\":0,\"2020-02-07T00:00:00Z\":0,\"2020-02-08T00:00:00Z\":0,\"2020-02-09T00:00:00Z\":0,\"2020-02-10T00:00:00Z\":0,\"2020-02-11T00:00:00Z\":0,\"2020-02-12T00:00:00Z\":0,\"2020-02-13T00:00:00Z\":0,\"2020-02-14T00:00:00Z\":0,\"2020-02-15T00:00:00Z\":0,\"2020-02-16T00:00:00Z\":0,\"2020-02-17T00:00:00Z\":0,\"2020-02-18T00:00:00Z\":0,\"2020-02-19T00:00:00Z\":0,\"2020-02-20T00:00:00Z\":0,\"2020-02-21T00:00:00Z\":0,\"2020-02-22T00:00:00Z\":0,\"2020-02-23T00:00:00Z\":0,\"2020-02-24T00:00:00Z\":0,\"2020-02-25T00:00:00Z\":0,\"2020-02-26T00:00:00Z\":0,\"2020-02-27T00:00:00Z\":0,\"2020-02-28T00:00:00Z\":0,\"2020-02-29T00:00:00Z\":0,\"2020-03-01T00:00:00Z\":0,\"2020-03-02T00:00:00Z\":0,\"2020-03-03T00:00:00Z\":0,\"2020-03-04T00:00:00Z\":0,\"2020-03-05T00:00:00Z\":0,\"2020-03-06T00:00:00Z\":0,\"2020-03-07T00:00:00Z\":0,\"2020-03-08T00:00:00Z\":0,\"2020-03-09T00:00:00Z\":0,\"2020-03-10T00:00:00Z\":0,\"2020-03-11T00:00:00Z\":0,\"2020-03-12T00:00:00Z\":0,\"2020-03-13T00:00:00Z\":0,\"2020-03-14T00:00:00Z\":0,\"2020-03-15T00:00:00Z\":0,\"2020-03-16T00:00:00Z\":0,\"2020-03-17T00:00:00Z\":0,\"2020-03-18T00:00:00Z\":0,\"2020-03-19T00:00:00Z\":0,\"2020-03-20T00:00:00Z\":0,\"2020-03-21T00:00:00Z\":0,\"2020-03-22T00:00:00Z\":0,\"2020-03-23T00:00:00Z\":0}},\"recovered\":{\"latest\":0,\"timeline\":{}}}}}");

        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    private class CustomHolder extends RecyclerView.ViewHolder{
        TextView title;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.row_text);
        }
        public void bindModel(String item){
            title.setText(item);
        }
    }
    private class CustomAdapter extends RecyclerView.Adapter<CustomHolder>{
        private List<String> mItems;

        public List<String> getmItems() {
            return mItems;
        }

        public CustomAdapter(List<String> mItems) {
            this.mItems = mItems;
        }

        @NonNull
        @Override
        public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("gsononviewtype", viewType+" ");
            return new CustomHolder(getLayoutInflater().inflate(R.layout.row, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
            holder.bindModel(mItems.get(position));
        }

        @Override
        public int getItemCount() {
            if (items == null) {
                return (0);
            }
            return (items.size());
        }
    }
}

class V {
    private Location location;

    @Override
    public String toString() {
        return location.toString();
    }

    public V(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}

class Confirmed {
    int latest;
    ArrayList<Y21> timeline;

    public Confirmed(int latest, ArrayList<Y21> timeline) {
        this.latest = latest;
        this.timeline = timeline;
    }

    public ArrayList<Y21> getTimeline() {
        return timeline;
    }
}

class Y21 {
    String y21;
    int y22;

    public Y21(int y22) {
        this.y22 = y22;
    }

    @NonNull
    @Override
    public String toString() {
        return y21 + ": " + y22 + "";
    }
}

class Timelines {
    Confirmed confirmed;


    public Timelines(Confirmed confirmed) {
        this.confirmed = confirmed;
    }

    public Confirmed getConfirmed() {
        return confirmed;
    }
}

class Coordinates {
    double latitude, longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

class Location {
    @SerializedName("coordinates")
    private Coordinates coord;
    private Timelines timelines;


    private String country;
    private String country_code;
    private int id;

    @Override
    public String toString() {
        return coord.latitude + " " + coord.longitude + " "
                + country + " " + country_code + " " + id + " " +
                timelines.confirmed.latest + "\n" +
                timelines.confirmed.timeline;
        //return timelines.toString() + coord.toString() + id + country;
    }

    public Location(Coordinates coord, Timelines timelines, String country, String country_code, int id) {
        this.coord = coord;
        this.timelines = timelines;
        this.country = country;
        this.country_code = country_code;
        this.id = id;
    }

    public Timelines getTimelines() {
        return timelines;
    }
}
