## MergeAdapter

This is a repo based on [MergeAdapter](https://github.com/commonsguy/cwac-merge).

### Chinese 
[中文](/README_CN.md)

 
### Feature
 Support 
- HeaderView , your Adapter need extends ListHeaderBaseAdapter 
- Support OnItemClick event
- Pinned listview with MergeAdapter 

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


### Note
MergeAdapter copy from [CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge)

HeaderAdapter: you need extends ListHeaderBaseAdapter to implements prepareViewForDisplay method , this contains headerview and contentView, if you don't know , you could reference examples.

if you want use pinned-section-listview, you could reference PinnedListViewActivity , it shows how to use PinnedSectionListView and PinnedMergeAdapter. I copied PinnedSectionListView.java and do some modifications,we use isItemPinned(int position) method rather than isItemViewTypePinned(viewType)

### Installation
copy ListHeaderBaseAdapter, ListBaseAdapter, and MergeAdapter, PinnedMergeAdapter in your project

### Screenshot

![Screenshot](art/mergeadapter_2016_08_28_001.png)

### Thanks

[CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge)

[sectionedmergeadapter](https://github.com/abacritt/sectionedmergeadapter)

[pinned-section-listview](https://github.com/beworker/pinned-section-listview)


### Contribution

 read [CONTRIBUTING.md](/CONTRIBUTING.md)
