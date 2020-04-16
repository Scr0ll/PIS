package sample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WSDLRequest
{
    public String GetUserInfo(String email)
    {
        StringBuffer response = new StringBuffer();
        try {
            String url = "http://pis.predmety.fiit.stuba.sk/pis/ws/Customer?WSDL";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
            String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://pis.predmety.fiit.stuba.sk/pis/customer/types\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <typ:getCustomerByEmail>\n" +
                    "         <email>" + email + "</email>\n" +
                    "      </typ:getCustomerByEmail>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
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
            System.out.println("response:" + response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return response.toString();
    }
}
