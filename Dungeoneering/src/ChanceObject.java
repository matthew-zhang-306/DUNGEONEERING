/**
 * <h1>ChanceObject</h1>
 * The ChanceObject class holds data on a Pickup with a chance of occuring.
 * <p>
 *     It stores the lower and upper bounds for a roll and the name.
 * </p>
 *
 * @author  myName
 * @version 1.0
 */

public class ChanceObject {

    int low;
    int high;
    String name;

    /**
     * Constructor for a ChanceObject.
     * @param l The lower bound of the roll
     * @param h The upper bound of the roll
     * @param n The name of the object
     */
    public ChanceObject(int l, int h, String n) {
        low = l;
        high = h;
        name = n;
    }

    /**
     * Returns whether a specified roll is within the range of the bounds of the object.
     * @return low <= roll <= high
     */
    public boolean getInRange(int roll) {
        if (roll >= low && roll <= high)
            return true;
        return false;
    }

    /**
     * Returns the name of the object.
     * @return name
     */
    public String getName() { return name; }

    /**
     * Returns a printout of the ChanceObject. Used purely for testing.
     * @return this as a String
     */
    public String toString() { return name + " (" + low + "-" + high + ")"; }

}
