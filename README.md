# MutantSquidMod (Fabric 1.20.1)

## Установка
1. JDK 17.
2. Соберите через `gradle build` (Gradle 8.8) или через GitHub Actions.
3. Готовый jar: `build/libs/mutantsquid-1.0.0.jar`.
4. Положите файл `ritual_song.ogg` в `src/main/resources/assets/mutantsquid/sounds/ritual_song.ogg`.

## Механика
- Бросьте `minecraft:nautilus_shell` на землю — начнётся ритуал (5 минут).
- `/skipritual` — перемотка на последние 3 секунды.
- Приведите 4 мобов (не игроков) к 4 щупальцам — они их поглотят.
- Появится Гигантский Спрут-Мутант, который ходит на щупальцах и охотится на игрока.
