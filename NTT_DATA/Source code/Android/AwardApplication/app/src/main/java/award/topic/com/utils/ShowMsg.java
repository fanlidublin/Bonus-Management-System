package award.topic.com.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by XUEYI on 2015/11/22.
 */
public class ShowMsg {
    public static void showMsg(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
