package jp.co.quest.QualiaArchive.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    private final Map<Long, Employee> employees = new HashMap<>();

    public EmployeeRepository() {
        // ダミーデータ
        add(1L, "佐藤", "🐱", "継続は力なり");
        add(2L, "田中", "🦊", "やらない後悔よりやる後悔");
        add(3L, "鈴木", "🐼", "心静かにして道遠し");
        add(4L, "山本", "🐧", "一日一歩");
        add(5L, "中村", "🐯", "挑戦なくして成長なし");
    }

    private void add(Long id, String name, String emoji, String motto) {
        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setEmoji(emoji);
        e.setMotto(motto);
        e.setLastBreakEnd(LocalDateTime.now().minusMinutes(new Random().nextInt(120)));
        employees.put(id, e);
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    public Employee findById(Long id) {
        return employees.get(id);
    }

    public void save(Employee e) {
        employees.put(e.getId(), e);
    }
}
