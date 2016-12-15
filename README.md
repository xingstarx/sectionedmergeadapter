## MergeAdapter

This is a repo based on [MergeAdapter](https://github.com/commonsguy/cwac-merge).

### Chinese 
[中文](/README_CN.md)

 
### Feature
 Support 
- HeaderView , your Adapter need extends ListHeaderBaseAdapter 
- Support OnItemClick event
- Pinned listview with MergeAdapter 
- Support RecyclerView with MergeRecyclerAdapter

### How to use it

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency in your module build.gradle

```
dependencies {
    compile 'com.github.xingstarx:MergeAdapter:v0.1.0'
	}
```


### Example code

HeaderAdapter with headerview 

```java
HeaderAdapter adapter1 = new HeaderAdapter(this, new ArrayList<>(array.subList(0, 8)), R.layout.item_list, R.layout.item_header);
HeaderAdapter adapter2 = new HeaderAdapter(this, new ArrayList<>(array.subList(8, 16)), R.layout.item_list, R.layout.item_header);
HeaderAdapter adapter3 = new HeaderAdapter(this, new ArrayList<>(array.subList(16, 30)), R.layout.item_list, R.layout.item_header);
adapter1.setHeaderType(1);
adapter2.setHeaderType(2);
adapter3.setHeaderType(3);
final MergeAdapter adapter = new MergeAdapter();
adapter.addAdapter(adapter1);
adapter.addAdapter(adapter2);
adapter.addAdapter(adapter3);

listView.setAdapter(adapter);
```


OnItemClick event 

```java
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                adapter.onItemClick(adapterView, view, position, id);
            }
        });
```

PinnedListView

```
final MergeAdapter adapter = new PinnedMergeAdapter();

HeaderAdapter adapter1 = new HeaderAdapter(this, new ArrayList<>(array.subList(0, 13)), R.layout.item_list, R.layout.item_header, 1);
HeaderAdapter adapter2 = new HeaderAdapter(this, new ArrayList<>(array.subList(13, 29)), R.layout.item_list, R.layout.item_header, 2);
HeaderAdapter adapter3 = new HeaderAdapter(this, new ArrayList<>(array.subList(29, 50)), R.layout.item_list, R.layout.item_header, 3);
adapter.addAdapter(adapter1);
adapter.addAdapter(adapter2);
adapter.addAdapter(adapter3);

listView.setAdapter(adapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        adapter.onItemClick(adapterView, view, position, id);
    }
});
```


RecyclerView

```
final MyAdapter mAdapter = new MyAdapter(array);
        final MyAdapter mAdapter2 = new MyAdapter(new CopyOnWriteArrayList<>(array));


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        final MergeRecyclerAdapter mMergeRecyclerAdapter = new MergeRecyclerAdapter();
        mMergeRecyclerAdapter.addAdapter(mAdapter);
        mMergeRecyclerAdapter.addAdapter(mAdapter2);
        mRecyclerView.setAdapter(mMergeRecyclerAdapter);

```


### Note
MergeAdapter copy from [CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge)

HeaderAdapter: you need extends ListHeaderBaseAdapter to implements prepareViewForDisplay method , this contains headerview and contentView, if you don't know , you could reference examples.

if you want use pinned-section-listview, you could reference PinnedListViewActivity , it shows how to use PinnedSectionListView and PinnedMergeAdapter. I copied PinnedSectionListView.java and do some modifications,we use isItemPinned(int position) method rather than isItemViewTypePinned(viewType)

MergeRecyclerAdapter 


### Installation
copy ListHeaderBaseAdapter, ListBaseAdapter, and MergeAdapter, PinnedMergeAdapter in your project

### Screenshot

![Screenshot](art/mergeadapter_2016_08_28_001.png)

![Screenshot](art/merge_adaper_2016_09_06_001.png)

![Screenshot](art/device-2016-09-13-192233-mergeadapter.gif) &ensp; &ensp;  ![Screenshot](art/device-2016-09-13-192330-pinned-mergeadapter.gif) &ensp; &ensp; ![Screenshot](art/device-2016-09-13-192408-recycler-mergeadapter.gif)


### Thanks

[CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge)

[sectionedmergeadapter](https://github.com/abacritt/sectionedmergeadapter)

[pinned-section-listview](https://github.com/beworker/pinned-section-listview)


### Contribution

 read [CONTRIBUTING.md](/CONTRIBUTING.md)
