package sample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zmluva {
    private String numZmluva;
    private String allZmluva;

    private String name;
    private String zmluvaId;
    private String nameZmluva;
    private String licencePlate;
    private LocalDate dateOfCreation;
    private LocalDate dateOfExpiration;

    private int sposobPlatby;
    private String balikyId;
    private String kategoriaVozidla;
    private String stav;
    private String typVozidla;
    private String vlastnikName;
    private String cena;


    /*private LocalDate dateOfCreation;
    private LocalDate dateOfExpiration;*/


    public Zmluva(String numZmluva, String allZmluva) {
        this.allZmluva = allZmluva;
        this.numZmluva = numZmluva;
        this.name = setName(allZmluva);

        setDateOfCreation(allZmluva);
        setDateOfExpiration(allZmluva);
        setVlastnikName(allZmluva);
        setTypVozidla(allZmluva);
        setKategoriaVozidla(allZmluva);
        setLicencePlate(allZmluva);
        setStav(allZmluva);
        setNameZmluva(allZmluva);
        setBalikyId(allZmluva);
        setZmluvaId(allZmluva);
       /* this.setDateOfCreation(dateOfCreation);
        this.setDateOfExpiration(dateOfExpiration);*/
        /*this.dateOfCreation = dateOfCreation;
        this.dateOfExpiration = dateOfExpiration;*/
    }

    /*public Zmluva(String name, String licencePlate, String numZmluva, String dateOfCreation, String dateOfExpiration) {
        this.name = name;
        this.licencePlate = licencePlate;
        this.numZmluva = numZmluva;
        this.setDateOfCreation(dateOfCreation);
        this.setDateOfExpiration(dateOfExpiration);
    }*/

    public String geAllZmluva() {
        return allZmluva;
    }

    public void setAllZmluva(String allZmluva) {
        this.allZmluva = allZmluva;
    }

    public String getName() {
        return name;
    }

    public String setName(String allZmluva) {
        String getCustomer = getCustomer(allZmluva);

        Pattern patternFullName = Pattern.compile("<name>(.*?)</name>", Pattern.DOTALL);
        Matcher m_fullName = patternFullName.matcher(getCustomer);

        if (m_fullName.find()) {
            return m_fullName.group(1);
        } else {
            return "Error.";
        }
    }

    public String getNameZmluva() {
        return nameZmluva;
    }

    public void setNameZmluva(String allZmluva) {

        Pattern patternNameZmluva = Pattern.compile("<name>(.*?)</name>", Pattern.DOTALL);
        Matcher m_nameZmluva = patternNameZmluva.matcher(allZmluva);

        String nameZmluva;
        if (m_nameZmluva.find()) {
            nameZmluva = m_nameZmluva.group(1);
        } else {
            nameZmluva = "error";
        }

        this.nameZmluva = nameZmluva;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String allZmluva) {

        Pattern patternLicencePlate = Pattern.compile("<evc>(.*?)</evc>", Pattern.DOTALL);
        Matcher m_licencePlate = patternLicencePlate.matcher(allZmluva);

        String licencePlate;
        if (m_licencePlate.find()) {
            licencePlate = m_licencePlate.group(1);
        } else {
            licencePlate = "error";
        }

        this.licencePlate = licencePlate;
    }

    public String getNumZmluva() {
        return numZmluva;
    }

    public void setNumZmluva(String numZmluva) {
        this.numZmluva = numZmluva;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(LocalDate dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public void setDateOfExpiration(String allZmluva) {

        Pattern patternDateOfExpiration = Pattern.compile("<platnost_do>(.*?)</platnost_do>", Pattern.DOTALL);
        Matcher m_dateOfExpiration = patternDateOfExpiration.matcher(allZmluva);

        String dateOfExpiration;
        if (m_dateOfExpiration.find()) {
            dateOfExpiration = m_dateOfExpiration.group(1);
        } else {
            dateOfExpiration = "error";
        }

        this.dateOfExpiration = LocalDate.parse(dateOfExpiration, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setDateOfCreation(String allZmluva) {

        Pattern patternDateOfCreation = Pattern.compile("<platnost_od>(.*?)</platnost_od>", Pattern.DOTALL);
        Matcher m_dateOfCreation = patternDateOfCreation.matcher(allZmluva);

        String dateOfCreation;
        if (m_dateOfCreation.find()) {
            dateOfCreation = m_dateOfCreation.group(1);
        } else {
            dateOfCreation = "error";
        }

        this.dateOfCreation = LocalDate.parse(dateOfCreation, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public int getSposobPlatby() {
        return sposobPlatby;
    }

    public void setSposobPlatby(int sposobPlatby) {
        this.sposobPlatby = sposobPlatby;
    }

    public String getKategoriaVozidla() {
        return kategoriaVozidla;
    }

    public void setKategoriaVozidla(String allZmluva) {

        Pattern patternKategoriaVozidla = Pattern.compile("<kategoria_vozidla>(.*?)</kategoria_vozidla>", Pattern.DOTALL);
        Matcher m_kategoriaVozidla = patternKategoriaVozidla.matcher(allZmluva);

        String kategoriaVozidla;
        if (m_kategoriaVozidla.find()) {
            kategoriaVozidla = m_kategoriaVozidla.group(1);
        } else {
            kategoriaVozidla = "error";
        }

        this.kategoriaVozidla = kategoriaVozidla;
    }

    public String getStav() {
        return stav;
    }

    public void setStav(String allZmluva) {

        Pattern patternStav = Pattern.compile("<stav>(.*?)</stav>", Pattern.DOTALL);
        Matcher m_stav = patternStav.matcher(allZmluva);

        String stav;
        if (m_stav.find()) {
            stav = m_stav.group(1);
        } else {
            stav = "error";
        }

        this.stav = stav;
    }

    public String getTypVozidla() {
        return typVozidla;
    }

    public void setTypVozidla(String allZmluva) {

        Pattern patternTypVozidla = Pattern.compile("<typ_vozidla>(.*?)</typ_vozidla>", Pattern.DOTALL);
        Matcher m_typVozidla = patternTypVozidla.matcher(allZmluva);

        String typVozidla;
        if (m_typVozidla.find()) {
            typVozidla = m_typVozidla.group(1);
        } else {
            typVozidla = "error";
        }

        this.typVozidla = typVozidla;
    }

    public String getVlastnikName() {
        return vlastnikName;
    }

    public void setVlastnikName(String allZmluva) {

        Pattern patternVlastnikName = Pattern.compile("<vlastnik_vozidla>(.*?)</vlastnik_vozidla>", Pattern.DOTALL);
        Matcher m_vlastnikName = patternVlastnikName.matcher(allZmluva);

        String vlastnikName;
        if (m_vlastnikName.find()) {
            vlastnikName = m_vlastnikName.group(1);
        } else {
            vlastnikName = "error";
        }

        this.vlastnikName = vlastnikName;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getBalikyId() {
        return balikyId;
    }

    public void setBalikyId(String allZmluva) {

        //System.out.println(allZmluva);
        Pattern patternBalikyId = Pattern.compile("<baliky_id>(.*?)</baliky_id>", Pattern.DOTALL);
        Matcher m_balikyId = patternBalikyId.matcher(allZmluva);

        String balikyId;
        if (m_balikyId.find()) {
            balikyId = m_balikyId.group(1);
        } else {
            balikyId = "Error.";
        }
        //System.out.println(balikyId);
        this.balikyId = balikyId;
    }

    public String getZmluvaId() {
        return zmluvaId;
    }

    public void setZmluvaId(String allZmluva) {

        //System.out.println(allZmluva);
        Pattern patternZmluvaId = Pattern.compile("<id>(.*?)</id>", Pattern.DOTALL);
        Matcher m_zmluvaId = patternZmluvaId.matcher(allZmluva);

        String zmluvaId;
        if (m_zmluvaId.find()) {
            zmluvaId = m_zmluvaId.group(1);
        } else {
            zmluvaId = "Error.";
        }
        //System.out.println(balikyId);
        this.zmluvaId = zmluvaId;
    }



    /*################################ WEB SERVICES ################################*/
    StringBuffer response = new StringBuffer();
    public String getCustomer(String allZmluva)
    {

        //System.out.println(allZmluva);
        Pattern patternCustomerID = Pattern.compile("<poistenec_id>(.*?)</poistenec_id>", Pattern.DOTALL);
        Matcher m_customerID = patternCustomerID.matcher(allZmluva);

       // String customerID = m_customerID.group(1);
       // System.out.println(m_customerID.find());
       // String customerID = m_customerID.group(1);
        String stringID;
        try {
            if (m_customerID.find()) {
                stringID = m_customerID.group(1);
                //System.out.println(stringID);
            } else {
                stringID = "0";
            }

            int customerID = Integer.parseInt(stringID);
            //System.out.println(customerID);

            String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Customer";
            String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072customer/types\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <typ:getById>\n" +
                    "         <id>" + customerID + "</id>\n" +
                    "      </typ:getById>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            SendRequest(url, xml);

            String strToReturn = response.toString();
            response.delete(0,response.length());
            //System.out.println(strToReturn);
            return strToReturn;

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return "failed";
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
}
