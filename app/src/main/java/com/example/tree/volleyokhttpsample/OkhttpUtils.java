package com.example.tree.volleyokhttpsample;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.MultipartBuilder;


import java.io.IOException;


/**
 * Created by tree on 10/15/2015.
 */
public class OkhttpUtils {

    public void get() {
//    http get
//    创建okhttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

        //    创建一个Request
        final Request request = new Request.Builder()
                .url("https://github.com/cnoldtree")
                .build();
//    new call
        Call call = mOkHttpClient.newCall(request);
//    请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String htmlString = response.body().string();
//                onResponse回调的参数是response，一般情况下，比如我们希望获得返回的字符串，
//                可以通过response.body().string()获取；如果希望获得返回的二进制字节数组，
//                则调用response.body().bytes()；如果你想拿到返回的inputStream，
//                则调用response.body().byteStream()

//                UI修改需加UI线程处理
//                final String res = response.body().string();
//                runOnUiThread(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        mTv.setText(res);
//                    }
//
//                });
            }
        });
    }

//    public void post(){
//        Request request = buildMultipartFormRequest(
//                url, new File[]{file}, new String[]{fileKey}, null);
//        FormEncodingBuilder builder = new FormEncodingBuilder();
//        builder.add("username","cnoldtree");
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(builder.build())
//                .build();
//        mOkHttpClient.newCall(request).enqueue(new Callback(){});
//    }

    public void uploadFile(){
//        File file = new File(Environment.getExternalStorageDirectory(), "balabala.mp4");
//
//        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//
//        RequestBody requestBody = new MultipartBuilder()
//                .type(MultipartBuilder.FORM)
//                .addPart(Headers.of(
//                                "Content-Disposition",
//                                "form-data; name=\"username\""),
//                        RequestBody.create(null, "cnoldtree"))
//                .addPart(Headers.of(
//                        "Content-Disposition",
//                        "form-data; name=\"mFile\";
//                        filename=\"wjd.mp4\""), fileBody)
//                .build();
//
//        Request request = new Request.Builder()
//                .url("http://192.168.1.103:8080/okHttpServer/fileUpload")
//                .post(requestBody)
//                .build();
//
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//
//            }
//        });
    }

}
