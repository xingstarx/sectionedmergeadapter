## MergeAdapter

基于 CommonsWare的 [MergeAdapter](https://github.com/commonsguy/cwac-merge) 的扩展
 
### 新增特性
 - 支持HeaderView的HeaderAdapter,Adapter有内容时显示header,没有内容不显示header
 - 对全局OnItemClick事件的支持
 
 

### 实例代码

支持HeaderView的HeaderAdapter

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


支持OnItemClick事件

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


### 描述
MergeAdapter从[CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge) 复制过来的
HeaderAdapter 在原来的Adapter中添加了header,使用时只需要关注自己的Adapter就行,不需要自己处理header的显示问题,当然如果有特殊需求,可以自己在修改,支持OnItemClick



### Screenshot

![Screenshot](art/mergeadapter_2016_08_28_001.png)



### 感谢
[CWAC MergeAdapter](https://github.com/commonsguy/cwac-merge)
[sectionedmergeadapter](https://github.com/abacritt/sectionedmergeadapter)


### Contribution

 请看[CONTRIBUTING.md](/CONTRIBUTING.md)