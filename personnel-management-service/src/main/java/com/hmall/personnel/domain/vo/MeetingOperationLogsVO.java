// personnel-management-service/src/main/java/com/hmall/personnel/vo/MeetingOperationLogsVO.java
package com.hmall.personnel.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会议操作日志视图对象")
public class MeetingOperationLogsVO {
    @ApiModelProperty("日志ID")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("操作类型")
    private String operationType;

    @ApiModelProperty("操作时间")
    private String operationTime;
}