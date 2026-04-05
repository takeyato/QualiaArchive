$(function () {
  const audio = $('#key-sound')[0];

  // 画面クリック
  $('.key').on('mousedown', function () {
    audio.currentTime = 0;
    audio.play();
  });

  // 実際のキーボード入力
  $(document).on('keydown', function (e) {
    const key = e.key.toUpperCase(); // 'a' → 'A'
    const $btn = $('.key[data-key="' + key + '"]');
    if ($btn.length) {
      audio.currentTime = 0;
      audio.play();
      // 見た目のフィードバック
      $btn.addClass('active');
      setTimeout(() => $btn.removeClass('active'), 100);
    }
  });
});
