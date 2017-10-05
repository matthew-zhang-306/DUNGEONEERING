public class ChanceObject {

    int low;
    int high;
    String name;

    public ChanceObject(int l, int h, String n) {
        low = l;
        high = h;
        name = n;
    }

    public boolean getInRange(int roll) {
        if (roll >= low && roll <= high)
            return true;
        return false;
    }

    public String getName() { return name; }

    public String toString() { return name + " (" + low + "-" + high + ")"; }

}
