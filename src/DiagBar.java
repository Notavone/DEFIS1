import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import static java.awt.Color.*;

public class DiagBar {

    private final String title;
    private final String xAxisLabel;
    private final String dataSource;
    private final Color[] colorsList = {LIGHT_GRAY, PINK, RED, BLUE, ORANGE, CYAN};
    private String caption;
    private ArrayList<String> names;
    private ArrayList<Integer> values;
    private ArrayList<Color> colors;

    /**
     * Créé un nouveau diagramme à barre.
     *
     * @param title      le titre
     * @param xAxisLabel la légende de l'axe horizontal
     * @param dataSource l'origine des données
     */
    public DiagBar(String title, String xAxisLabel, String dataSource) {
        if (title == null) throw new IllegalArgumentException("name is null");
        if (xAxisLabel == null) throw new IllegalArgumentException("x-axis label is null");
        if (dataSource == null) throw new IllegalArgumentException("data source is null");
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.dataSource = dataSource;
        reset();
    }

    /**
     * Permet de réinitialiser un DiagBar
     */
    public void reset() {
        names = new ArrayList<>();
        values = new ArrayList<>();
        colors = new ArrayList<>();
        caption = "";
    }

    /**
     * Sets the caption of this bar chart.
     * The caption is drawn in the lower-right part of the window.
     *
     * @param caption the caption
     */
    public void setCaption(String caption) {
        if (caption == null) throw new IllegalArgumentException("caption is null");
        this.caption = caption;
    }

    /**
     * Ajoute une barre au diagramme.
     * La longueur de la barre est proportionnelle à sa valeur.
     * Les barre sont tracées de haut en bas dans l'ordre où elle sont ajoutées.
     * Toutes les barres d'une même catégorie sont de la même couleur.
     *
     * @param name     le nom de la barre
     * @param value    la valeur de la barre
     * @param category la catégorie de la barre
     */
    public void add(String name, int value, String category) {
        if (name == null) throw new IllegalArgumentException("name is null");
        if (category == null) throw new IllegalArgumentException("category is null");
        if (value <= 0) throw new IllegalArgumentException("value must be positive");

        Color color = colorsList[category.length() % colorsList.length];
        names.add(name);
        values.add(value);
        colors.add(color);
    }

    /**
     * Dessine une image statique du diagramme à barre, pour créer une animation, se reporter à {@see AnimatedDiagBar}
     */
    public void draw() {
        if (names.isEmpty()) return; // Ne rien faire si le jeu de données est vide

        int nBars = names.size();
        int differenceAxe = 1;
        double valeurMax = 0;
        for (int valeur : values) {
            if (valeur > valeurMax) valeurMax = valeur;
        }
        // Nombre maximum d'axes qui affichent des valeurs de références
        final int nAxes = 10;
        while (Math.floor(valeurMax / differenceAxe) >= nAxes) {
            // Permet d'avoir uniquement des multiples de 10, 100, 1000
            if (differenceAxe % 9 == 2) differenceAxe *= 5.0 / 2.0;
            else differenceAxe *= 2;
        }

        // On change l'échelle de la fenêtre afin de laisser un vide avant les barres et après
        // C'est purement esthétique
        StdDraw.setXscale(-0.01 * valeurMax, 1.2 * valeurMax);
        StdDraw.setYscale(-0.01 * nBars, nBars * 1.25);

        // On dessine le titre du diagramme
        StdDraw.setPenColor(BLACK);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 24));
        StdDraw.text(0.6 * valeurMax, nBars * 1.2, title);

        // On dessine le libellé de l'axe x
        StdDraw.setPenColor(GRAY);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
        StdDraw.textLeft(0, nBars * 1.10, xAxisLabel);

        // On dessine les valeurs de référence sur l'axe x
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int i = 0; i <= valeurMax; i += differenceAxe) {
            StdDraw.setPenColor(GRAY);
            StdDraw.text(i, nBars * 1.02, String.format("%,d", i));
            StdDraw.setPenColor(LIGHT_GRAY);
            StdDraw.line(i, 0.1, i, nBars * 1.0);
        }

        // Pour chaque barre du diagramme
        for (int i = 0; i < names.size(); i++) {
            // On récupère ses informations
            String name = names.get(i);
            Color color = colors.get(i);
            int value = values.get(i);

            // On trace le rectangle représentant la valeur de la barre
            StdDraw.setPenColor(color);
            StdDraw.filledRectangle(0.5 * value, nBars - i - 0.5, 0.5 * value, 0.4);

            // On écrit le nom de la barre quelques pixels avant la fin du rectangle
            StdDraw.setPenColor(BLACK);
            int fontSize = (int) Math.ceil(14 * 10.0 / nBars);
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, fontSize));
            StdDraw.textRight(value - 0.01 * valeurMax, nBars - i - 0.5, name);


            // On écrit la valeur de la barre quelques pixels après la fin du rectangle
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
            StdDraw.setPenColor(DARK_GRAY);
            StdDraw.textLeft(value + 0.01 * valeurMax, nBars - i - 0.5, String.format("%,d", value));
        }

        // On écrit le caption du diagramme en bas à droite
        StdDraw.setPenColor(LIGHT_GRAY);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 40));
        StdDraw.textRight(1.15 * valeurMax, 0.2 * nBars, caption);

        // On écrit la source des données en bas à droite, sous le caption
        StdDraw.setPenColor(LIGHT_GRAY);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 14));
        StdDraw.textRight(1.14 * valeurMax, 0.1 * nBars, dataSource);
    }

    public static void main(String[] args) {
        // création du diagramme
        String title = "Famous brands";
        String xAxis = "stock value $(million)";
        String source = "Source: Interbrand website";
        DiagBar diag = new DiagBar(title, xAxis, source);
        diag.setCaption("2000-01-01");

        // ajout des barres suivantes au diagramme
        diag.add("adidas", 3791, "Sporting Goods");
        diag.add("Amazon", 4528, "Retail");
        diag.add("American Express", 16122, "Financial Services");
        diag.add("AOL", 4531, "Media");
        diag.add("Apple", 6594, "Technology");
        diag.add("AT&T", 25548, "Telecommunications");
        diag.add("Bacardi", 3187, "Alcohol");
        diag.add("Barbie", 2315, "Toys & Games");
        diag.add("BMW", 12969, "Automotive");
        diag.add("BP", 3066, "Energy");
        diag.add("Budweiser", 10684, "Alcohol");
        diag.add("Burger King", 2701, "Restaurants");
        diag.add("Chanel", 4141, "Luxury");

        // rendu du diagramme
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();
        diag.draw();
        StdDraw.show();
    }
}
