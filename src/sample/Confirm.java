package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class Confirm {

    @FXML
    Button confirm;
    private String zmluva;
    WSDLRequest wsdlRequest = new WSDLRequest();

    public String getZmluva() {
        return zmluva;
    }

    public void setZmluva(String zmluva) {
        this.zmluva = zmluva;
    }

    public void sendResult() throws IOException, SAXException, ParserConfigurationException {


        int poistenec_id = Integer.parseInt(getSometing(this.zmluva,"poistenec_id"));
        System.out.println(poistenec_id);
        String customer = wsdlRequest.getUserById(poistenec_id);
        String email_customer = getSometing(customer,"email");
        String cislo_zmluvy = getSometing(zmluva,"cislo_zmluvy");
        String message = "Vas navrh zmluvy s cislom "+ cislo_zmluvy +" bol schvaleny a zmluva na podpis Vam pride postou";
        String typ_notifikacie = getSometing(customer, "notification");
        String adresa = getSometing(customer, "adress");
        String phone = getSometing(customer, "phoneNumber");
        String poistenec = getSometing(customer,"name");

        if(typ_notifikacie.equals("email")) {
            wsdlRequest.SendEmail(cislo_zmluvy,message,email_customer);
        }
        else if(typ_notifikacie.equals("phone")){
            wsdlRequest.SendSMS(message, phone);
        }

        wsdlRequest.SendMail(adresa,poistenec);
        Stage stage = (Stage) confirm.getScene().getWindow();

        stage.close();

    }


    public String getSometing(String zmluva, String parameter) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(zmluva)));
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName(parameter);
        String result = "";
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList sublist = nodeList.item(i).getChildNodes();
            result = sublist.item(0).getNodeValue();
        }
        return result;
    }



}
