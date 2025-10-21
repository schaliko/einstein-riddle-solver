# Einstein-Rätsel (Zebra Puzzle)  
### Lösung in Java – Hausaufgabe

---

## Aufgabenstellung

Ein Rätsel, das (nur der Legende nach) von **Albert Einstein** stammt:

1. Es gibt fünf Häuser mit je einer anderen Farbe.  
2. In jedem Haus wohnt eine Person einer anderen Nationalität.  
3. Jeder Hausbewohner bevorzugt ein bestimmtes Getränk, raucht eine bestimmte Zigarettenmarke und hält ein bestimmtes Haustier.  
4. Keine der 5 Personen bevorzugt das gleiche Getränk, raucht die gleiche Zigarettenmarke oder hält das gleiche Tier wie irgend eine der anderen Personen.  
5. Der Brite lebt im roten Haus.  
6. Der Schwede hält einen Hund.  
7. Der Däne trinkt gerne Tee.  
8. Der Deutsche raucht Rothmanns.  
9. Der Besitzer des grünen Hauses trinkt Kaffee.  
10. Der Winfield-Raucher trinkt gerne Bier.  
11. Der Norweger wohnt im ersten Haus.  
12. Der Norweger wohnt neben dem blauen Haus.  
13. Der Besitzer des gelben Hauses raucht Dunhill.  
14. Die Person, die Pall Mall raucht, hält einen Vogel.  
15. Der Mann, der im mittleren Haus wohnt, trinkt Milch.  
16. Das grüne Haus steht unmittelbar links vom weißen Haus.  
17. Der Marlboro-Raucher wohnt neben dem, der eine Katze hält.  
18. Der Marlboro-Raucher hat einen Nachbarn, der Wasser trinkt.  
19. Der Mann, der ein Pferd hält, wohnt neben dem, der Dunhill raucht.  

**Frage:** Wer hält einen Fisch als Haustier?

---

## Ziel der Aufgabe

Ein Programm soll **alle möglichen Kombinationen** für die Häuser und Eigenschaften durchrechnen und diejenigen finden, die **alle obigen Regeln** erfüllen.  
Das Programm soll am Ende die gültige(n) Lösung(en) ausgeben.

---

## Verwendete Sprache und Paradigma

- Implementierung in **Java** (objektorientiert)  
- Nutzung von **Enums** zur Modellierung der festen Werte  
- **Generische Permutationsfunktionen** zur Erzeugung aller möglichen Kombinationen  
- **Constraint-Pruning**: ungültige Kombinationen werden schrittweise ausgeschlossen  
- Ausgabe erfolgt in tabellarischer, übersichtlicher Form  

---

## Projektstruktur
```

├── README.md     
├── src/
│   ├── Permutation.java # Enthält die Permutationslogik (Erzeugung aller Kombinationen)
    ├── Printer.java # Zuständig für die formatierte Ausgabe der Lösung
    ├── Solver.java # Hauptklasse: enthält alle 19 Bedingungen und die Hauptlogik
    └── RiddleEnums.java # Definition der Enums (Farben, Nationen, Getränke, Zigaretten, Haustiere) 
```

## ⚙️ Ausführung

```bash
javac riddle/*.java
java riddle.Solver