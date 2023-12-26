package leejaewoo.server.mongotest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Document(collection = "RED_RECORD")
@NoArgsConstructor
@AllArgsConstructor
public class RedRecord {

    @MongoId
    private final String redRecordId = UUID.randomUUID().toString();

    @Field(name = "RECORD_TITLE")
    private String recordTitle;

    @Field(name = "CREATION_START_DATE")
    private LocalDateTime creationStartDate;

    @Field(name = "CREATION_END_DATE")
    private LocalDateTime creationEndDate;

    @Field(name = "CREATOR_NAME")
    private String creatorName;

    @Field(name = "ELECTRONIC_CODE")
    private String electronicCode;

    @Field(name = "ITEM_TYPE_CODE")
    private String itemTypeCode;

    @Field(name = "RECORD_SHAPE_CODE")
    private String recordShapeCode;

    @Field(name = "CREATION_DEPT_ID")
    private String creationDeptId;

    @Field(name = "MANAGEMENT_DEPT_ID")
    private String managementDeptId;

    @Field(name = "OPEN_STATUS_CODE")
    private String openStatusCode;

    @Field(name = "ACCESS_CONTROL_CODE")
    private String accessControlCode;

    @Field(name = "RECORD_SCHEDULE")
    private String recordSchedule;
}
