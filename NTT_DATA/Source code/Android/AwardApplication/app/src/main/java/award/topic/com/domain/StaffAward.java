package award.topic.com.domain;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by XUEYI on 2016/8/12.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class StaffAward {
    @Expose
    private String staffId;
    @Expose
    private String staffName;
    @Expose
    private String staffAccount;
    @Expose
    private String staffTime;

}
