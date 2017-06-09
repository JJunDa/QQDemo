package com.example.administrator.mybombtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button mButton;
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一：默认初始化
        Bmob.initialize(this, "e0921f45c32e7a102b72e6ec8f98e5ed");
        inits();

    }

    private void inits() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyView);
        mButton = (Button) findViewById(R.id.btn);
        mTextView = (TextView) findViewById(R.id.resultView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              addOne();
//                queryone();
                querys();
//                updateone();
//                deleteone();
            }
        });
    }

    private void querys() {
        BmobQuery<Person> query = new BmobQuery<Person>();
        query.addWhereEqualTo("address","38_730");
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this,"查询成功  "+list.size(),Toast.LENGTH_LONG).show();

                    for(Person person: list){
                        person.getName();
                        person.getAge();
                      /*  Person p2 = new Person();
                        p2.setName(person.getName());
                        p2.setAge(person.getAge());
                        p2.setAddress(person.getAddress());
                       mTextView.setText(p2.getName()+" "+p2.getAge());*/
                    }

                }else{
                    Toast.makeText(MainActivity.this,"查询失败  "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void deleteone() {
        final Person p1 = new Person();
        p1.setObjectId("bf1726eb5a");
        p1.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this,"删除成功  "+ p1.getUpdatedAt(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"删除失败  "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void updateone() {
        final Person p = new Person();
        p.setName("李白");
        p.update("bf1726eb5a", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this,"修改成功  "+ p.getUpdatedAt(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"修改失败  "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void queryone() {
        BmobQuery<Person> bmobquery = new BmobQuery<Person>();
        bmobquery.getObject("bf1726eb5a", new QueryListener<Person>() {
            @Override
            public void done(Person person, BmobException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this,"查询成功  "+person.getName().toString(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"查询失败  "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void addOne() {
        Person p = new Person();
        p.setName("韩信");
        p.setAge(20);
        p.setAddress("38_730");
        p.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this,"添加成功  "+s,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"添加失败  "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
