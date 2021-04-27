package network.cycan.core.enums;

import lombok.Getter;

/**
 * <p>
 * FileType 类型: 文件类型
 * </p>
 *
 * @author linjd
 * @since 2020/6/2 17:13
 */
@Getter
public enum FileType {
    Image(1, "图片"),
    audio(2, "音频"),
    video(3, "视频"),
    TEXT(4, " 文本") ;
    private Integer code;



    private String name;
    FileType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Integer getCode() {
        return code;
    }

}
