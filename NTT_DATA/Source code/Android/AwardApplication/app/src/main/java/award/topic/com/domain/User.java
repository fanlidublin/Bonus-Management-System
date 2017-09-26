package award.topic.com.domain;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 * 底层对象不关联外键
 * 注意：修改属性时候需要维护Dto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class User {

    @Expose
    private int adminId;
    /**
     * 登录名称
     */
    @Expose
    private String userName;

    /**
     * 密码
     */
    @Expose
    private String password;

    @Expose
    private String userType;



}
