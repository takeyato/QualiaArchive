package jp.co.quest.QualiaArchive.entity;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Employee {
    public Long id;
    public String name;
    public String emoji;
    public String motto;

    public LocalDateTime lastBreakStart;   // 休憩開始時刻
    public LocalDateTime lastBreakEnd;     // 休憩終了時刻

    public boolean isOnBreak() {
        if (lastBreakStart == null) return false;
        return lastBreakStart.plusMinutes(10).isAfter(LocalDateTime.now());
    }
}
