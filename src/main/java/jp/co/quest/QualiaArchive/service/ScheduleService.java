package jp.co.quest.QualiaArchive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jp.co.quest.QualiaArchive.dao.ScheduleDao;
import jp.co.quest.QualiaArchive.entity.ScheduleEntity;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao dao;

    public Page<ScheduleEntity> search(String keyword, int page, int size) {

        long total = dao.count(keyword);

        int offset = page * size;

        List<ScheduleEntity> list = dao.findPage(keyword, offset, size);

        return new PageImpl<>(list, PageRequest.of(page, size), total);
    }
}
