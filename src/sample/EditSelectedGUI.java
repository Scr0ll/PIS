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
    private JCheckBox balik1CheckBox;
    private JCheckBox balik2CheckBox;
    private JCheckBox balik3CheckBox;
    private JCheckBox balik4CheckBox;
    private JCheckBox avsdfCheckBox;
    private JCheckBox afwegCheckBox;
    private JCheckBox balik654CheckBox;
    private JCheckBox balik8CheckBox;
    private JComboBox comboBox1;
    private JLabel labelTypVozidla;
    private JLabel labelKategoriaVozidla;
    private JLabel labelCena;
    private JLabel labelStav;
    private JTextField textName;
    static ArrayList<Zmluva> zmluvy;
    private DefaultListModel listZmluvyModel;

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
                            "               <cislo_zmluvy>" + z.getNumZmluva() + "</cislo_zmluvy>\n" +
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
                    System.out.println(xml);
                    createRequest(url, xml);

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

        String zmluvaString = "";

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
        String word = "<email>";
        int count = 0, fromIndex = 0;;
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
        System.out.println(strToReturn);
        return strToReturn;
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
            System.out.println(responseStatus);
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
    }

    public void createRequest(String url, String xml)
    {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
            con.setDoOutput(true);
            System.out.println("HERE1");
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
            System.out.println("HERE2");
            writer.write(xml);
            writer.flush();
            writer.close();
            System.out.println("HERE3");
            String responseStatus = con.getResponseMessage();
            System.out.println(responseStatus);
            System.out.println("HERE4");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            System.out.println("HERE5");
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //System.out.println("response:" + response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
