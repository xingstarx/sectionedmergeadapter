package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by xiongxingxing on 16/8/13.
 */

public class MergeAdapterDemo extends ListActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String[] items=
            { "lorem", "ipsum", "dolor", "sit", "amet", "consectetuer",
                    "adipiscing", "elit", "morbi", "vel", "ligula", "vitae",
                    "arcu", "aliquet", "mollis", "etiam", "vel", "erat",
                    "placerat", "ante", "porttitor", "sodales", "pellentesque",
                    "augue", "purus" };
    private MergeAdapter adapter=null;
    private ArrayAdapter<String> arrayAdapter=null;
    private Button btn=null;
    private ArrayAdapter<String> secordArrayAdapter=null;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        adapter=new MergeAdapter();
        arrayAdapter=buildFirstList();
        adapter.addAdapter(arrayAdapter);
        btn=buildButton();
        adapter.addView(btn, true);
        secordArrayAdapter = (ArrayAdapter<String>) buildSecondList();
        adapter.addAdapter(secordArrayAdapter);
        adapter.addView(buildLabel());
        adapter.addAdapter(buildSecondList());

        setListAdapter(adapter);

        getListView().postDelayed(new Runnable() {
            @Override
            public void run() {
                arrayAdapter.add("Xingxing Android Developer");
                getListView().removeCallbacks(this);
                getListView().postDelayed(this, 500);
            }
        }, 500);

        getListView().postDelayed(new Runnable() {
            @Override
            public void run() {
                secordArrayAdapter.add("Android Developer");
                getListView().removeCallbacks(this);
                getListView().postDelayed(this, 2000);
            }
        }, 2000);



        MergeAdapter spinnerAdapter=new MergeAdapter();

        spinnerAdapter.addAdapter(buildFirstSpinnerList());
        spinnerAdapter.addAdapter(buildSecondSpinnerList());

        ((Spinner)findViewById(R.id.spinner)).setAdapter(spinnerAdapter);

        CheckBox cb=(CheckBox)findViewById(R.id.toggleAdapter);

        cb.setOnCheckedChangeListener(this);
        cb=(CheckBox)findViewById(R.id.toggleButton);
        cb.setOnCheckedChangeListener(this);
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position,
                                long id) {
        Log.d("MergeAdapterDemo", String.valueOf(position));
    }

    private ArrayAdapter<String> buildFirstList() {
        return(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(
                        Arrays
                                .asList(items))));
    }

    private Button buildButton() {
        Button result=new Button(this);

        result.setText("Add Capitalized Words");
        result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (String item : items) {
                    arrayAdapter.add(item.toUpperCase());
                }
            }
        });

        return(result);
    }

    private View buildLabel() {
        TextView result=new TextView(this);

        result.setText("Hello, world!");

        return(result);
    }

    private ListAdapter buildSecondList() {
        ArrayList<String> list=new ArrayList<String>(Arrays.asList(items));

        Collections.shuffle(list);

        return(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                list));
    }

    private ArrayAdapter<String> buildFirstSpinnerList() {
        ArrayAdapter<String> result=
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_item,
                        new ArrayList<String>(
                                Arrays
                                        .asList(items)));

        result
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return(result);
    }

    private ListAdapter buildSecondSpinnerList() {
        ArrayList<String> list=new ArrayList<String>(Arrays.asList(items));

        Collections.shuffle(list);

        ArrayAdapter<String> result=
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        list);

        result
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return(result);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView,
                                 boolean isChecked) {
        if (buttonView.getId()==R.id.toggleAdapter) {
            adapter.setActive(arrayAdapter, isChecked);
        }
        else {
            adapter.setActive(btn, isChecked);
        }
    }
}