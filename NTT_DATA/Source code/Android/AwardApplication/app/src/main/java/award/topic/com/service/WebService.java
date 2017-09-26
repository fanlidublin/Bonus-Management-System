package award.topic.com.service;


import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import award.topic.com.utils.StreamToos;

public class WebService {

    public static void post(final String path, final String data, final HttpCallBack callback) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                String result = "";
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(path);
                    conn = (HttpURLConnection) url.openConnection();
                    //区别2请求方式变成了
                    conn.setRequestMethod("POST");//get一定大写
                    //区别3必须指定请求参数
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    //请求的类型表单数据
                    conn.setRequestProperty("Content-Length", data.length() + "");//数据的长度
                    //区别四： 记得设置把数据写给服务器
                    conn.setDoOutput(true);//设置向服务器写数据
                    conn.getOutputStream().write(data.getBytes());//把数据以流的方式写给服务器
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        String cookieskey = "Set-Cookie";
                        Map<String, List<String>> maps = conn.getHeaderFields();
                        List<String> coolist = maps.get(cookieskey);

                        Iterator<String> it = coolist.iterator();
                        StringBuffer sbu = new StringBuffer();
                        //sbu.append("eos_style_cookie=default; ");
                        while(it.hasNext()){
                            sbu.append(it.next());
                        }



                    //System.out.println(sbu.toString());
                        Log.d("12345677",sbu.toString());

                        //服务器端返回的InputStream把is里面的内容转换成一个文本显示出来
                        InputStream is = conn.getInputStream();
                        result = StreamToos.readStream(is);
                        if (callback != null) {
                            callback.onFinish(result,sbu.toString());
                        }
                    }
                } catch (Exception ex) {
                    if (callback != null) {
                        callback.onError(ex);
                    }
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
}

