// personnel-management-service/src/main/java/com/hmall/personnel/dto/MeetingOperationLogsDTO.java
package com.hmall.personnel.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MeetingOperationLogsDTO {
    private Long id;
    private Long userId;
    private String operationType;
    private Date operationTime;
}