## SectionedMergeAdapter

This is a library based on `MergeAdapter` by CommonsWare. It allows you to work with sections and subheaders in your `ListView`s. With this library, you get the advantage of stitching together the rows and the header of the subsection in the subsection itself. For example, if there are no rows in a subsection of the `ListView`, the header of the subsection is also not shown.

### Example code

```java
ArrayAdapter<String> adapter1 =
        new ArrayAdapter<>(this, R.layout.item_list, android.R.id.text1,array.subList(0, 8));
ArrayAdapter<String> adapter2 =
        new ArrayAdapter<>(this, R.layout.item_list, android.R.id.text1,array.subList(8, 16));
ArrayAdapter<String> adapter3 =
        new ArrayAdapter<>(this, R.layout.item_list, android.R.id.text1,array.subList(16, 30));
View view1 = getLayoutInflater().inflate(R.layout.item_header, null, false);
TextView tv1 = (TextView) view1.findViewById(R.id.headerText);
tv1.setText("Header 1");
View view2 = getLayoutInflater().inflate(R.layout.item_header, null, false);
TextView tv2 = (TextView) view2.findViewById(R.id.headerText);
tv2.setText("Header 2");
View view3 = getLayoutInflater().inflate(R.layout.item_header, null, false);
TextView tv3 = (TextView) view3.findViewById(R.id.headerText);
tv3.setText("Header 3");
SectionedMergeAdapter adapter = new SectionedMergeAdapter();
adapter.addHeaderAdapter(new SectionedMergeAdapter.HeaderAdapter(view1, adapter1));
adapter.addHeaderAdapter(new SectionedMergeAdapter.HeaderAdapter(view2, adapter2));
adapter.addHeaderAdapter(new SectionedMergeAdapter.HeaderAdapter(view3, adapter3));
listView.setAdapter(adapter);
```

### Note
Since `HeaderAdapter` is extending from the `MergeAdapter`, you can use other `SectionedMergeAdapter` functionalities like `addHeaderAdapter()` with this library.

For a detailed list of things supported with `MergeAdapter` check out [CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge).

### Installation
Clone the repository and import the module in your project.

### Screenshot

![Screenshot](app/Screenshot_20160724-231927.png)
