## MergeAdapter

This is a repo based on [MergeAdapter](https://github.com/commonsguy/cwac-merge).

### Chinese 
[中文](/README_CN.md)

 
### Feature
 Support 
- It allows you to work with sections and subheaders in your `ListView`s. With this library, you get the advantage of stitching together the rows and the header of the subsection in the subsection itself. For example, if there are no rows in a subsection of the `ListView`, the header of the subsection is also not shown.
- Support OnItemClick event


### Example code

HeaderAdapter with headerview 

```java
MyAdapter adapter1 = new MyAdapter(this, array.subList(0, 8));
MyAdapter adapter2 = new MyAdapter(this, array.subList(8, 16));
MyAdapter adapter3 = new MyAdapter(this, array.subList(16, 30));
View view1 = getLayoutInflater().inflate(R.layout.item_header, null, false);
TextView tv1 = (TextView) view1.findViewById(R.id.headerText);
tv1.setText("Header 1");
View view2 = getLayoutInflater().inflate(R.layout.item_header, null, false);
TextView tv2 = (TextView) view2.findViewById(R.id.headerText);
tv2.setText("Header 2");
View view3 = getLayoutInflater().inflate(R.layout.item_header, null, false);
TextView tv3 = (TextView) view3.findViewById(R.id.headerText);
tv3.setText("Header 3");
final MergeAdapter adapter = new MergeAdapter();
adapter.addAdapter(new HeaderAdapter(view1, adapter1));
adapter.addAdapter(new HeaderAdapter(view2, adapter2));
adapter.addAdapter(new HeaderAdapter(view3, adapter3));

listView.setAdapter(adapter);
```


OnItemClick event 

```java
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (ListAdapter piece : adapter.getPieces()) {
                    int size = piece.getCount();
                    if (position < size) {
                        HeaderAdapter headerAdapter = (HeaderAdapter) piece;
                        headerAdapter.onItemClick(adapterView, view, position, id);
                        break;
                    }
                    position -= size;
                }
            }
        });
```


### Note
MergeAdapter copy from [CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge)
HeaderAdapter support header, onItemClick event , you only focus on your business adapter

### Installation
copy HeaderAdapter, and MergeAdapter in your project

### Screenshot

![Screenshot](art/mergeadapter_2016_08_28_001.png)

### Thanks
[CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge)
[sectionedmergeadapter](https://github.com/abacritt/sectionedmergeadapter)


### Contribution

 read [CONTRIBUTING.md](/CONTRIBUTING.md)