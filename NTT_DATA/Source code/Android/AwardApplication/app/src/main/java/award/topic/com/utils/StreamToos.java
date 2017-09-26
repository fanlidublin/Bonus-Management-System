package award.topic.com.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamToos {
    /**
     * 把一个流里面的内容转换成一个字符串
     * @param is
     * @return 流的字符串null解析失败
     *
     */
    public static String readStream(InputStream is){
        try {
            //		先把流的数据读到内存里面去
            ByteArrayOutputStream baos =new  ByteArrayOutputStream();
            //		定义出来一个小缓冲区了
            byte[] buffer=new byte[1024];
            int len= -1;
            //!=-1 代表的是没有读到流的末尾
            while((len =is.read(buffer))!=-1){
                //			写到内存的缓冲区里面
                baos.write(buffer, 0, len);
                //			流里面的内容都早baos里面

            }
            is.close();
            return new String(baos.toByteArray());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //	有异常就返回一个空字符串
            return "";
        }
    }

}

