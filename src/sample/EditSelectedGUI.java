package sample;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EditSelectedGUI extends JFrame {
    private JPanel mainBackground;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JList listZmluvy;
    private JButton buttonCancel;
    private JButton buttonEdit;
    private JLabel labelNumZmluva;
    private JTextField textLicencePlate;
    private JLabel labelCustomer;
    private JLabel labelExpiration;
    private JLabel labelCreationDate;
    private JCheckBox CheckBox1;
    private JCheckBox CheckBox2;
    private JCheckBox CheckBox3;
    private JCheckBox CheckBox4;
    private JCheckBox CheckBox5;
    private JCheckBox CheckBox6;
    private JCheckBox CheckBox7;
    private JCheckBox CheckBox8;
    private JComboBox comboBox1;
    private JLabel labelTypVozidla;
    private JLabel labelKategoriaVozidla;
    private JLabel labelCena;
    private JLabel labelStav;
    private JTextField textName;
    private JLabel CheckBox1_popis;
    private JLabel CheckBox1_cena;
    private JLabel CheckBox2_cena;
    private JLabel CheckBox3_cena;
    private JLabel CheckBox4_cena;
    private JLabel CheckBox5_cena;
    private JLabel CheckBox6_cena;
    private JLabel CheckBox7_cena;
    private JLabel CheckBox8_cena;
    private JLabel CheckBox2_popis;
    private JLabel CheckBox3_popis;
    private JLabel CheckBox4_popis;
    private JLabel CheckBox5_popis;
    private JLabel CheckBox6_popis;
    private JLabel CheckBox7_popis;
    private JLabel CheckBox8_popis;
    private JLabel labelSum;
    static ArrayList<Zmluva> zmluvy;
    private DefaultListModel listZmluvyModel;
    //static String idBalikyAll;

    EditSelectedGUI(int id) {
        super("Test");
        //this.setUndecorated(true);
        //this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setContentPane(this.mainBackground);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        zmluvy = new ArrayList<Zmluva>();
        listZmluvyModel = new DefaultListModel();
        listZmluvy.setModel(listZmluvyModel);
        buttonEdit.setEnabled(false);

        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numZmluva = listZmluvy.getSelectedIndex();
                if (numZmluva >= 0) {
                    Zmluva z = zmluvy.get(numZmluva);
                    //textName.getText();
                    //textLicencePlate.getText();
                    //System.out.println(textLicencePlate.getText());

                    String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Zmluva";
                    String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072zmluva/types\">\n" +
                            "   <soapenv:Header/>\n" +
                            "   <soapenv:Body>\n" +
                            "       <typ:insert>\n" +
                            "           <team_id>072</team_id>\n" +
                            "           <team_password>ZXKMZ6</team_password>\n" +
                            "           <Zmluva>\n" +
                            "               <id></id>\n" +
                            "               <name>" + z.getNameZmluva() + "</name>\n" +
                            "               <poistenec_id>" + String.valueOf(id) + "</poistenec_id>\n" +
                            "               <cislo_zmluvy>" + z.getNumZmluva() + "-new</cislo_zmluvy>\n" +
                            "               <platnost_od>" + String.valueOf(z.getDateOfCreation()) + "</platnost_od>\n" +
                            "               <platnost_do>" + String.valueOf(z.getDateOfExpiration()) + "</platnost_do>\n" +
                            "               <evc>" + textLicencePlate.getText() + "</evc>\n" +
                            "               <sposob_platby>1</sposob_platby>\n" +
                            "               <kategoria_vozidla>" + z.getKategoriaVozidla() + "</kategoria_vozidla>\n" +
                            "               <stav>Upravená</stav>\n" +
                            "               <typ_vozidla>" + z.getTypVozidla() + "</typ_vozidla>\n" +
                            "               <vlastnik_vozidla>" + textName.getText() + "</vlastnik_vozidla>\n" +
                            "               <baliky_id>0</baliky_id>\n" +
                            "           </Zmluva>\n" +
                            "       </typ:insert>\n" +
                            "   </soapenv:Body>\n" +
                            "</soapenv:Envelope>";
                    //System.out.println(xml);
                    String zmluvaReturnNew = createRequest(url, xml);
                    response.delete(0,response.length());

                    //String zmluvaIdNew = ;

                    /* ZISKAJ ID NOVEJ ZMLUVY */
                    Pattern patternZmluvaIdNew = Pattern.compile("<id>(.*?)</id>", Pattern.DOTALL);
                    Matcher m_zmluvaIdNew = patternZmluvaIdNew.matcher(zmluvaReturnNew);
                    String zmluvaIdNew;
                    if (m_zmluvaIdNew.find()) {
                        zmluvaIdNew = m_zmluvaIdNew.group(1);
                    } else {
                        zmluvaIdNew = "Error.";
                    }
                    //System.out.println(zmluvaIdNew);

                    String tmp_name = "", tmp_popis = "", tmp_cena = "";
                    Boolean tmp_stav = false;
                    String id_balik_1 = "", id_balik_2 = "", id_balik_3 = "",id_balik_4 = "", id_balik_5 = "", id_balik_6 = "", id_balik_7 = "", id_balik_8 = "";
                    for (int i = 0; i < 8; i++){


                        if (i == 0) {
                            //System.out.println(i);;
                            tmp_name  = CheckBox1.getText();
                            tmp_popis = CheckBox1_popis.getText();
                            tmp_cena = CheckBox1_cena.getText();
                            tmp_stav = CheckBox1.isSelected();
                        } else if (i == 1) {
                            //System.out.println(i);;
                            tmp_name  = CheckBox2.getText();
                            tmp_popis = CheckBox2_popis.getText();
                            tmp_cena = CheckBox2_cena.getText();
                            tmp_stav = CheckBox2.isSelected();
                        } else if (i == 2) {
                            //System.out.println(i);;
                            tmp_name  = CheckBox3.getText();
                            tmp_popis = CheckBox3_popis.getText();
                            tmp_cena = CheckBox3_cena.getText();
                            tmp_stav = CheckBox3.isSelected();
                        } else if (i == 3) {
                            //System.out.println(i);;
                            tmp_name  = CheckBox4.getText();
                            tmp_popis = CheckBox4_popis.getText();
                            tmp_cena = CheckBox4_cena.getText();
                            tmp_stav = CheckBox4.isSelected();
                        } else if (i == 4) {
                            //System.out.println(i);;
                            tmp_name  = CheckBox5.getText();
                            tmp_popis = CheckBox5_popis.getText();
                            tmp_cena = CheckBox5_cena.getText();
                            tmp_stav = CheckBox5.isSelected();
                        } else if (i == 5) {
                            //System.out.println(i);;
                            tmp_name  = CheckBox6.getText();
                            tmp_popis = CheckBox6_popis.getText();
                            tmp_cena = CheckBox6_cena.getText();
                            tmp_stav = CheckBox6.isSelected();
                        } else if (i == 6) {
                            //System.out.println(i);;
                            tmp_name  = CheckBox7.getText();
                            tmp_popis = CheckBox7_popis.getText();
                            tmp_cena = CheckBox7_cena.getText();
                            tmp_stav = CheckBox7.isSelected();
                        } else if (i == 7) {
                            //System.out.println(i);;
                            tmp_name  = CheckBox8.getText();
                            tmp_popis = CheckBox8_popis.getText();
                            tmp_cena = CheckBox8_cena.getText();
                            tmp_stav = CheckBox8.isSelected();
                        }

                        String tmp_cena_x = tmp_cena.replace(" €","");
                        String balikUrl = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Balik";
                        String balikXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072balik/types\">\n" +
                                "   <soapenv:Header/>\n" +
                                "   <soapenv:Body>\n" +
                                "       <typ:insert>\n" +
                                "           <team_id>072</team_id>\n" +
                                "           <team_password>ZXKMZ6</team_password>\n" +
                                "           <Balik>\n" +
                                "               <id></id>\n" +
                                "               <name>" + tmp_name + "</name>\n" +
                                "               <popis>" + tmp_popis + "</popis>\n" +
                                "               <cena>" + tmp_cena_x + "</cena>\n" +
                                "               <stav>" + tmp_stav + "</stav>\n" +
                                "               <zmluva_id>" + zmluvaIdNew + "</zmluva_id>\n" +
                                "           </Balik>\n" +
                                "       </typ:insert>\n" +
                                "   </soapenv:Body>\n" +
                                "</soapenv:Envelope>";
                        //System.out.println(balikXml);

                        String balikReturnNew = createRequest(balikUrl, balikXml);
                        response.delete(0,response.length());

                        //System.out.println(balikReturnNew);
                        Pattern patternBalikIdNew = Pattern.compile("<id>(.*?)</id>", Pattern.DOTALL);
                        Matcher m_balikIdNew = patternBalikIdNew.matcher(balikReturnNew);
                        String balikIdNew;
                        if (m_balikIdNew.find()) {
                            balikIdNew = m_balikIdNew.group(1);
                        } else {
                            balikIdNew = "Error.";
                        }
                        //System.out.println(balikIdNew);

                        if (i == 0) {
                            //System.out.println(i);;
                            id_balik_1 = balikIdNew;
                        } else if (i == 1) {
                            //System.out.println(i);;
                            id_balik_2 = balikIdNew;
                        } else if (i == 2) {
                            //System.out.println(i);;
                            id_balik_3 = balikIdNew;
                        } else if (i == 3) {
                            //System.out.println(i);;
                            id_balik_4 = balikIdNew;
                        } else if (i == 4) {
                            //System.out.println(i);;
                            id_balik_5 = balikIdNew;
                        } else if (i == 5) {
                            //System.out.println(i);;
                            id_balik_6 = balikIdNew;
                        } else if (i == 6) {
                            //System.out.println(i);;
                            id_balik_7 = balikIdNew;
                        } else if (i == 7) {
                            //System.out.println(i);;
                            id_balik_8 = balikIdNew;
                        }

                    }

                    String balikyUrl = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Baliky";
                    String balikyXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072baliky/types\">\n" +
                            "   <soapenv:Header/>\n" +
                            "   <soapenv:Body>\n" +
                            "       <typ:insert>\n" +
                            "           <team_id>072</team_id>\n" +
                            "           <team_password>ZXKMZ6</team_password>\n" +
                            "           <Baliky>\n" +
                            "               <id></id>\n" +
                            "               <name></name>\n" +
                            "               <id_balik_1>" + id_balik_1 + "</id_balik_1>\n" +
                            "               <id_balik_2>" + id_balik_2 + "</id_balik_2>\n" +
                            "               <id_balik_3>" + id_balik_3 + "</id_balik_3>\n" +
                            "               <id_balik_4>" + id_balik_4 + "</id_balik_4>\n" +
                            "               <id_balik_5>" + id_balik_5 + "</id_balik_5>\n" +
                            "               <id_balik_6>" + id_balik_6 + "</id_balik_6>\n" +
                            "               <id_balik_7>" + id_balik_7 + "</id_balik_7>\n" +
                            "               <id_balik_8>" + id_balik_8 + "</id_balik_8>\n" +
                            "           </Baliky>\n" +
                            "       </typ:insert>\n" +
                            "   </soapenv:Body>\n" +
                            "</soapenv:Envelope>";
                    //System.out.println(balikyXml);
                    String balikyReturnNew = createRequest(balikyUrl, balikyXml);
                    response.delete(0,response.length());

                    Pattern patternBalikyIdNew = Pattern.compile("<id>(.*?)</id>", Pattern.DOTALL);
                    Matcher m_balikyIdNew = patternBalikyIdNew.matcher(balikyReturnNew);
                    String balikyIdNew;
                    if (m_balikyIdNew.find()) {
                        balikyIdNew = m_balikyIdNew.group(1);
                    } else {
                        balikyIdNew = "Error.";
                    }


                    url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Zmluva";
                    xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072zmluva/types\">\n" +
                            "   <soapenv:Header/>\n" +
                            "   <soapenv:Body>\n" +
                            "       <typ:update>\n" +
                            "           <team_id>072</team_id>\n" +
                            "           <team_password>ZXKMZ6</team_password>\n" +
                            "           <entity_id>" + zmluvaIdNew + "</entity_id>\n" +
                            "           <Zmluva>\n" +
                            "               <id>" + zmluvaIdNew + "</id>\n" +
                            "               <name>" + z.getNameZmluva() + "</name>\n" +
                            "               <poistenec_id>" + String.valueOf(id) + "</poistenec_id>\n" +
                            "               <cislo_zmluvy>" + z.getNumZmluva() + "-new</cislo_zmluvy>\n" +
                            "               <platnost_od>" + String.valueOf(z.getDateOfCreation()) + "</platnost_od>\n" +
                            "               <platnost_do>" + String.valueOf(z.getDateOfExpiration()) + "</platnost_do>\n" +
                            "               <evc>" + textLicencePlate.getText() + "</evc>\n" +
                            "               <sposob_platby>1</sposob_platby>\n" +
                            "               <kategoria_vozidla>" + z.getKategoriaVozidla() + "</kategoria_vozidla>\n" +
                            "               <stav>Upravená</stav>\n" +
                            "               <typ_vozidla>" + z.getTypVozidla() + "</typ_vozidla>\n" +
                            "               <vlastnik_vozidla>" + textName.getText() + "</vlastnik_vozidla>\n" +
                            "               <baliky_id>" + balikyIdNew + "</baliky_id>\n" +
                            "           </Zmluva>\n" +
                            "       </typ:update>\n" +
                            "   </soapenv:Body>\n" +
                            "</soapenv:Envelope>";
                    //System.out.println(xml);
                    createRequest(url, xml);
                    response.delete(0,response.length());

                    refreshZmluvyList();
                    zmluvy.clear();
                    populate(id);
                }

            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        listZmluvy.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int numZmluva = listZmluvy.getSelectedIndex();
                if (numZmluva >= 0) {
                    Zmluva z = zmluvy.get(numZmluva);
                    double sum = 0;
                    labelCustomer.setText(z.getName());
                    //textLicencePlate.setText(z.getLicencePlate());
                    labelNumZmluva.setText(z.getNumZmluva());
                    labelExpiration.setText(z.getDateOfExpiration().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    labelCreationDate.setText(z.getDateOfCreation().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    textName.setText(z.getVlastnikName());
                    textLicencePlate.setText(z.getLicencePlate());
                    labelKategoriaVozidla.setText(z.getKategoriaVozidla());
                    labelTypVozidla.setText(z.getTypVozidla());
                    labelStav.setText(z.getStav());
                    //idBalikyAll = z.getBalikyId();
                    String balikyIdAll = getBaliky(z.getBalikyId());


                    //System.out.println(returnBaliky);



                    String balikyAll = getBalikyAll(z.getZmluvaId());
                    //System.out.println(balikyAll);

                    /*for(int b = 1; b <= 8; b++) {
                    }*/

                    /*getBalikInfo(returnBaliky, 1, 0);
                    getBalikInfo(returnBaliky, 1, 1);
                    getBalikInfo(returnBaliky, 1, 2);
                    getBalikInfo(returnBaliky, 1, 3);*/
                    /*if (getBalikInfo(returnBaliky, 1, 1).contains("true")) {
                        CheckBox1.setSelected(true);
                    } else {
                        CheckBox1.setSelected(false);
                    }*/
                    if(getBalikInfo(balikyAll, balikyIdAll, 1, 0).contains("true")) {
                        CheckBox1.setSelected(true);
                        sum += Double.parseDouble(getBalikInfo(balikyAll, balikyIdAll, 1, 3));
                    } else CheckBox1.setSelected(false);
                    CheckBox1.setText(getBalikInfo(balikyAll, balikyIdAll, 1, 1));
                    CheckBox1_popis.setText(getBalikInfo(balikyAll, balikyIdAll, 1, 2));
                    CheckBox1_cena.setText(getBalikInfo(balikyAll, balikyIdAll, 1, 3) + " €");

                    if(getBalikInfo(balikyAll, balikyIdAll, 2, 0).contains("true")) {
                        CheckBox2.setSelected(true);
                        sum += Double.parseDouble(getBalikInfo(balikyAll, balikyIdAll, 2, 3));
                    } else CheckBox2.setSelected(false);
                    CheckBox2.setText(getBalikInfo(balikyAll, balikyIdAll, 2, 1));
                    CheckBox2_popis.setText(getBalikInfo(balikyAll, balikyIdAll, 2, 2));
                    CheckBox2_cena.setText(getBalikInfo(balikyAll, balikyIdAll, 2, 3) + " €");

                    if(getBalikInfo(balikyAll, balikyIdAll, 3, 0).contains("true")) {
                        CheckBox3.setSelected(true);
                        sum += Double.parseDouble(getBalikInfo(balikyAll, balikyIdAll, 3, 3));
                    } else CheckBox3.setSelected(false);
                    CheckBox3.setText(getBalikInfo(balikyAll, balikyIdAll, 3, 1));
                    CheckBox3_popis.setText(getBalikInfo(balikyAll, balikyIdAll, 3, 2));
                    CheckBox3_cena.setText(getBalikInfo(balikyAll, balikyIdAll, 3, 3) + " €");

                    if(getBalikInfo(balikyAll, balikyIdAll, 4, 0).contains("true")) {
                        CheckBox4.setSelected(true);
                        sum += Double.parseDouble(getBalikInfo(balikyAll, balikyIdAll, 4, 3));
                    } else CheckBox4.setSelected(false);
                    CheckBox4.setText(getBalikInfo(balikyAll, balikyIdAll, 4, 1));
                    CheckBox4_popis.setText(getBalikInfo(balikyAll, balikyIdAll, 4, 2));
                    CheckBox4_cena.setText(getBalikInfo(balikyAll, balikyIdAll, 4, 3) + " €");

                    if(getBalikInfo(balikyAll, balikyIdAll, 5, 0).contains("true")) {
                        CheckBox5.setSelected(true);
                        sum += Double.parseDouble(getBalikInfo(balikyAll, balikyIdAll, 5, 3));
                    } else CheckBox5.setSelected(false);
                    CheckBox5.setText(getBalikInfo(balikyAll, balikyIdAll, 5, 1));
                    CheckBox5_popis.setText(getBalikInfo(balikyAll, balikyIdAll, 5, 2));
                    CheckBox5_cena.setText(getBalikInfo(balikyAll, balikyIdAll, 5, 3) + " €");

                    if(getBalikInfo(balikyAll, balikyIdAll, 6, 0).contains("true")) {
                        CheckBox6.setSelected(true);
                        sum += Double.parseDouble(getBalikInfo(balikyAll, balikyIdAll, 6, 3));
                    } else CheckBox6.setSelected(false);
                    CheckBox6.setText(getBalikInfo(balikyAll, balikyIdAll, 6, 1));
                    CheckBox6_popis.setText(getBalikInfo(balikyAll, balikyIdAll, 6, 2));
                    CheckBox6_cena.setText(getBalikInfo(balikyAll, balikyIdAll, 6, 3) + " €");

                    if(getBalikInfo(balikyAll, balikyIdAll, 7, 0).contains("true")) {
                        CheckBox7.setSelected(true);
                        sum += Double.parseDouble(getBalikInfo(balikyAll, balikyIdAll, 7, 3));
                    } else CheckBox7.setSelected(false);
                    CheckBox7.setText(getBalikInfo(balikyAll, balikyIdAll, 7, 1));
                    CheckBox7_popis.setText(getBalikInfo(balikyAll, balikyIdAll, 7, 2));
                    CheckBox7_cena.setText(getBalikInfo(balikyAll, balikyIdAll, 7, 3) + " €");

                    if(getBalikInfo(balikyAll, balikyIdAll, 8, 0).contains("true")) {
                        CheckBox8.setSelected(true);
                        sum += Double.parseDouble(getBalikInfo(balikyAll, balikyIdAll, 8, 3));
                    } else CheckBox8.setSelected(false);
                    CheckBox8.setText(getBalikInfo(balikyAll, balikyIdAll, 8, 1));
                    CheckBox8_popis.setText(getBalikInfo(balikyAll, balikyIdAll, 8, 2));
                    CheckBox8_cena.setText(getBalikInfo(balikyAll, balikyIdAll, 8, 3) + " €");


                    labelCena.setText(String.valueOf(sum) + " €");
                    //labelTypVozidla.setText(z.get);
                    //labelKategoriaVozidla;
                    //labelCreationDate.setText(z.getDateOfCreation().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    //labelExpiration.setText(z.getDateOfExpiration().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    //labelCena;
                    //labelStav;
                    buttonEdit.setEnabled(true);
                } else {
                    buttonEdit.setEnabled(false);
                }
            }
        });

    }

    public void refreshZmluvyList() {
        listZmluvyModel.removeAllElements();
        System.out.println("Refresh...");
        for (Zmluva z : zmluvy) {
            System.out.println("Adding " + z.getNumZmluva());
            listZmluvyModel.addElement(z.getNumZmluva());
        }

    }

    public void addZmluva(Zmluva z) {
        zmluvy.add(z);
        this.refreshZmluvyList();
    }

    public void populate(int customerID) {
        String returnZmluvy = getZmluvy(customerID);

        //String zmluvaString = "";

        Pattern patternCisloZmluvy = Pattern.compile("<cislo_zmluvy>(.*?)</cislo_zmluvy>", Pattern.DOTALL);
        Pattern patternAllZmluva = Pattern.compile("<zmluva>(.*?)</zmluva>", Pattern.DOTALL);

        Matcher m_cisloZmluvy = patternCisloZmluvy.matcher(returnZmluvy);
        Matcher m_allZmluva = patternAllZmluva.matcher(returnZmluvy);

        while (m_cisloZmluvy.find()) {
            m_allZmluva.find();
            this.addZmluva(new Zmluva(m_cisloZmluvy.group(1),m_allZmluva.group(1)));
        }

    }





    /*################################ WEB SERVICES ################################*/
    StringBuffer response = new StringBuffer();
    public String getZmluvy(int id)
    {
        //String word = "<email>";
        //int count = 0, fromIndex = 0;;
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Zmluva";

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072zmluva/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getByAttributeValue>\n" +
                "         <attribute_name>poistenec_id</attribute_name>\n" +
                "         <attribute_value>" + id + "</attribute_value>\n" +
                "         <ids>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <id>"+"</id>\n" +
                "         </ids>\n" +
                "      </typ:getByAttributeValue>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);
        //System.out.println(xml);

        String strToReturn = response.toString();
        response.delete(0,response.length());
        //System.out.println(strToReturn);
        return strToReturn;
    }

    public String getBaliky(String id)
    {
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Baliky";

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072baliky/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getByAttributeValue>\n" +
                "         <attribute_name>id</attribute_name>\n" +
                "         <attribute_value>" + id + "</attribute_value>\n" +
                "         <ids>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <id>"+"</id>\n" +
                "         </ids>\n" +
                "      </typ:getByAttributeValue>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);
        //System.out.println(xml);

        String strToReturn = response.toString();
        response.delete(0,response.length());
        //System.out.println(strToReturn);
        return strToReturn;
    }

    public String getBalik(String id)
    {
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Balik";

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072balik/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getByAttributeValue>\n" +
                "         <attribute_name>id</attribute_name>\n" +
                "         <attribute_value>" + id + "</attribute_value>\n" +
                "         <ids>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <id>"+"</id>\n" +
                "         </ids>\n" +
                "      </typ:getByAttributeValue>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);
        //System.out.println(xml);

        String strToReturn = response.toString();
        response.delete(0,response.length());
        //System.out.println(strToReturn);
        return strToReturn;
    }

    public String getBalikyAll(String id)
    {
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Balik";

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072balik/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getByAttributeValue>\n" +
                "         <attribute_name>zmluva_id</attribute_name>\n" +
                "         <attribute_value>" + id + "</attribute_value>\n" +
                "         <ids>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <id>"+"</id>\n" +
                "         </ids>\n" +
                "      </typ:getByAttributeValue>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);
        //System.out.println(xml);

        String strToReturn = response.toString();
        response.delete(0,response.length());
        //System.out.println(strToReturn);
        return strToReturn;
    }

    public String getBalikInfo(String allBaliky, String allBalikyId, int num, int flag) {

        //System.out.println(allZmluva);
        Pattern patternBalikyId = Pattern.compile("<id_balik_" + num + ">(.*?)</id_balik_" + num + ">", Pattern.DOTALL);
        Matcher m_balikyId = patternBalikyId.matcher(allBalikyId);

        String balikId;
        if (m_balikyId.find()) {
            balikId = m_balikyId.group(1);
        } else {
            balikId = "Error.";
        }

        Pattern patternBalikInfo = Pattern.compile("<balik><id>" + balikId + "(.*?)</balik>", Pattern.DOTALL);
        Matcher m_balikyInfo = patternBalikInfo.matcher(allBaliky);
        String balikInfo;
        if (m_balikyInfo.find()) {
            balikInfo = m_balikyInfo.group(1);
        } else {
            balikInfo = "Error.";
        }

        //String balikReturn = getBalik(balikId);
        String balikReturn = balikInfo;

        if (flag == 0) { //stav

            Pattern patternStav = Pattern.compile("<stav>(.*?)</stav>", Pattern.DOTALL);
            Matcher m_stav = patternStav.matcher(balikReturn);

            String stav;
            if (m_stav.find()) {
                stav = m_stav.group(1);
            } else {
                stav = "Error.";
            }
            //System.out.println(stav);
            return stav;

        } else if (flag == 1) { //name
            Pattern patternName = Pattern.compile("<name>(.*?)</name>", Pattern.DOTALL);
            Matcher m_name = patternName.matcher(balikReturn);

            String name;
            if (m_name.find()) {
                name = m_name.group(1);
            } else {
                name = "Error.";
            }
            //System.out.println(name);
            return name;

        } else if (flag == 2) { //popis
            Pattern patternPopis = Pattern.compile("<popis>(.*?)</popis>", Pattern.DOTALL);
            Matcher m_popis = patternPopis.matcher(balikReturn);

            String popis;
            if (m_popis.find()) {
                popis = m_popis.group(1);
            } else {
                popis = "Error.";
            }
            //System.out.println(popis);
            return popis;

        } else if (flag == 3) { //cena
            Pattern patternCena = Pattern.compile("<cena>(.*?)</cena>", Pattern.DOTALL);
            Matcher m_cena = patternCena.matcher(balikReturn);

            String cena;
            if (m_cena.find()) {
                cena = m_cena.group(1);
            } else {
                cena = "Error.";
            }
            //System.out.println(cena);
            return cena;

        }



        //System.out.println(balikReturn);
        return balikReturn;
    }

    public void SendRequest(String url, String xml)
    {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            //System.out.println(responseStatus + " (ReqFromZmluva)");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //System.out.println("response:" + response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        //System.out.println("________________");
    }

    public String createRequest(String url, String xml)
    {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
            con.setDoOutput(true);
            //System.out.println("HERE1");
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
            //System.out.println("HERE2");
            writer.write(xml);
            writer.flush();
            writer.close();
            //System.out.println("HERE3");
            String responseStatus = con.getResponseMessage();
            //System.out.println(responseStatus);
            //System.out.println("HERE4");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            //System.out.println("HERE5");
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //System.out.println("response:" + response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println(e);
            return "Exception";
        }
    }

}
