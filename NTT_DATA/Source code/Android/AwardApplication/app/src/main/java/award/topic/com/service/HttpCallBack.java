package award.topic.com.service;

public interface HttpCallBack {
    void onFinish(String res, String data);
    void onError(Exception ex);
}
