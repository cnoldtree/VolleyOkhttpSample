package com.example.tree.volleyokhttpsample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.example.tree.volleyokhttpsample.OkHttpClientManager;
import com.example.tree.volleyokhttpsample.OkHttpClientManager.ResultCallback;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTv;
    private ImageView mImageView;

    public abstract class MyResultCallback<T> extends ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            setTitle("loading...");
        }

        @Override
        public void onAfter() {
            super.onAfter();
            setTitle("Sample-okHttp");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTv = (TextView) findViewById(R.id.id_textview);
        mImageView = (ImageView) findViewById(R.id.id_imageview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getUser(View view) {

        OkHttpClientManager.getAsyn("https://raw.githubusercontent.com/hongyangAndroid/okhttp-utils/master/user.gson",
                new MyResultCallback<User>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(User u) {
                        mTv.setText(u.toString());
                    }
                });
    }


    public void getUsers(View view) {
        OkHttpClientManager.getAsyn("https://raw.githubusercontent.com/hongyangAndroid/okhttp-utils/master/users.gson",
                new MyResultCallback<List<User>>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(List<User> us) {
                        Log.e("TAG", us.size() + "");
                        mTv.setText(us.get(1).toString());
                    }
                });


    }

    public void getSimpleString(View view) {
        OkHttpClientManager.getAsyn("https://raw.githubusercontent.com/hongyangAndroid/okhttp-utils/master/user.gson",
                new MyResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String u) {
                        mTv.setText(u);
                    }
                });
    }

    public void getHtml(View view) {
        //https://192.168.56.1:8443/
        //https://kyfw.12306.cn/otn/
        //https://192.168.187.1:8443/
        OkHttpClientManager.getAsyn("http://www.csdn.net/", new MyResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e("TAG", "onError" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(String u) {
                Log.e("TAG", "onResponse" + MainActivity.this);
                mTv.setText(u);
            }
        }, this);
    }

    public void getHttpsHtml(View view) {
        OkHttpClientManager.getAsyn("https://kyfw.12306.cn/otn/", new MyResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String u) {
                mTv.setText(u);
            }
        }, this);


    }

    public void getImage(View view) {
        mTv.setText("");
        OkHttpClientManager.getDisplayImageDelegate().displayImage(mImageView, "http://images.csdn.net/20150817/1.jpg", this);
    }

    public void uploadFile(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "test1.txt");

        if (!file.exists()) {
            Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClientManager.getUploadDelegate()
                .postAsyn("http://192.168.1.103:8080/okHttpServer/fileUpload",//
                        "mFile",//
                        file,//
                        new OkHttpClientManager.Param[]{
                                new OkHttpClientManager.Param("username", "zhy"),
                                new OkHttpClientManager.Param("password", "123")},//
                        new MyResultCallback<String>() {
                            @Override
                            public void onError(Request request, Exception e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(String filePath) {
                                Log.e("TAG", filePath);
                            }
                        }, this
                );
    }


    public void downloadFile(View view) {
        OkHttpClientManager.getDownloadDelegate().downloadAsyn("https://github.com/hongyangAndroid/okhttp-utils/blob/master/gson-2.2.1.jar?raw=true",
                Environment.getExternalStorageDirectory().getAbsolutePath(),
                new MyResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response + "下载成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        mTv.setText("success");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpClientManager.cancelTag(this);
    }
}
