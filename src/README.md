# Défi de fin de S1 - 2020

## Classes

Les classes sont fournies telle quelles, pour pouvoir les utiliser il faut passer par un compilateur et interpréteur

On peut utiliser un interpréteur intégré comme celui d'intellij idea ou on peut aussi compiler et interpréter via la CLI grâce au JDK

Compiler les classes :

```shell script
javac *.java -Xlint:deprecation 
# Rajoutez -Xlint:deprecation afin de pouvoir compiler StdDraw sans erreurs sur les dernières versions du JDK
```

Supprimer les classes :

```shell script
rm *.class
```

### DiagBar

Cette classe s'occupe de la partie visuelle du diagramme en barres

### Bar

Cette classe représente les données d'une barre, elle implémente la classe comparable qui permet par la suite de ranger les barres par valeurs croissantes

### PreprocessData

Cette class permet de transformer un fichier tableur type `.csv` et d'ordonner ses données pour les utiliser dans le diagramme animé

(On laisse aux utilisateurs le soin de préciser le titre du diagramme ainsi que la source et le libellé de l'axe x dans le fichier généré)

Usage :

```shell script
java PreprocessData < <fichier.csv> <idCaption> <idNames> <idValues> <idCats> [> <fichier.txt>]
```

### AnimatedDiagBar

Cette classe utilise les classes ci-dessus afin de générer plusieurs diagrammes à la suite et de les afficher, créant alors une animation

Usage :

```shell script
java AnimatedDiagBar < <fichier.txt> <nBars> <sort|nosort>
```

© Léo Hugonnot - 2020
---