import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    private static BufferedReader bufferedReader;
    private static FantasticCharacter player = null;
    private static Battle battle = null;


    public static void main(String[] args) {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Дайте имя герою: ");
        try {
            command(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new SuperHero(string, 100, 0, 20, 5, 20);
            System.out.println(String.format("Спасти мир от драконов монстров вызвался %s! Пожелаем ему удачи в неравном бою",
                    player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Друид опять где-то пропадает!!!");
                command(bufferedReader.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(bufferedReader.readLine());
            }
        }
        command(bufferedReader.readLine());
    }

    private static void printNavigation() {
        System.out.println("Куда желаете продолжить путь?");
        System.out.println("1. К Друиду!");
        System.out.println("2. В темный лес!");
        System.out.println("3. Выход");
    }

    private static void commitFight() {
        battle.fight(player, monster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у тебя %d опыта и %d золота, осталось %d здоровья.",
                        player.getName(), player.getExperience(), player.getGold(), player.getHealth()));
                System.out.println("Хочешь продолжить поход и ли вернуться в город? (Да/Нет)");
                try {
                    command(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void fightLost() {
            }
        });
    }

    private static FantasticCharacter monster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) {
            return new Goblin("Гоблин", 50, 10, 15, 70, 20);
        } else if (random % 2 == 0) {
            return new Skeleton("Скелет", 40, 20, 10, 60, 15);
        } else
            return new Dragon("Дракон", 60, 50, 40, 80, 30);
    }
}

