public class Battle {

    public void fight(FantasticCharacter superHero, FantasticCharacter monster, Realm.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("---- Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(superHero, monster, fightCallback);
                } else {
                    isFightEnded = makeHit(monster, superHero, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private boolean makeHit(FantasticCharacter defender, FantasticCharacter attacker, Realm.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHealth() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s Нанесен удар силой %d едениц!", attacker.getName(), hit));
            System.out.println(String.format("У %s остается %d здоровья!..", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s промахнулся!!!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof SuperHero) {
            System.out.println("Ваш супергерой погиб в бою");
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            System.out.println(String.format("Монстр побежден. Супергерой получает %d опыт и %d золота",
                    defender.getExperience(), defender.getGold()));
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHealth(defenderHealth);
            return false;
        }
    }
}
