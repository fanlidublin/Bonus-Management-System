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
public class ProjectAward {
    @Expose
    private int projectId;
    @Expose
    private String projectAward;
    @Expose
    private String project;
    @Expose
    private String projectTime;


}
