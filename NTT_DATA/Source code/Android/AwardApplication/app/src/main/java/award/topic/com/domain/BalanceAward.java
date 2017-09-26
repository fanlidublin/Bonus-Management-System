package award.topic.com.domain;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by XUEYI on 2016/8/14.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class BalanceAward {
    @Expose
    private String awardName;
    @Expose
    private String awardBalance;

}
