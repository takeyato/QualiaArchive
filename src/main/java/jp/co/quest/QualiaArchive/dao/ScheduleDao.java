package jp.co.quest.QualiaArchive.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.quest.QualiaArchive.entity.ScheduleEntity;

@Dao
@ConfigAutowireable
public interface ScheduleDao {

    @Select
    long count(String keyword);

    @Select
    List<ScheduleEntity> findPage(String keyword, int offset, int limit);
}