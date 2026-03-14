package jp.co.quest.QualiaArchive.bean;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
/**
 * 予定アイテムを表すクラス
 */
public class ScheduleItem {
    /** 予定のタイトル */
    private String title;
    /** 予定の開始日時 */
    private LocalDateTime start;
    /** 予定の終了日時 */
    private LocalDateTime end;
    /** 予定の場所 */
    private String place;
    /** 予定の背景色（例: "#FF0000"）*/
    private String baggroundColor;

    /** コンストラクタ */
    public ScheduleItem(String title, LocalDateTime start, LocalDateTime end, String place, String baggroundColor) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.place = place; 
        this.baggroundColor = baggroundColor;
    }

    // ★ 終日判定ロジック
    public boolean isAllDay() {
        return start.toLocalTime().equals(LocalTime.MIDNIGHT)
            && end.toLocalTime().equals(LocalTime.MIDNIGHT)
            && (end.toLocalDate().isEqual(start.toLocalDate().plusDays(1))
                || end.toLocalDate().isEqual(start.toLocalDate()));
    }

    /** 表示用の時間 */
    public String getDisplayTime() {
        if (isAllDay()) {
            return "終日";
        }
        return start.toLocalTime() + "〜" + end.toLocalTime();
    }
    
    /**
     * 背景色に応じて文字色を自動で決定するロジック
     * @return
     */
    public String getTextColor() {
        if (baggroundColor == null || !baggroundColor.startsWith("#") || baggroundColor.length() != 7) {
            return "#000000"; // デフォルト黒
        }

        // #RRGGBB を RGB に変換
        int r = Integer.parseInt(baggroundColor.substring(1, 3), 16);
        int g = Integer.parseInt(baggroundColor.substring(3, 5), 16);
        int b = Integer.parseInt(baggroundColor.substring(5, 7), 16);

        // 相対輝度（WCAG 準拠）
        double luminance = (0.299 * r + 0.587 * g + 0.114 * b);

        // 明るい背景 → 黒文字、暗い背景 → 白文字
        return luminance > 150 ? "#000000" : "#FFFFFF";
    }

}
