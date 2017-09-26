package award.topic.com.service;


import award.topic.com.domain.User;
import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;


/**
 * Created by xueyi on 2015/12/27.
 */
public interface HttpService {


    @POST("/NTT/login")
    Call<User> login(@Query("userName") String userName, @Query("password") String password, @Query("userType") String userType);


}
