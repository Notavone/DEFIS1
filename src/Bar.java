import java.util.Arrays;

public class Bar implements Comparable<Bar> {

    private final String name;
    private final int value;
    private final String category;

    /**
     * Crée une nouvelle barre
     *
     * @param name     nom de la barre
     * @param value    valeur de la barre
     * @param category catégorie de la barre
     */
    public Bar(String name, int value, String category) {
        this.name = name;
        this.value = value;
        this.category = category;
    }

    /**
     * Assesseur : renvoie le nom de la barre
     *
     * @return le nom de la barre
     */
    public String getName() {
        return this.name;
    }

    /**
     * Assesseur : renvoie la valeur de la barre
     *
     * @return la valeur de la barre
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Assesseur : renvoie la catégorie de la barre
     *
     * @return la catégorie de la barre
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Envoie une représentation de chaque barre dans l'entrée standard
     *
     * @param tBars tableau de barres
     */
    public static void display(Bar[] tBars) {
        for (Bar tBar : tBars) {
            System.out.println(tBar);
        }
    }

    /**
     * Compare la valeur de deux barres
     *
     * @param that une autre barre
     * @return 1 si la valeur de l'autre barre est supérieure à la valeur de cette barre, -1 sinon, 0 si les valeurs sont égales
     */
    public int compareTo(Bar that) {
        if (this.value == that.value) return 0;
        if (this.value < that.value) return 1;
        return -1;
    }

    /**
     * Représentation d'une barre de manière textuelle
     *
     * @return une chaine de caractères
     */
    public String toString() {
        return this.name + ", " + this.value + ", " + this.category;
    }

    public static void main(String[] args) {
        Bar[] tBars = new Bar[13];

        tBars[0] = new Bar("adidas", 3791, "Sporting Goods");
        tBars[1] = new Bar("Amazon", 4528, "Retail");
        tBars[2] = new Bar("American Express", 16122, "Financial Services");
        tBars[3] = new Bar("AOL", 4531, "Media");
        tBars[4] = new Bar("Apple", 6594, "Technology");
        tBars[5] = new Bar("AT&T", 25548, "Telecommunications");
        tBars[6] = new Bar("Bacardi", 3187, "Alcohol");
        tBars[7] = new Bar("Barbie", 2315, "Toys & Games");
        tBars[8] = new Bar("BMW", 12969, "Automotive");
        tBars[9] = new Bar("BP", 3066, "Energy");
        tBars[10] = new Bar("Budweiser", 10684, "Alcohol");
        tBars[11] = new Bar("Burger King", 2701, "Restaurants");
        tBars[12] = new Bar("Chanel", 4141, "Luxury");

        Arrays.sort(tBars);
        Bar.display(tBars);
    }

}