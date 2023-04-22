public abstract class FantasticCharacter implements Fighter {

    private String name;
    private int health;
    private int gold;
    private int dexterity; //ловкость
    private int experience; //опыт
    private int power;

    public FantasticCharacter(String name, int health, int gold, int dexterity, int experience, int power) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.dexterity = dexterity;
        this.experience = experience;
        this.power = power;
    }

    public int attack() {
        if (dexterity * 3 > getRandomValue()) return power;
        else return 0;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String toString() {
        return String.format("%s здоровье: %d", name, health);
    }
}
