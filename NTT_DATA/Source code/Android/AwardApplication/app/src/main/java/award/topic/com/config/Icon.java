package award.topic.com.config;


import lombok.Builder;
import lombok.Data;

/**
 * Created by XUEYI on 2015/11/30.
 * iId：GridView 图片
 * iName 名字
 */
@Data
@Builder
public class Icon {
    private Integer id;
    private String name;
}
