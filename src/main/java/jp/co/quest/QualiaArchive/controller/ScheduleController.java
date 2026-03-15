package jp.co.quest.QualiaArchive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.quest.QualiaArchive.bean.ScheduleSearchForm;
import jp.co.quest.QualiaArchive.entity.ScheduleEntity;
import jp.co.quest.QualiaArchive.service.ScheduleService;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedule")
    public String list(@ModelAttribute("form") ScheduleSearchForm form, Model model) {

        Page<ScheduleEntity> page = scheduleService.search(
                form.getKeyword(),
                form.getPage(),
                form.getSize()
        );

        model.addAttribute("page", page);
        model.addAttribute("form", form);

        return "schedule/list";
    }
}
