// Name: Shashank
// Reg. No: 230970004
// Section: A

package quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Score extends JFrame {
    // MongoDB variables
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    Score(String uname, int score,int total) {
        // Initialize JFrame properties
        setLayout(null);
        setBackground(new Color(0, 0, 0));
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add GUI components
        JLabel lblThank = new JLabel("Thank you " + uname + "!");
        lblThank.setBounds(90, 30, 300, 40);
        lblThank.setFont(new Font("COMIC SANS MS", Font.BOLD, 30));
        lblThank.setForeground(new Color(0, 0, 100));
        add(lblThank);
        
        //display score
        JLabel lblScore = new JLabel("Score :" + score+"/"+total);
        lblScore.setBounds(170, 80, 300, 40);
        lblScore.setFont(new Font("", Font.ROMAN_BASELINE, 24));
        lblScore.setForeground(new Color(5, 7, 62));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(lblScore);

        //display background image
        ImageIcon imgcon = new ImageIcon(ClassLoader.getSystemResource("tick.png"));
        imgcon = new ImageIcon(imgcon.getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH));
        JLabel img = new JLabel(imgcon);
        img.setOpaque(false);
        add(img);
        img.setBounds(90, 120, 250, 150);

        setVisible(true);

        // Store the user's score in the database
        storeScore(uname, score);
    }

    void storeScore(String uname, int score) {
        try {
            //connect to database
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("quizdb");
            collection = database.getCollection("users");

            // Define filter and update documents
            Document filter = new Document("uname", uname);
            Document update = new Document("$set", new Document("score", score));

            // Update the user's score in the database
            collection.updateOne(filter, update);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            if (mongoClient != null) {
                // Close the MongoDB client
                mongoClient.close();
            }
        }
    }

    public static void main(String[] args) {
        new Score("uname", 0,5);
    }
}