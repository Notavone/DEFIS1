import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AnimatedDiagBar {
    private static final Scanner input = new Scanner(System.in);
    private static final int CAPTION_INDEX = 0; // Indique a quel index regarder afin de trouver les captions
    private static final int NAME_INDEX = 1; // Indique a quel index regarder afin de trouver les noms des barres
    private static final int VALUE_INDEX = 2; // Indique a quel index regarder afin de trouver les valeurs des barres
    private static final int CATEGORY_INDEX = 3; // Indique a quel index regarder afin de trouver les catégories des barres
    private static final int FRAMES_PER_SECOND = 10; // Indique la vitesse de rafraîchissement des diagrammes en IPS

    /**
     * Renvoie la prochaine ligne de l'entrée standard sous forme de tableau
     *
     * @return un tableau de chaines de caractères
     */
    private static String[] getInput() {
        return input.nextLine().split(",");
    }

    public static void main(String[] args) {
        // Initialisation de la fenêtre StdDraw
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();

        // Initialisation et allocation des tableau pour les captions et les jeux de données
        ArrayList<Bar[]> dataSet = new ArrayList<>();
        ArrayList<String> captions = new ArrayList<>();

        // Lit le nombre de barres que l'on souhaite afficher et s'il faut trier les barres depuis les arguments
        int nBars = Integer.parseInt(args[0]);
        boolean isSorted = args[1].equals("sort");

        boolean isLastLineNull = false;

        // Crée un diagramme à barres
        String title = input.nextLine();
        String xAxisLabel = input.nextLine();
        String dataSource = input.nextLine();
        DiagBar diagBar = new DiagBar(title, xAxisLabel, dataSource);

        while (input.hasNext()) {
            String[] line = getInput();
            if (line.length == 1 && line[0].equals("") && isLastLineNull)
                break; // Si on atteint la fin du fichier de jeux de données
            if (line.length == 1 && line[0].equals(""))
                isLastLineNull = true; // Si on atteint la fin d'un jeu de données
            else {
                // Lis la taille du jeu de données et initialise un tableau de barres de cette taille
                int length = Integer.parseInt(line[0]);
                Bar[] set = new Bar[length];

                // Initialise les barres
                for (int i = 0; i < length; i++) {
                    line = getInput();
                    String name = line[NAME_INDEX];
                    String category = line[CATEGORY_INDEX];
                    int value = Integer.parseInt(line[VALUE_INDEX]);

                    set[i] = new Bar(name, value, category);
                }
                captions.add(line[CAPTION_INDEX]);
                dataSet.add(set); // Ajoute le tableau de barres au jeux de données
                isLastLineNull = false;
            }
        }

        // Pour chaque jeu de données
        for (int i = 0; i < dataSet.size(); i++) {
            // On récupère le jeu de données
            Bar[] set = dataSet.get(i);

            diagBar.reset();
            diagBar.setCaption(captions.get(i));
            StdDraw.clear();
            if (isSorted) Arrays.sort(set);

            // Pour les nBars premières barres
            for (int j = 0; j < nBars; j++) {
                // On récupère la barre
                Bar bar = set[j];

                String name = bar.getName();
                String category = bar.getCategory();
                int value = bar.getValue();

                // On ajoute la barre au diagramme
                diagBar.add(name, value, category);
            }

            // On affiche le diagramme
            diagBar.draw();
            StdDraw.show();
            StdDraw.pause(1000 / FRAMES_PER_SECOND);
        }
    }
}
