/*
 * CPP datastructuren opdracht 2 Steven Minken 12 mei 2018
 * Toelichting Opdracht 2
 * 
 * De klasse synoniemenBeheer maakt door middel van delegatie gebruik van een TreeMap<String>, TreeSet<String>>. 
 * Deze wordt opgenomen als private attribuut in de klasse synoniemenBeheer en enkel de methoden 
 * die nodig zijn zijn beschikbaar van buitenaf.
 * 
 * Voor de samenstelling van de lijsten met synoniemen wordt gebruik gemaakt van TreeSet<String>. De klasse TreeSet
 * extends AbstractSet die de Set interface implementeert. Deze is sorteerbaar en het is niet mogelijk om
 * duplicaten op te nemen. 
 * Aangezien we afwijken van de sortering volgens de natuurlijke ordening geven we bij de creatie van een nieuwe 
 * TreeSet een Comparator mee. Deze wordt meegegeven als lambda expressie, heeft 2 parameters en implementeert 
 * de methode compare. Wanneer er een nieuwe lijst woorden wordt toegevoegd voor een bestaand woord dan worden
 * alleen de nog niet bestaande synoniemen toegevoegd.
 *  
 * De waarden van de synoniemen in de TreeSet worden vervolgens meegegeven als value in de TreeMap met als 
 * bijbehorende key een woord. 
 * De TreeMap extends de abstracte klasse AbstractMap die de Map interface implementeert. De sleutels in de TreeMap 
 * zijn standaard gesorteerd volgens de natuurlijke ordening, dus alfabetisch. Dat is conform de opdrachtomschrijving.
 * Het is niet mogelijk hetzelfde woord dubbel als key op te nemen.
 * 
 * Er is een synoniemenException klasse aangemaakt in een aparte package om specifieke excepties op te kunnen 
 * gooien bij fouten in de applicatie of bij de invoer. 
 */

package synoniemen;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import synoniemendata.synoniemenException;

/**
 * Klasse verantwoordelijk voor het beheer van lijsten met woorden en synoniemen. 
 * Woord- en synoniemcombinaties worden geregistreerd in een TreeMap<String, TreeSet<String>> met als Key de woorden 
 * en als Value de synoniemen.
 * Het is niet mogelijk dubbele woorden of synoniemen op te nemen.
 * Wanneer er nieuwe synoniemen worden ingevoerd voor een woord dan worden enkel de nog niet bestaande synoniemen toegvoegd.
 */
public class SynoniemenBeheer {
  
//  Attribuut voor het registreren van de woorden en synoniemen in een TreeMap
  private TreeMap<String, TreeSet<String>> synoniemenMap;

// Constructor voor het initialiseren van een nieuwe instantie van synoniemenbeheer.
  public SynoniemenBeheer() {
    synoniemenMap = new TreeMap<>();  
}
  
  /**
   * Voegt nieuwe synoniemen toe in een TreeSet aan de TreeMap
   * Indien het woord al voorkomt als sleutel in de TreeMap worden enkel de nog niet voorkomende 
   * woorden toegevoegd aan de TreeSet
   * @param woord het toe te voegen woord
   * @param alleSynoniemen de synoniemen
   */
  public void voegToe(String woord, String alleSynoniemen) {

    woord = woord.trim().toLowerCase();
    String[] synoniemenArray = alleSynoniemen.trim().toLowerCase().split(" ");
    
//    Declareert, creeert en initialiseert een nieuwe TreeSet met een meegegeven Comparator die de sortering aangeeft.
//    De lambda expressie implementeert de methode compare van Comparator met twee
//    mee te geven parameters van type String.
//    Sorteert eerst op lengte en dan alfabetisch.  
    TreeSet<String> synoniemen = new TreeSet<>((String s1, String s2) -> {
      if (s1.length() == s2.length()) {
        return s1.compareTo(s2);
      } else 
        return s1.length()-s2.length();
      });
    
//    Voegt de synoniemen toe aan de TreeSet
    synoniemen.addAll(Arrays.asList(synoniemenArray));

//    Voegt de TreeSet toe aan de Map. Indien het woord al voorkomt: vult
//    de waarden in de TreeMap aan met nieuwe woorden
    if (!synoniemenMap.containsKey(woord)) {
      synoniemenMap.put(woord, synoniemen);
    } else {
      synoniemenMap.get(woord).addAll(Arrays.asList(synoniemenArray));
    }
  
  }
  
  /**
   * Retourneert String[] van de woorden die als Key voorkomen in de TreeMap
   * @return String[] array van de woorden
   * @throws synoniemenException exceptie wanneer de lijst leeg is
   */
  public String[] vraagWoordenOp() throws synoniemenException {
    
    if (!synoniemenMap.isEmpty()) {
      Set<String> set = synoniemenMap.keySet();
      return set.toArray(new String[set.size()]);
    }
    else  {
      throw new synoniemenException("De lijst is leeg ");
    }
  }
  
/**
 * Retourneert een String[] van de lijst met synoniemen voor een ingegeven woord
 * @param woord het woord in de map
 * @return String[] de array met de synoniemen
 * @throws synoniemenException exceptie wanneer synoniem niet gevonden wordt
 */
  public String[] vraagSynoniemenOp(String woord) throws synoniemenException {

    if (synoniemenMap.containsKey(woord)) {
      TreeSet<String> set = synoniemenMap.get(woord);
      return set.toArray(new String[set.size()]);
    }
    else  {
      throw new synoniemenException("Er zijn geen synoniemen gevonden voor: " + woord);
    }
  }

 }

