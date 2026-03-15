package jp.co.quest.QualiaArchive.bean;

import lombok.Data;

@Data
public class ScheduleSearchForm {
    private String keyword;
    private Integer page = 0;
    private Integer size = 10;
}
