package DataBase;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Server.Server;
import User.UserProxy;
import org.w3c.dom.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Logger.*;
import User.IUser;

public class XMLDataBase {

    private static final XMLDataBase instance = new XMLDataBase();

    private XMLDataBase()
    { }

    public static XMLDataBase getInstance()
    {
        return instance;
    }

    public List<IUser> LoadAllUsers()
    {
        List<IUser> userList = new ArrayList<>();
        File[] directories = new File("Users").listFiles(File::isDirectory);

        assert directories != null;
        for (File directory : directories)
        {
            String userIndex = directory.getName();

            try
            {
                int userIndexInt = Integer.parseInt(userIndex);

                IUser userToLoad = LoadUser(userIndexInt);
                if (userToLoad != null)
                {
                    userList.add(userToLoad);
                }
            }
            catch(Exception ignore)
            {
            }
        }
        return userList;
    }

    public IUser LoadUser(int index)
    {
        try
        {
            // Create XML reader.
            String fullPath = "Users\\" + index + "\\user.xml";
            File fXmlFile = new File(fullPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parse XML and fine User node.
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            Node userNode = doc.getDocumentElement();
            Element eElement = (Element) userNode;

            // Get user info from user.xml .
            short id = Short.parseShort(eElement.getAttribute("id"));
            short age = Short.parseShort(eElement.getElementsByTagName("Age").item(0).getTextContent());
            String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
            String shortDescription = eElement.getElementsByTagName("ShortDescription").item(0).getTextContent();
            String longDescription = eElement.getElementsByTagName("LongDescription").item(0).getTextContent();

            // Convert Strings to UserType and UserSex
            IUser.UserType userType = IUser.StringToUserType(eElement.getElementsByTagName("UserType").item(0).getTextContent());
            IUser.UserSex userSex = IUser.StringToUserSex(eElement.getElementsByTagName("Sex").item(0).getTextContent());
            IUser.UserSex interestedIn = IUser.StringToUserSex(eElement.getElementsByTagName("InterestedIn").item(0).getTextContent());

            Logger.getInstance().
                    WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.DATABASE, String.format("User %d was loaded", index));
            return new UserProxy(id, age, name, shortDescription, longDescription, userType, userSex, interestedIn);
        }
        catch (Exception e)
        {
            Logger.getInstance().
                    WriteToLog(ILogger.LogLevel.WARNING, ILogger.LogSubject.DATABASE, String.format("User %d could not be loaded", index));
            return null;
        }
    }

    public boolean SaveUser(IUser userToSave)
    {
        String fullPath = "Users\\" + userToSave.getId() + "\\user.xml";
        boolean savingSucceeded = false;

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element userElement = document.createElement("User");
            document.appendChild(userElement);

            Attr idAttr = document.createAttribute("id");
            idAttr.setValue(Short.toString(userToSave.getId()));
            userElement.setAttributeNode(idAttr);

            Element ageElement = document.createElement("Age");
            ageElement.appendChild(document.createTextNode(Short.toString(userToSave.getAge())));
            userElement.appendChild(ageElement);

            Element nameElement = document.createElement("Name");
            nameElement.appendChild(document.createTextNode(userToSave.getName()));
            userElement.appendChild(nameElement);

            Element shortDescriptionElement = document.createElement("ShortDescription");
            shortDescriptionElement.appendChild(document.createTextNode(userToSave.getShortDescription()));
            userElement.appendChild(shortDescriptionElement);

            Element longDescriptionElement = document.createElement("LongDescription");
            longDescriptionElement.appendChild(document.createTextNode(userToSave.getLongDescription()));
            userElement.appendChild(longDescriptionElement);

            Element userTypeElement = document.createElement("UserType");
            userTypeElement.appendChild(document.createTextNode(String.format("%s", userToSave.getUserType())));
            userElement.appendChild(userTypeElement);

            Element userSexElement = document.createElement("Sex");
            userSexElement.appendChild(document.createTextNode(String.format("%s", userToSave.getUserSex())));
            userElement.appendChild(userSexElement);

            Element userInterestedInElement = document.createElement("InterestedIn");
            userInterestedInElement.appendChild(document.createTextNode(String.format("%s", userToSave.getInterestedIn())));
            userElement.appendChild(userInterestedInElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            File saveFile = new File(fullPath);
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
            StreamResult streamResult = new StreamResult(saveFile);
            transformer.transform(domSource, streamResult);

            Logger.getInstance().
                    WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.DATABASE, String.format("User %d was saved", userToSave.getId()));

            savingSucceeded = true;

        } catch (ParserConfigurationException | TransformerException | IOException pce) {
            Logger.getInstance().
                    WriteToLog(ILogger.LogLevel.WARNING, ILogger.LogSubject.DATABASE, String.format("User %d could not be saved", userToSave.getId()));
        }

        return savingSucceeded;
    }

    public short GetNextID()
    {
        short maxId = -1;
        File folder = new File("Users");
        File[] files = folder.listFiles();
        if (files == null)
        {
            return maxId;
        }

        for (File file : files)
        {
            if (file.isDirectory())
            {
                try
                {
                    short id = Short.parseShort(file.getName());
                    if (maxId < id)
                    {
                        maxId = id;
                    }
                }
                catch (Exception ignore) {}
            }
        }

        return (short)(maxId + 1);
    }

    public static void main(String [] args)
    {
        XMLDataBase db = new XMLDataBase();
        short id = db.GetNextID();
    }
}
