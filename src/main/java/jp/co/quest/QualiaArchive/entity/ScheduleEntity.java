package jp.co.quest.QualiaArchive.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Entity
@Table(name = "schedule")
@Data
public class ScheduleEntity {

    @Id
    private Long id;

    private String title;
    private LocalDateTime start_at;
    private LocalDateTime end_at;
    private String place;
    private String backgroundcolor;
}
