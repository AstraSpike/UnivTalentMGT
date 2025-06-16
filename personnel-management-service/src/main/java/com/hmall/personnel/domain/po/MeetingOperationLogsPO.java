// personnel-management-service/src/main/java/com/hmall/personnel/po/MeetingOperationLogsPO.java
package com.hmall.personnel.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("meeting_operation_logs")
public class MeetingOperationLogsPO {
    private Long id;
    private Long userId;
    private String operationType;
    private Date operationTime;
}