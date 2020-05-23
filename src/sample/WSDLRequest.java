package sample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WSDLRequest
{
    StringBuffer response = new StringBuffer();
    public String GetUserByEmail(String email)
    {
        String word = "<email>";
        int count = 0, fromIndex = 0;;
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Customer";

        /*String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072customer/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getAll/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        SendRequest(url, xml);
        while ((fromIndex = response.toString().indexOf(word, fromIndex)) != -1 )
        {
            count++;
            fromIndex++;
        }
        response.delete(0, response.length());*/
        for(int id = 35; id < 42; id++)
        {
            if(id == 41)
            {
                id = 2642;
            }
            String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072customer/types\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <typ:getByAttributeValue>\n" +
                    "         <attribute_name>email</attribute_name>\n" +
                    "         <attribute_value>" + email + "</attribute_value>\n" +
                    "         <ids>\n" +
                    "            <!--Zero or more repetitions:-->\n" +
                    "            <id>" + id + "</id>\n" +
                    "         </ids>\n" +
                    "      </typ:getByAttributeValue>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            SendRequest(url, xml);
            if(response.length() < 350)
            {
                response.delete(0,response.length());
            }
            else
            {
                break;
            }
        }

        String strToReturn = response.toString();
        response.delete(0,response.length());
        return strToReturn;
    }

    public String getUserById(int id){
        System.out.println(id);
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Customer";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072customer/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getById>\n" +
                "         <id>" + id + "</id>\n" +
                "      </typ:getById>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        System.out.println("Tu som");
        SendRequest(url, xml);
        String strToReturn = response.toString();
        response.delete(0,response.length());
        return strToReturn;
    }

    public String getZmluvy(){
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Zmluva";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072zmluva/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getAll>\n" +

                "      </typ:getAll>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);

        String strToReturn = response.toString();
        response.delete(0,response.length());
        return strToReturn;

    }

    public String getZmluvaByID(String cislo_zmluvy){
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Zmluva";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072zmluva/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getByAttributeValue>\n" +
                "         <attribute_name>cislo_zmluvy</attribute_name>\n" +
                "         <attribute_value>" + cislo_zmluvy + "</attribute_value>\n" +
                "         <ids>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "         </ids>\n" +
                "      </typ:getByAttributeValue>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);

        String strToReturn = response.toString();
        response.delete(0,response.length());
        return strToReturn;
    }

    public String getBalikyByZmluva(int id_zmluvy){
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Balik";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072balik/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:getByAttributeValue>\n" +
                "         <attribute_name>zmluva_id</attribute_name>\n" +
                "         <attribute_value>" + id_zmluvy + "</attribute_value>\n" +
                "         <ids>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "         </ids>\n" +
                "      </typ:getByAttributeValue>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);

        String strToReturn = response.toString();
        response.delete(0,response.length());
        return strToReturn;
    }


    public void UpdatePassword(int id, String name, String email, String password)
    {

        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Students/Team072Customer";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/students/team072customer/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:update>\n" +
                "         <team_id>072</team_id>\n" +
                "         <team_password>ZXKMZ6</team_password>\n" +
                "         <entity_id>" + String.valueOf(id) + "</entity_id>\n" +
                "         <Customer>\n" +
                "            <id>" + String.valueOf(id) + "</id>\n" +
                "            <name>" + name + "</name>\n" +
                "            <email>" + email + "</email>\n" +
                "            <password>" + password + "</password>\n" +
                "         </Customer>\n" +
                "      </typ:update>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);
    }

    public void SendEmail(String email, String newPassword)
    {
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/NotificationServices/Email";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/notificationservices/email/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:notify>\n" +
                "         <team_id>072</team_id>\n" +
                "         <password>ZXKMZ6</password>\n" +
                "         <email>" + email + "</email>\n" +
                "         <subject>Password reset</subject>\n" +
                "         <message>Your new password: " + newPassword + "</message>\n" +
                "      </typ:notify>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        SendRequest(url, xml);
    }

    public void SendEmail(String cislo_zmluvy, String message, String email_poistenca){
        System.out.println(email_poistenca + message);
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/NotificationServices/Email";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/notificationservices/email/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:notify>\n" +
                "         <team_id>072</team_id>\n" +
                "         <password>ZXKMZ6</password>\n" +
                "         <email>" + email_poistenca + "</email>\n" +
                "         <subject>Posudok</subject>\n" +
                "         <message>Oznam: " + message + "</message>\n" +
                "      </typ:notify>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        SendRequest(url, xml);
    }

    public void SendSMS(String message, String phone_number){

        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/NotificationServices/SMS";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/notificationservices/sms/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:notify>\n" +
                "         <team_id>072</team_id>\n" +
                "         <password>ZXKMZ6</password>\n" +
                "         <phone>" + phone_number    + "</phone>\n" +
                "         <subject>Posudok</subject>\n" +
                "         <message>Oznam: " + message + "</message>\n" +
                "      </typ:notify>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        SendRequest(url, xml);

    }

    public void SendMail(String adress, String message){
        String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/NotificationServices/Mail";
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/notificationservices/mail/types\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <typ:notify>\n" +
                "         <team_id>072</team_id>\n" +
                "         <password>ZXKMZ6</password>\n" +
                "         <address>" + adress + "</address>\n" +
                "         <subject>Posudok</subject>\n" +
                "         <message>Oznam: " + message + "</message>\n" +
                "      </typ:notify>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        SendRequest(url, xml);
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
