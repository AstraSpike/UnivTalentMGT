// personnel-management-service/src/main/java/com/hmall/personnel/po/MeetingOperationLogsPO.java
package com.hmall.personnel.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@TableName("meeting_operation_logs")
@Table(name = "meeting_operation_logs")
public class MeetingOperationLogsPO {
    @Id
    private Long id;
    private Long userId;
    private String operationType;
    private Date operationTime;
}