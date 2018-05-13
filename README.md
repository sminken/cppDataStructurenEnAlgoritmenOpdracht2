# cpp datastructuren en algoritmen: opdracht 2

Implementatie van de klasse synoniemenBeheer met bijbehorende grafische interface en een exceptieklasse. 
De applicatie legt een collectie vast van woorden met bijbehorende synoniemen. Hierbij wordt gebruik gemaakt van klassen 
uit het java collections framewor en Map. De klasse maakt gebruik van delegatie en slaat de gegevens op in een 
TreeMap<<String>,<TreeSet<String>>. Aan de TreeSet wordt door middel van een lambda expressie een Comparator meegegeven 
die de methode compare implementeerd omdat af wordt geweken van de natuurlijke ordening. 
De synoniemen worden eerst op lengte gesorteerd en vervolgens op alfabetische volgorde.
