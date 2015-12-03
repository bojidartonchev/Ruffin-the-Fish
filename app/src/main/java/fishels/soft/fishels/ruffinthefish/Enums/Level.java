package fishels.soft.fishels.ruffinthefish.Enums;

public enum  Level {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3);

    private Integer value;

    Level(int value) {
        this.value = value;
    }

    public boolean isBiggerThanOrEqual(Level otherLevel){
        if(this.value >= otherLevel.value){
            return true;
        }
        return false;
    }
}
