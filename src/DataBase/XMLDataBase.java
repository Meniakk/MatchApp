package DataBase;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import User.UserProxy;
import org.w3c.dom.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Logger.*;
import User.IUser;
import User.RealUser;

public class XMLDataBase implements IDataBase {
    private String m_pathToUsersDir;

    public XMLDataBase(String pathToUsersDir)
    {
        this.m_pathToUsersDir = pathToUsersDir;
        Logger.getInstance().
                WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.DATABASE, "Users dir is " + pathToUsersDir);
    }

    public String getPathToUsersDir()
    {
        return m_pathToUsersDir;
    }

    public void setPathToUsersDir(String pathToUsersDir)
    {
        this.m_pathToUsersDir = pathToUsersDir;
        Logger.getInstance().
                WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.DATABASE, "New users dir is " + pathToUsersDir);

    }

    @Override
    public List<IUser> LoadAllUsers()
    {
        List<IUser> userList = new ArrayList<>();
        File[] directories = new File(m_pathToUsersDir).listFiles(File::isDirectory);

        assert directories != null;
        for (File directory : directories)
        {
            String userIndex = directory.getName();

            try
            {
                int userIndexInt = Integer.parseInt(userIndex);
                userList.add(LoadUser(userIndexInt));
            }
            catch (Exception ignore)
            { }
        }
        return userList;
    }

    @Override
    public IUser LoadUser(int index)
    {
        try
        {
            // Create XML reader.
            String fullPath = m_pathToUsersDir + "\\" + Integer.toString(index) + "\\user.xml";
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

            // Convert String to userType
            IUser.UserType userType = IUser.StringToUserType(eElement.getElementsByTagName("UserType").item(0).getTextContent());

            Logger.getInstance().
                    WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.DATABASE, String.format("User %d was loaded", index));
            return new UserProxy(id, age, name, shortDescription, longDescription, userType);
        }
        catch (Exception e)
        {
            Logger.getInstance().
                    WriteToLog(ILogger.LogLevel.WARNING, ILogger.LogSubject.DATABASE, String.format("User %d could not be loaded", index));
            return null;
        }
    }

    @Override
    public boolean SaveUser(IUser userToSave)
    {
        String fullPath = m_pathToUsersDir + "\\" + Short.toString(userToSave.getId()) + "\\user.xml";
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

    public static void main(String [] args)
    {
        XMLDataBase db = new XMLDataBase("C:\\Users\\aviad\\IdeaProjects\\MatchApp\\Users");
        for (IUser user : db.LoadAllUsers())
        {
            System.out.println(user);
        }

        ILogger logger = Logger.getInstance();
        logger.WriteToLog(ILogger.LogLevel.INFO, ILogger.LogSubject.DATABASE, "second");

        //IUser user = db.LoadUser(0);
        //user.setAge((short) 666);
        //db.SaveUser(user);
        //System.out.println(user);
    }
}
