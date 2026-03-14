package jp.co.quest.QualiaArchive.bean;

import lombok.Data;

@Data
public class ScheduleItem {
    public ScheduleItem(String title, String datetime, String place) {
        this.title = title;
        this.datetime = datetime;
        this.place = place;
    }
	private String title;
    private String datetime;
    private String place;
}
