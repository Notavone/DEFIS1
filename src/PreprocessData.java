import java.util.ArrayList;
import java.util.Scanner;

public class PreprocessData {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Renvoie la prochaine ligne de l'entrée standard sous forme de tableau
     *
     * @return un tableau de chaines de caractères
     */
    private static String[] getInput() {
        return input.nextLine().split(",");
    }

    public static void main(String[] args) {
        // On initialise un jeu de données
        ArrayList<String> dataSet = new ArrayList<>();
        final int idCaption = Integer.parseInt(args[0]);
        final int idNames = Integer.parseInt(args[1]);
        final int idValues = Integer.parseInt(args[2]);
        final int idCats = Integer.parseInt(args[3]);

        String lastCaption = "";
        while (input.hasNext()) {
            String[] line = getInput();
            if (!line[idCaption].equals(lastCaption) && dataSet.size() != 0) {
                // Si on atteint une ligne pour laquelle le caption n'est plus le même que celui stocké
                // OU
                // que la taille du précédent jeu de données est égale à 0
                // On envoi le jeu de données dans la sortie standard
                System.out.println(dataSet.size());
                dataSet.forEach(System.out::println);
                System.out.println();
                // On remet à zéro le tableau de données
                dataSet.clear();
            }
            lastCaption = line[idCaption];

            String[] dataLine = new String[4];
            dataLine[0] = line[idCaption];
            dataLine[1] = line[idNames];
            dataLine[2] = String.valueOf((int) Math.round(Double.parseDouble(line[idValues])));
            dataLine[3] = line[idCats];
            dataSet.add(String.join(",", dataLine));
        }
        System.out.println(dataSet.size());
        dataSet.forEach(System.out::println);
        System.out.println();
    }
}
