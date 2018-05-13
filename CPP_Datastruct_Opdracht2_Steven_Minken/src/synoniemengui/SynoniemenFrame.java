/*
 * CPP datastructuren opdracht 2 Steven Minken 12 mei 2018
 */

package synoniemengui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import synoniemen.SynoniemenBeheer;
import synoniemendata.synoniemenException;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasse verantwoordelijk voor de grafische weergave van de synoniemen applicatie
 */
public class SynoniemenFrame extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel jContentPane = null;
  private JLabel woordLabel = null;
  private JLabel synoniemenLabel = null;
  private JScrollPane woordScrollPane = null;
  private JScrollPane synomiemenScrollPane = null;
  private JList<String> woordList = null;
  private JList<String> synoniemenList = null;
  private JTextField woordVeld = null;
  private JTextField synoniemenVeld = null;
  private JButton voegtoeKnop = null;
  private JLabel foutLabel = null;
  
  private SynoniemenBeheer beheer = null;

  /**
   * This is the default constructor
   */
  public SynoniemenFrame() {
    super();
    initialize();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    this.setSize(512, 382);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(getJContentPane());
    this.setTitle("JFrame");
    beheer = new SynoniemenBeheer();
  }
  
  /**
   * Event handler voor klikken in woordList

   */
  private void woordListPressed(){
    
    String woord = woordList.getSelectedValue().toString();
    try {
      String[] synoniemen = beheer.vraagSynoniemenOp(woord);
      synoniemenList.setListData(synoniemen);
      maakFoutlabelLeegAction();
      maakInvoerVeldenLeegAction();
    }
    catch (synoniemenException e) {
      foutLabel.setText(e.getMessage());
    }
  }

  /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      foutLabel = new JLabel();
      foutLabel.setBounds(new Rectangle(13, 252, 473, 55));
      foutLabel.setText("");
      synoniemenLabel = new JLabel();
      synoniemenLabel.setBounds(new Rectangle(154, 8, 109, 20));
      synoniemenLabel.setText("Synoniemen");
      woordLabel = new JLabel();
      woordLabel.setBounds(new Rectangle(14, 8, 109, 20));
      woordLabel.setText("Woorden");
      jContentPane = new JPanel();
      jContentPane.setLayout(null);
      jContentPane.add(woordLabel, null);
      jContentPane.add(synoniemenLabel, null);
      jContentPane.add(getWoordScrollPane(), null);
      jContentPane.add(getSynomiemenScrollPane(), null);
      jContentPane.add(getWoordVeld(), null);
      jContentPane.add(getSynoniemenVeld(), null);
      jContentPane.add(foutLabel, null);
      jContentPane.add(getVoegtoeKnop(), null);
    }
    return jContentPane;
  }

  /**
   * This method initializes woordScrollPane    
   *    
   * @return javax.swing.JScrollPane    
   */
  private JScrollPane getWoordScrollPane() {
    if (woordScrollPane == null) {
      woordScrollPane = new JScrollPane();
      woordScrollPane.setBounds(new Rectangle(12, 40, 109, 125));
      woordScrollPane.setViewportView(getWoordList());
    }
    return woordScrollPane;
  }

  /**
   * This method initializes synomiemenScrollPane       
   *    
   * @return javax.swing.JScrollPane    
   */
  private JScrollPane getSynomiemenScrollPane() {
    if (synomiemenScrollPane == null) {
      synomiemenScrollPane = new JScrollPane();
      synomiemenScrollPane.setBounds(new Rectangle(150, 40, 109, 125));
      synomiemenScrollPane.setViewportView(getSynoniemenList());
    }
    return synomiemenScrollPane;
  }

  /**
   * This method initializes woordList  
   *    
   * @return javax.swing.JList  
   */
  private JList<?> getWoordList() {
    if (woordList == null) {
      woordList = new JList<String>();
      woordList.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent e) {
          woordListPressed();
        }
      });
    }
    return woordList;
  }

  /**
   * This method initializes synoniemenList     
   *    
   * @return javax.swing.JList  
   */
  private JList<?> getSynoniemenList() {
    if (synoniemenList == null) {
      synoniemenList = new JList<String>();
      synoniemenList.setLocation(155, 0);
    }
    return synoniemenList;
  }

  /**
   * This method initializes woordVeld  
   *    
   * @return javax.swing.JTextField     
   */
  private JTextField getWoordVeld() {
    if (woordVeld == null) {
      woordVeld = new JTextField();
      woordVeld.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          maakFoutlabelLeegAction();
          maakSynoniemenLeegAction();
        }
      });
      woordVeld.setBounds(new Rectangle(14, 181, 109, 21));
    }
    return woordVeld;
  }

  /**
   * This method initializes synoniemenVeld     
   *    
   * @return javax.swing.JTextField     
   */
  private JTextField getSynoniemenVeld() {
    if (synoniemenVeld == null) {
      synoniemenVeld = new JTextField();
      synoniemenVeld.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          maakFoutlabelLeegAction();
          maakSynoniemenLeegAction();
        }
      });
      synoniemenVeld.setBounds(new Rectangle(150, 181, 313, 21));
    }
    return synoniemenVeld;
  }

  /**
   * This method initializes voegtoeKnop        
   *    
   * @return javax.swing.JButton        
   */
  private JButton getVoegtoeKnop() {
    if (voegtoeKnop == null) {
      voegtoeKnop = new JButton();
      voegtoeKnop.setText("Toevoegen");
      voegtoeKnop.setBounds(new Rectangle(82, 216, 96, 26));
      voegtoeKnop.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          voegtoeKnopAction();
        }
      });
    }
    return voegtoeKnop;
  }
  
  /**
   * Voegt woorden en synoniemen toe.
   * Er wordt gecontroleerd of een woord uit letters bestaat, en een
   * woordenlijst bestaat uit woorden gescheiden door spaties.
   */
  private void voegtoeKnopAction(){

    String woord = woordVeld.getText().trim();
    
    if (woordVeld.getText().equals("") && synoniemenVeld.getText().equals("")) {
      foutLabel.setText("<html>Beide velden zijn leeg. Vul een woord in in het linkerveld en <br>"
          + "synoniemen gescheiden door spaties in het rechterveld.</html>");
      return;
    }
    
    if (!Pattern.matches("[a-zA-Z]+", woord)){
      foutLabel.setText("<html>het veld woord bestaat niet enkel uit letters,<br> bevat spaties of is leeg.</html>");
      return;
    }
    String alleSynoniemen = synoniemenVeld.getText().trim() + ' ';
    if (!Pattern.matches("([a-zA-Z]+ +)+", alleSynoniemen)){
      foutLabel.setText("Het veld synoniemen bestaat niet uit woorden gescheiden door spaties of is leeg");
      return;
    }

    beheer.voegToe(woordVeld.getText(), synoniemenVeld.getText());
    foutLabel.setText(woord + " toegevoegd");
    
    try {
      String[] woorden = beheer.vraagWoordenOp();
      woordList.setListData(woorden);
    }
    catch (synoniemenException e) {
      foutLabel.setText(e.getMessage());
    }
    finally {
      maakInvoerVeldenLeegAction();
      maakSynoniemenLeegAction();
    }
  }
  
  /**
   * Maakt foutLabel leeg
   */
  public void maakFoutlabelLeegAction() {
    foutLabel.setText("");
  }
  
  /**
   * Maakt synoniemen leeg
   */
  public void maakSynoniemenLeegAction() {
    synoniemenList.setListData(new String[0]);
  }
  
  /**
   * Maakt invoervelden leeg
   */
  public void maakInvoerVeldenLeegAction() {
    woordVeld.setText("");
    synoniemenVeld.setText("");
  }
  
  public static void main(String[] args) {
    SynoniemenFrame fr = new SynoniemenFrame();
    fr.setVisible(true);
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
