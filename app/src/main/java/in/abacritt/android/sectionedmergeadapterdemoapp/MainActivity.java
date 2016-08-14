package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.abacritt.android.library.SectionedMergeAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(android.R.id.list);

        List<String> array = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            array.add("Row " + i);
        }

        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<>(this, R.layout.item_list, android.R.id.text1,
                        array.subList(0, 8));
        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<>(this, R.layout.item_list, android.R.id.text1,
                        array.subList(8, 16));
        ArrayAdapter<String> adapter3 =
                new ArrayAdapter<>(this, R.layout.item_list, android.R.id.text1,
                        array.subList(16, 24));

        View rootView1 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv1 = (TextView) rootView1.findViewById(R.id.headerText);
        tv1.setText("Header 1");

        View rootView2 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv2 = (TextView) rootView2.findViewById(R.id.headerText);
        tv2.setText("Header 2");

        View rootView3 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv3 = (TextView) rootView3.findViewById(R.id.headerText);
        tv3.setText("Header 3");

        SectionedMergeAdapter adapter = new SectionedMergeAdapter();
        adapter.addSection(new SectionedMergeAdapter.Section(rootView1, adapter1));
        adapter.addSection(new SectionedMergeAdapter.Section(rootView2, adapter2));
        adapter.addSection(new SectionedMergeAdapter.Section(rootView3, adapter3));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
