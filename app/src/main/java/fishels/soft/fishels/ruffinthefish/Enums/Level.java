package fishels.soft.fishels.ruffinthefish.Enums;

public enum  Level {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);

    private Integer value;

    Level(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isBiggerThanOrEqual(Level otherLevel){
        if(this.value >= otherLevel.value){
            return true;
        }
        return false;
    }
}
