package jp.co.quest.QualiaArchive.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.quest.QualiaArchive.entity.EmployeeStatus;
import jp.co.quest.QualiaArchive.service.EmployeeService;

@Controller
public class StatusController {

    private final EmployeeService service;

    public StatusController(EmployeeService service) {
        this.service = service;
    }

    /**
     * ステータス画面
     */
    @GetMapping("/status")
    public String index() {
        return "status";
    }

    /**
     * ステータスAPI
     */
    @ResponseBody
    @GetMapping("/api/status")
    public List<EmployeeStatus> getStatus() {
        return service.getAllStatuses();
    }

    /**
     * 休憩開始API
     */
    @PostMapping("/api/break/start/{id}")
    @ResponseBody
    public void startBreak(@PathVariable Long id) {
        service.startBreak(id);
    }

    /**
     * 休憩終了API
     */
    @PostMapping("/api/break/end/{id}")
    @ResponseBody
    public void endBreak(@PathVariable Long id) {
        service.endBreak(id);
    }

    /**
     * プロフィール編集API
     */
    @PostMapping("/api/profile/update/{id}")
    @ResponseBody
    public void updateProfile(
            @PathVariable Long id,
            @RequestParam(required = false) String emoji,
            @RequestParam(required = false) String motto) {

        service.updateProfile(id, emoji, motto);
    }
}
