package sample;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class PosudokController {

    @FXML
    TableView table;

    TableColumn zmluvy = new TableColumn("Zmluvy");

    WSDLRequest wsdlRequest = new WSDLRequest();
    String baliky = "";
    String zmluva = "";
    NavrhZmluvy highlight;

    public PosudokController() throws ParserConfigurationException, IOException, SAXException {
    }

    public void loadData() throws ParserConfigurationException, IOException, SAXException {

        String info = wsdlRequest.getZmluvy();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(info)));
        Element element = document.getDocumentElement();
        ArrayList<String> cisla = getString("cislo_zmluvy",element);

        zmluvy.setCellValueFactory(new PropertyValueFactory<>("cislo_zmluvy"));
        table.getColumns().addAll(zmluvy);
        for(int i = 0; i < cisla.size(); i++){
            NavrhZmluvy navrhZmluvy = new NavrhZmluvy(cisla.get(i));
            table.getItems().add(navrhZmluvy);
        }

    }

    public void confirm(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirm.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Potvrdenie");
            Confirm confirm = loader.getController();
            confirm.setZmluva(zmluva);
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void modify(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modify.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Uprava");
            Modify modify = loader.getController();
            modify.setZmluva(zmluva);
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            System.out.println(e);
        }

    }
    public void refuse(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Refuse.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Zamietnutie");
            Refuse refuse = loader.getController();
            refuse.setZmluva(zmluva);
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            System.out.println(e);
        }

    }

    protected ArrayList<String> getString(String tagName, Element element) {

        ArrayList<String> id_zmluv = new ArrayList<String>();
        NodeList list = element.getElementsByTagName(tagName);

       for (int i = 0; i < list.getLength(); i++) {
            NodeList subList = list.item(i).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                System.out.println(subList.item(0).getNodeValue());
                id_zmluv.add(subList.item(0).getNodeValue());
            }

        }

        return id_zmluv;
    }


    public void ShowZmluva(MouseEvent mouseEvent) throws IOException, SAXException, ParserConfigurationException {

        highlight = (NavrhZmluvy) table.getSelectionModel().getSelectedItem();
        System.out.println(highlight.getCislo_zmluvy());

        zmluva = wsdlRequest.getZmluvaByID(highlight.getCislo_zmluvy());


        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(zmluva)));
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName("id");
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList sublist = nodeList.item(i).getChildNodes();

            baliky = wsdlRequest.getBalikyByZmluva(Integer.parseInt(sublist.item(0).getNodeValue()));

        }


    }
}
