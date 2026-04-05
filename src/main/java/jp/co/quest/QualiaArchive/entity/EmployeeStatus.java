package jp.co.quest.QualiaArchive.entity;

import lombok.Data;

@Data
public class EmployeeStatus {
    public Long id;
    public String name;
    public String emoji;
    public String motto;
    public boolean onBreak;
    public long minutesSinceLastBreak; // 休憩を取っていない時間
    public long breakRemaining; // 休憩中なら残り時間（秒）
}
