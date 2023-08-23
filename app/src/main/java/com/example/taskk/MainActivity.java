package com.example.taskk;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QuantityKistener {

    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> listDataGroup;
    private HashMap<String, List<String>> listDataChild;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
//    private ListView list;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    TagAdapter adapterr;
    LinearLayoutManager HorizontalLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initListeners();
        initObjects();
        initListData();
    }


    private void initViews() {
        expandableListView = findViewById(R.id.expandableListView);
        arrayList = new ArrayList<String>();
        recyclerView = (RecyclerView)findViewById(R.id.dises_tad);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
    }

    private void initListeners() {
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

//                Toast.makeText(getApplicationContext(), listDataGroup.get(groupPosition)
//                                        + " : "
//                                        + listDataChild.get(
//                                        listDataGroup.get(groupPosition)).get(
//                                        childPosition), Toast.LENGTH_SHORT)
//                        .show();



                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataGroup.get(groupPosition) + " " + getString(R.string.text_collapsed),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataGroup.get(groupPosition) + " " + getString(R.string.text_collapsed),
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initObjects() {
        listDataGroup = new ArrayList<>();
        listDataChild = new HashMap<>();
        expandableListViewAdapter = new ExpandableListViewAdapter(this, listDataGroup, listDataChild,this);
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    private void initListData() {
        listDataGroup.add(getString(R.string.text_shakti));
        listDataGroup.add(getString(R.string.text_end));
        listDataGroup.add(getString(R.string.text_gen));
        listDataGroup.add(getString(R.string.text_skin));
        listDataGroup.add(getString(R.string.text_infe));
        listDataGroup.add(getString(R.string.text_res));

        String[] array;
        List<String> shaktiList = new ArrayList<>();
        array = getResources().getStringArray(R.array.text_shakti);
        for (String item : array) {
            shaktiList.add(item);
        }

        List<String> endList = new ArrayList<>();
        array = getResources().getStringArray(R.array.text_end);
        for (String item : array) {
            endList.add(item);
        }

        List<String> genList = new ArrayList<>();
        array = getResources().getStringArray(R.array.text_gen);
        for (String item : array) {
            genList.add(item);
        }

        List<String> skinList = new ArrayList<>();
        array = getResources().getStringArray(R.array.text_skin);
        for (String item : array) {
            skinList.add(item);
        }

        List<String> infeList = new ArrayList<>();
        array = getResources().getStringArray(R.array.text_infe);
        for (String item : array) {
            infeList.add(item);
        }

        List<String> respList = new ArrayList<>();
        array = getResources().getStringArray(R.array.text_res);
        for (String item : array) {
            respList.add(item);
        }

        listDataChild.put(listDataGroup.get(0), shaktiList);
        listDataChild.put(listDataGroup.get(1), endList);
        listDataChild.put(listDataGroup.get(2), genList);
        listDataChild.put(listDataGroup.get(3), skinList);
        listDataChild.put(listDataGroup.get(4), infeList);
        listDataChild.put(listDataGroup.get(5), respList);

        expandableListViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onQuantityChange(ArrayList<String> arrayList) {
        Toast.makeText(getApplicationContext(),arrayList.toString(),Toast.LENGTH_SHORT).show();



        // Set Horizontal Layout Manager
        // for Recycler view
        HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        adapterr = new TagAdapter(arrayList);

        // Set adapter on recycler view
        recyclerView.setAdapter(adapterr);
    }
}