function loadStatus() {
    $.getJSON("/api/status", function (data) {

        data.sort((a, b) => b.minutesSinceLastBreak - a.minutesSinceLastBreak);

        $("#employee-list").empty();
        $("#break-list").empty();

        data.forEach(emp => {

            let level = Math.min(Math.floor(emp.minutesSinceLastBreak / 60), 8);

            let colors = [
                "#4ade80", "#a3e635", "#facc15", "#fbbf24",
                "#f59e0b", "#f97316", "#ef4444", "#dc2626", "#b91c1c"
            ];
            let color = colors[level];

            let hours = Math.floor(emp.minutesSinceLastBreak / 60);
            let mins = emp.minutesSinceLastBreak % 60;
            let timeText = `${hours}時間 ${mins}分`;

            let card = `
                <div class="employee-card mb-3" style="background:${color};">
                    <div class="d-flex align-items-center mb-2">
                        <div class="emoji-icon">${emp.emoji}</div>
                        <h5 class="card-title mb-0">${emp.name}</h5>
                    </div>
                    <p class="card-text">${emp.motto}</p>
                    <small>休憩未取得: ${timeText}</small>
                </div>
            `;

            let breakCard = `
                <div class="break-card mb-3">
                    <div class="d-flex align-items-center mb-2">
                        <div class="emoji-icon">${emp.emoji}</div>
                        <h5 class="card-title mb-0">${emp.name}</h5>
                    </div>
                    <p class="card-text">${emp.motto}</p>
                    <small>残り ${emp.breakRemaining} 秒</small>
                </div>
            `;

            if (emp.onBreak) {
                $("#break-list").append(breakCard);
            } else {
                $("#employee-list").append(card);
            }
        });
    });
}

/* 紙吹雪 */
function launchConfetti() {
    const container = document.querySelector(".confetti-container");
    container.innerHTML = "";
    container.style.opacity = 1;

    for (let i = 0; i < 25; i++) {
        const c = document.createElement("div");
        c.classList.add("confetti");
        c.style.left = Math.random() * 100 + "%";
        c.style.background = ["#ef4444", "#3b82f6", "#facc15", "#22c55e"][Math.floor(Math.random()*4)];
        c.style.animationDelay = (Math.random() * 0.3) + "s";
        container.appendChild(c);
    }

    setTimeout(() => container.style.opacity = 0, 1500);
}

/* 休憩開始 */
$("#breakButton").on("click", function () {
    $.post("/api/break/start/1", function () {

        let modalEl = document.getElementById("breakStartModal");
        let modal = new bootstrap.Modal(modalEl);
        modal.show();

        $(".ticket-left, .ticket-right").css({ "animation": "none", "opacity": "0" });
        $(".cut-line").css("animation", "none");

        setTimeout(() => {

            $(".ticket-left").css("animation", "ticketAppear 0.4s ease-out forwards");
            $(".ticket-right").css("animation", "ticketAppear 0.4s ease-out forwards");

            $(".cut-line").css("animation", "cutMove 1.2s ease-in forwards");

            setTimeout(() => {
                $(".ticket-left").css("animation", "ticketSplitTop 0.6s ease-out forwards");
                $(".ticket-right").css("animation", "ticketSplitBottom 0.6s ease-out forwards");

                launchConfetti();

            }, 1200);

            setTimeout(() => {
                modal.hide();
            }, 1800);

        }, 50);

        loadStatus();
    });
});

/* 休憩終了 */
$("#breakEndButton").on("click", function () {
    $.post("/api/break/end/1", function () {
        loadStatus();
    });
});

/* プロフィール保存 */
$("#saveProfile").on("click", function () {
    const emoji = $("#editEmoji").val();
    const motto = $("#editMotto").val();

    $.post("/api/profile/update/1", { emoji, motto }, function () {
        loadStatus();

        const modal = bootstrap.Modal.getInstance(document.getElementById("editModal"));
        modal.hide();
    });
});

setInterval(loadStatus, 5000);
loadStatus();
