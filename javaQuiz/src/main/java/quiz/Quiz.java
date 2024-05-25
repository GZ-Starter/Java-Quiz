// Name: Shashank
// Reg. No: 230970004
// Section: A

package quiz;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Quiz extends JFrame implements ActionListener {
        // Array to store correct and user answers      
        String correctAns[]=new String[10];
        String userAns[]=new String[10];

        // GUI components
        JLabel lblqno, lblqtn;
        JRadioButton opt1, opt2, opt3, opt4;
        JButton btnNext, btnPrev, btnSubmit;
        ButtonGroup grpoptn;
        int count = 0, total = 0;
        long qtncount;

        String uname;

        // MongoDB variables
        MongoClient mongoClient;
        MongoDatabase database;
        MongoCollection<Document> collection;

        public Quiz(String uname) {
                // Initialize username and MongoDB connection
                this.uname = uname;

                mongoClient = MongoClients.create("mongodb://localhost:27017");   
                database = mongoClient.getDatabase("quizdb"); //connecting to databse
                collection = database.getCollection("questions");
                
                qtncount = collection.countDocuments();

                // Initialize user answers array
                for (int i = 0; i < 10; i++) {
                        userAns[i]="none";
                }
   
                // Set JFrame properties
                getContentPane().setBackground(Color.WHITE);
                setBounds(50, 0, 1000, 650);
                setLayout(null);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //display background image
                ImageIcon imgcon = new ImageIcon(ClassLoader.getSystemResource("quizpage.jpg"));
                JLabel img = new JLabel(imgcon);
                add(img);
                img.setBounds(10, 10, 960, 250);

                //Building GUI for quiz
                lblqno = new JLabel(" ");
                lblqtn = new JLabel(" ");

                opt1 = new JRadioButton(" ");
                opt2 = new JRadioButton(" ");
                opt3 = new JRadioButton(" ");
                opt4 = new JRadioButton(" ");

                //Function to format and place radio buttons
                createOptions(opt1, 330);
                createOptions(opt2, 370);
                createOptions(opt3, 410);
                createOptions(opt4, 450);

                add(opt1);
                add(opt2);
                add(opt3);
                add(opt4);

                lblqno.setBounds(30, 275, 50, 30);
                lblqtn.setBounds(70, 275, 1000, 30);
                lblqno.setFont(new Font("Tahoma", Font.PLAIN, 24));
                lblqtn.setFont(new Font("Tahoma", Font.PLAIN, 24));

                grpoptn = new ButtonGroup();
                grpoptn.add(opt1);
                grpoptn.add(opt2);
                grpoptn.add(opt3);
                grpoptn.add(opt4);

                btnNext = new JButton("Next");
                btnNext.setBounds(750, 400, 150, 40);
                btnNext.setFont(new Font("Tahoma", Font.PLAIN, 22));
                btnNext.setBackground(Color.BLACK);
                btnNext.setForeground(Color.white);

                btnPrev = new JButton("Previous");
                btnPrev.setBounds(750, 450, 150, 40);
                btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 22));
                btnPrev.setBackground(Color.BLACK);
                btnPrev.setForeground(Color.white);

                btnSubmit = new JButton("Submit");
                btnSubmit.setBounds(750, 500, 150, 40);
                btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 22));
                btnSubmit.setBackground(Color.BLACK);
                btnSubmit.setForeground(Color.white);

                btnNext.addActionListener(this);
                btnPrev.addActionListener(this);
                btnSubmit.addActionListener(this);

                add(btnNext);
                add(btnPrev);
                add(btnSubmit);

                add(lblqno);
                add(lblqtn);
                setVisible(true);

                btnPrev.setEnabled(false);
                loadQuestion(count);
        }

        //Method to load a question from the database and display it on the GUI.
        public void loadQuestion(int index) {
                //Filter to retrieve 1 record skipping first n records
                FindIterable<Document> result = collection.find().limit(1).skip(index);
        
                // Display question and options on the GUI
                for (Document doc : result) {
                    lblqno.setText("" + (index + 1) + ".");
                    lblqtn.setText(doc.getString("question"));
        
                    opt1.setText(doc.getString("option1"));
                    opt2.setText(doc.getString("option2"));
                    opt3.setText(doc.getString("option3"));
                    opt4.setText(doc.getString("option4"));

                    opt1.setActionCommand(doc.getString("option1"));
                    opt2.setActionCommand(doc.getString("option2"));                
                    opt3.setActionCommand(doc.getString("option3"));
                    opt4.setActionCommand(doc.getString("option4"));

                    correctAns[index] = doc.getString("answer");
                }
                grpoptn.clearSelection();

                if (count > 0)
                        btnPrev.setEnabled(true);
        }

        public void createOptions(JRadioButton opt, int y) 
        {
                // Format and place the radio button
                opt.setBounds(70, y, 600, 30);
                opt.setBackground(Color.white);
                opt.setFont(new Font("Dialog", Font.PLAIN, 20));
        }

        public void actionPerformed(ActionEvent e) {
                if (grpoptn.getSelection() != null) {
                        //store the selected answer in userAns[]
                        userAns[count] = grpoptn.getSelection().getActionCommand();
                }

                if (e.getSource() == btnNext) {
                        count++;
                        if (count < qtncount-1)
                                loadQuestion(count);
                        else {
                                loadQuestion(count);
                                btnNext.setEnabled(false);
                        }
                }
                else if (e.getSource() == btnPrev) 
                {
                        if (count > 1) {
                                loadQuestion(--count);
                                btnNext.setEnabled(true);
                        } else {
                                loadQuestion(--count);
                                btnPrev.setEnabled(false);
                        }
                } 
                else if (e.getSource() == btnSubmit) 
                {
                        for (int i = 0; i < qtncount; i++) 
                        {
                                //compare users answer with correct answer
                                if (userAns[i].equals(correctAns[i])) {
                                        total++;
                                }
                        }
                        //display score page
                        setVisible(false);
                        //passing username, score and max score to score class
                        new Score(uname, total,(int)qtncount);
                }
        }

        public static void main(String[] args) {
                new Quiz("uname");
        }

}
