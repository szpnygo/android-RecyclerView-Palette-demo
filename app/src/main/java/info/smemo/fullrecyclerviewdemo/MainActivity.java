package info.smemo.fullrecyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<ImageItem> imageItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //设置方向
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        final ImageItem item0=new ImageItem("test0",R.drawable.b0,ImageItem.LEFT);
        ImageItem item1=new ImageItem("test1",R.drawable.b1,ImageItem.RIGHT);
        ImageItem item2=new ImageItem("test2",R.drawable.b2,ImageItem.LEFT);
        ImageItem item3=new ImageItem("test3",R.drawable.b3,ImageItem.RIGHT);
        ImageItem item5=new ImageItem("test5",R.drawable.b5,ImageItem.LEFT);
        ImageItem item6=new ImageItem("test6",R.drawable.b6,ImageItem.RIGHT);
        ImageItem item7=new ImageItem("test7",R.drawable.b7,ImageItem.LEFT);
        ImageItem item8=new ImageItem("test8",R.drawable.b8,ImageItem.RIGHT);
//        @DrawableRes int字段必须为DrawableRes
//        ImageItem item10=new ImageItem("test9",11,ImageItem.LEFT);

        imageItemArrayList=new ArrayList<>();
        imageItemArrayList.add(item0);
        imageItemArrayList.add(item1);
        imageItemArrayList.add(item2);
        imageItemArrayList.add(item3);
        imageItemArrayList.add(item5);
        imageItemArrayList.add(item6);
        imageItemArrayList.add(item7);
        imageItemArrayList.add(item8);

        myAdapter=new MyAdapter(this,imageItemArrayList);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(Object object, int position) {
                ImageItem imageItem = (ImageItem) object;
                Toast.makeText(MainActivity.this, "Activity注册：" + position + ":" + imageItem.title, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageItem item8=new ImageItem("新增",R.drawable.b8,ImageItem.LEFT);
                imageItemArrayList.add(2, item8);
                myAdapter.add(2);
            }
        });


    }

}
