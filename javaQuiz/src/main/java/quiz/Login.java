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
import com.mongodb.client.model.Filters;
import org.bson.Document;

class Login extends JFrame {
    MongoCollection<Document> usersCollection;
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    Login() {
        try {
            //connection to mongodb
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("quizdb");
            usersCollection = database.getCollection("users"); 
            //retriving instace on collection (mongodb equivalent of table)

        } catch (Exception e) {
            //Handling connection error
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        //creating login GUI
        loginGUI();
    }

    public void loginGUI() {
        //Setting properties for Jframe
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setTitle("MyFrame");
        setSize(380, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creating GUI components
        JLabel lblhead = new JLabel("Login");
        JLabel lbluname = new JLabel("Username :");
        JLabel lblpasswd = new JLabel("Password :");
        JTextField txtuname = new JTextField();
        JTextField txtpwd = new JTextField();
        JButton btnLogin = new JButton("Login");

        lblhead.setBounds(120, 20, 100, 50);
        lbluname.setBounds(50, 110, 100, 30);
        txtuname.setBounds(170, 110, 130, 30);
        lblpasswd.setBounds(50, 150, 100, 30);
        txtpwd.setBounds(170, 150, 130, 30);

        btnLogin.setBounds(190, 220, 110, 30);

        lbluname.setFont(new Font(" ", Font.BOLD, 17));
        lblpasswd.setFont(new Font(" ", Font.BOLD, 17));
        btnLogin.setFont(new Font(" ", Font.BOLD, 17));

        lblhead.setFont(new Font("COMIC SANS MS", Font.BOLD, 35));
        lblhead.setForeground(new Color(0,0,100));
        txtuname.setBackground(Color.lightGray);
        txtpwd.setBackground(Color.lightGray);

        add(lblhead);
        add(lbluname);
        add(txtuname);
        add(lblpasswd);
        add(txtpwd);
        add(btnLogin);

        setVisible(true);

        // Adding error label
        JLabel lblErr = new JLabel("Invalid username or password!");
        lblErr.setBounds(110, 170, 300, 50);
        lblErr.setForeground(Color.red);
        lblErr.setVisible(false);
        add(lblErr);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve username and password from text fields
                String uname = txtuname.getText();
                String password = txtpwd.getText();

                // Retrieve user document from MongoDB based on username
                Document user = usersCollection.find(Filters.eq("uname", uname)).first();
                
                // Check if user exists and password matches
                if (user != null && password.equals(user.getString("password"))) 
                {
                    // Hide login window and open quiz page
                    setVisible(false);
                    new Quiz(uname); //passing username to quiz class
                } 
                else 
                {
                    //display invalid user or password message
                    lblErr.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        new Login();
    }
}