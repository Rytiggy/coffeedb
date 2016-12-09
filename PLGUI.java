import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // seems to be missing.

import javax.swing.table.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.*;

import java.awt.Desktop;  
import java.io.IOException;  
import java.net.URI;  
import java.net.URISyntaxException;  
/*
* CoffeeDB
* Gustav, Aaron, Ryan, and Jeremy
* PLGUI
*/


public class PLGUI {
  
 
   public static void main(String[] args){
   
      PLActions action = new PLActions();       
      
      JFrame frame = new JFrame("Faculty Research Assistant");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
   
      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
      //mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
     
     
      //NAV
      JPanel navControl = new JPanel();
      JButton uploadButton = new JButton("Upload");
      JButton loginButton = new JButton("Login");
      navControl.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
      navControl.add(uploadButton);
      navControl.add(loginButton);
      mainPanel.add(navControl);
      navControl.setBackground(Color.gray);
   
   
      JPanel title = new JPanel();
      title.setBackground(Color.orange);
      JLabel header = new JLabel("<html><span style='color: white;'>Faculty Research Assistant</span></html>");
      header.setFont(header.getFont().deriveFont(64.0F));
      title.add(header);
      mainPanel.add(title);
      
      
      mainPanel.add(Box.createVerticalStrut(2));
      JPanel info = new JPanel();
      info.setLayout(new FlowLayout(FlowLayout.RIGHT,30,0));
      JLabel teamName = new JLabel("<html><div style='margin-bottom:3px'>By CoffeeDB</div> Version 1.0 </html>");
      info.add(teamName);  
      mainPanel.add(info);
      mainPanel.add(Box.createVerticalStrut(10));
   
   
      JTextField searchBox = new JTextField();
      searchBox.setHorizontalAlignment(JTextField.CENTER);
      searchBox.setPreferredSize(new Dimension(150,40)); 
      JButton searchButton = new JButton("Search");   
      JPanel control = new JPanel();
      mainPanel.add(searchBox);
      control.add(searchButton);
      mainPanel.add(control);
    
    
      JPanel result = new JPanel();
      JLabel keywords = new JLabel("<html><span style='color: black;'></span></html>");
      keywords.setHorizontalAlignment(JTextField.LEFT);
      result.add(keywords);
      mainPanel.add(result);
      Object rowData[][] ={ };
      Object columnNames[] ={ "ID","Title", "PI","Email" };
      TableModel tableModel = new DefaultTableModel(rowData, columnNames);
      
      JTable table = 
         new JTable(tableModel) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {                
               return false;               
            };
             
               //Implement table cell tool tips.           
            public String getToolTipText(MouseEvent e) {
               String tip = null;
               java.awt.Point p = e.getPoint();
               int rowIndex = rowAtPoint(p);
               int colIndex = columnAtPoint(p);
            
                                   
               return "Double Click to view more";
            }
         }; 
   
         
         
        
      JTableHeader tableHeader = table.getTableHeader();
      tableHeader.setFont(new Font("Arial", Font.PLAIN, 20));
      table.setFont(new Font("Arial", Font.PLAIN, 15));
      table.setRowHeight( 30);
      JScrollPane scrollPane = new JScrollPane(table); 
      mainPanel.add(scrollPane);
      //scrollPane.setVisible(true);
   
   
   
   
      //Finish up
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setVisible(true);
      
      
      
      
      uploadButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e){
             
               System.out.println("Upload Prompt");
               
               upload(frame);
               
               //action.uploadPaper()
            
            }
         });
         
         
   
              
      
      
   
               
      searchButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e){
               String searchTerms = searchBox.getText();
               ArrayList<BLPapers> papers = null;
            
               
               DefaultTableModel model = (DefaultTableModel) table.getModel();
                  
               int rowCount = model.getRowCount();
               System.out.println(rowCount);
               //Remove rows one by one from the end of the table
               for (int i = rowCount - 1; i >= 0; i--) {
                  model.removeRow(i);
               }
               
               try{
                  papers = action.search(searchTerms);
               }
               catch(DLException dle){
                  System.out.println(dle);
               }
               System.out.println(papers);
               for (BLPapers blPaper : papers) {
                  System.out.println(blPaper.getPaperID());
                  System.out.println(blPaper.getPaperAbstract());
                  System.out.println(blPaper.getTitle());
                  System.out.println(blPaper.getAuthor());
                  model.addRow(new Object[]{blPaper.getPaperID(),blPaper.getTitle(),blPaper.getAuthor(), "Send Email"});  
                  
               }
               keywords.setText("Finding papers that match: " + searchTerms);
               
            }
         });
   
      
                        
      loginButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e){
               System.out.println("Login Prompt");
                //login stuff
               JFrame loginFrame = new JFrame("Login");
            
               JPanel panel = new JPanel();
               loginFrame.add(panel);
               placeComponents(panel);
               loginFrame.setVisible(true);
               loginFrame.setSize(300,180);
               loginFrame.setLocationRelativeTo(frame);
            
            
            }
         });
      
         
      table.addMouseListener(
         new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  JTable target = (JTable)e.getSource();
                  int row = target.getSelectedRow();
                  int column = target.getSelectedColumn();
                  
                  Object paperID = (Object) table.getModel().getValueAt(row, 0);
                  BLPapers paper = null;
                  try{
                     paper = new BLPapers(paperID.toString());              
                  }
                  catch(DLException dle){
                     
                  } 
                  
                  if(column == 3){
                     //JDialog d = new JDialog(frame, "Send Email" , true);
                    
                     JFrame d = new JFrame("Send Email");
                     JPanel result = new JPanel();
                  
                     
                     
                     
                     
                  //    
                     //JLabel p1 = new JLabel("<html><span style='color: black;'>"+paper+"</span></html>");
                     //JLabel p2 = new JLabel("<html><span style='color: black;'>"+paper.getPaperAbstract()+"</span></html>");
                  
                     JLabel sendToLabel = new JLabel("<html><span style='color: black;'>\u0020 to:\u0020\u0020\u0020\u0020 </span><br /></html>");
                     JTextField sendTo = new JTextField();
                     sendTo.setHorizontalAlignment(JTextField.CENTER);
                     sendTo.setPreferredSize(new Dimension(250,40));
                        
                     JLabel sendersEmailLabel = new JLabel("<html><span style='color: black;'>From:</span><br /></html>");
                     JTextField sendersEmail = new JTextField();
                     sendersEmail.setHorizontalAlignment(JTextField.CENTER);
                     sendersEmail.setPreferredSize(new Dimension(250,40));
                           
                     JLabel emailBodyLabel = new JLabel("<html><span style='color: black;'>Body: </span><br /></html>");
                     JTextArea emailBody = new JTextArea(5,25);
                     emailBody.setSize(250,100);
                     emailBody.setLineWrap(true);
                     JScrollPane abstrctScrollPane = new JScrollPane(emailBody);
                  
                  
                  
                     result.add(sendToLabel);//label
                     result.add(sendTo);
                     sendTo.setText("AnEmail@email.com");
                  
                     result.add(sendersEmailLabel);//lavel
                     result.add(sendersEmail);
                  
                     result.add(emailBodyLabel);//label
                     result.add(emailBody); 
                     
                     emailBody.setText("Hello, \n \n I am writing to enquire about your research paper. I would like to collaborate on it.");
                  
                  
                  
                     JButton sendEmail = new JButton("Send");   
                     JButton cancelEmail = new JButton("Cancel"); 
                   
                     result.add(cancelEmail);
                     result.add(sendEmail);
                     d.add(result);
                     d.pack();
                     d.setSize(310,270);
                     d.setLocationRelativeTo(frame);
                  
                     d.setVisible(true);
                  
                  
                     sendEmail.addActionListener(
                        new ActionListener()
                        {
                           public void actionPerformed(ActionEvent e){
                                                         
                              openEmail(sendTo.getText(), sendersEmail.getText() ,emailBody.getText());
                           }
                        });
                  
                     cancelEmail.addActionListener(
                        new ActionListener()
                        {
                           public void actionPerformed(ActionEvent e){
                              System.out.println("cancle Prompt");
                                                       
                              int response = JOptionPane.showConfirmDialog(null, "Are you sure you want cancel your email?");
                           
                           
                           }
                        });
                     
                  
                  
                  
                  
                  
                  
                  
                  
                  }
                  else{
                  
                    
                  // do some action if appropriate column
                  
                  
                  
                  
                  
                     System.out.println("Table Clicked " + row + " " + column + " " + target);
                  // display/center the jdialog when the button is pressed
                     //JDialog d = new JDialog(frame, "Preview" , true);
                     JFrame d = new JFrame("Preview");
                     
                     
                  //    
                     JPanel result = new JPanel();
                    
                     JLabel p1 = new JLabel("<html><span style='color: black;'>Title: "+paper.getTitle()+"</span></html>");
                     p1.setPreferredSize(new Dimension(440, 40));
                    
                     JLabel paperAbstractLabel = new JLabel("<html><span style='color: black;'>Paper Abstract</span></html>");
                     JTextArea p2 = new JTextArea();
                     p2.setText(paper.getPaperAbstract());
                     p2.setWrapStyleWord(true);
                     p2.setLineWrap(true);
                     p2.setOpaque(true);
                     p2.setEditable(false);
                     p2.setFocusable(false);
                     p2.setBackground(UIManager.getColor("Label.background"));
                     p2.setFont(UIManager.getFont("Label.font"));
                     p2.setBorder(UIManager.getBorder("Label.border"));
                     JScrollPane abstractScroll = new JScrollPane(p2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                     abstractScroll.setMinimumSize(new Dimension(440, 100));
                     abstractScroll.setPreferredSize(new Dimension(440, 100));
                  
                     JLabel paperCitationLabel = new JLabel("<html><span style='color: black;'>Paper Citation</span></html>");
                     JTextArea p3 = new JTextArea();
                     p3.setText(paper.getCitation());
                     p3.setWrapStyleWord(true);
                     p3.setLineWrap(true);
                     p3.setOpaque(true);
                     p3.setEditable(false);
                     p3.setFocusable(false);
                     p3.setBackground(UIManager.getColor("Label.background"));
                     p3.setFont(UIManager.getFont("Label.font"));
                     p3.setBorder(UIManager.getBorder("Label.border"));
                     JScrollPane citationScroll = new JScrollPane(p3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                     citationScroll.setMinimumSize(new Dimension(440, 50));
                     citationScroll.setPreferredSize(new Dimension(440, 50));
                  
                  
                  
                     JButton editPaper = new JButton("Edit");   
                     JButton deletePaper = new JButton("Delete");
                     JButton downloadPaper = new JButton("Download"); 
                  
                   
                   
                     deletePaper.addActionListener(
                        new ActionListener()
                        {
                           public void actionPerformed(ActionEvent e){
                              System.out.println("Delete Prompt");
                              int response;
                           
                              response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this file?");
                           
                           
                           }
                        });
                  
                  
                  
                  
                     editPaper.addActionListener(
                        new ActionListener()
                        {
                           public void actionPerformed(ActionEvent e){
                              upload(frame);
                           
                           }
                        });
                     
                     
                     downloadPaper.addActionListener(
                        new ActionListener()
                        {
                           public void actionPerformed(ActionEvent e){
                              System.out.println("Download paper");
                           
                           }
                        });
                  
                  
                  
                     keywords.setHorizontalAlignment(JTextField.LEFT);
                     result.add(paperAbstractLabel);
                  
                     result.add(p1);
                    
                    
                     result.add(paperAbstractLabel);
                     result.add(abstractScroll);
                     result.add(Box.createVerticalStrut(20));
                    
                     result.add(paperCitationLabel);
                     result.add(citationScroll);
                  
                     result.add(editPaper);
                     result.add(deletePaper);
                     result.add(downloadPaper);
                  
                     d.add(result);
                     //d.pack();
                     d.setSize(500,400);
                     d.setLocationRelativeTo(frame);
                  
                     d.setVisible(true);
                  }
                  
                  
                  
               
               }
            }
            
            
            
            
            
            
            
            
            
            
         });
         
         
           
         
         
   }//end of run
     
   private static void placeComponents(JPanel panel) {
   
      panel.setLayout(null);
   
      JLabel userLabel = new JLabel("User");
      userLabel.setBounds(10, 10, 80, 25);
      panel.add(userLabel);
   
      JTextField userText = new JTextField(20);
      userText.setBounds(100, 10, 160, 25);
      panel.add(userText);
   
      JLabel passwordLabel = new JLabel("Password");
      passwordLabel.setBounds(10, 40, 80, 25);
      panel.add(passwordLabel);
   
      JPasswordField passwordText = new JPasswordField(20);
      passwordText.setBounds(100, 40, 160, 25);
      panel.add(passwordText);
   
      JButton LoginPrompt = new JButton("Login");
      LoginPrompt.setBounds(10, 80, 80, 25);
   	
      JButton cancleLoginPromp = new JButton("Cancle");
      cancleLoginPromp.setBounds(180, 80, 80, 25);
      panel.add(cancleLoginPromp);
      panel.add(LoginPrompt);
      
      JLabel loginError = new JLabel();
      loginError.setBounds(30, 110, 250, 25);
      panel.add(loginError);
      
                  
      LoginPrompt.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e){
               System.out.println("Validate Login");
                       //login stuff
               loginError.setText("<html><div style='color:red'>Error: Username or Password is wrong </html>");
            
            
               LoginPrompt.addActionListener(
                  new ActionListener()
                  {
                     public void actionPerformed(ActionEvent e){
                        System.out.println("Validate Login");
                       //login stuff
                        loginError.setText("<html><div style='color:red'>Error: Username or Password is wrong </html>");
                     
                     
                     
                     }
                  });
            
            }
         });
   
   }
     
  
   public static void upload(JFrame frame){
      PLActions action = new PLActions();       
      System.out.println("editPaper Prompt");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files only", "pdf");
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileFilter(filter);
      boolean fileUploaded = false;
                        
      int result = fileChooser.showOpenDialog(null);
      switch (result) {
         case JFileChooser.APPROVE_OPTION:
            System.out.println("Approve (Open or Save) was clicked");
            fileUploaded = true;
            break;
         case JFileChooser.CANCEL_OPTION:
            System.out.println("Cancel or the close-dialog icon was clicked");
            fileUploaded = false;
            break;
         case JFileChooser.ERROR_OPTION:
            System.out.println("Error");
            break;
      }
                        
      String PDFurl = null;
                
      int returnValue = fileChooser.showOpenDialog(null);
      
      if (returnValue == JFileChooser.APPROVE_OPTION) {
         File selectedFile = fileChooser.getSelectedFile();
                           
         PDFurl = selectedFile.toString();               
      
      
                           
                           
      }
      final String FinalPDFPath = PDFurl; // and now use this one in the Inner
   
      if(fileUploaded){
         JFrame uploadFrame = new JFrame("Upload a research project");
         
         BLPapers papers = new BLPapers();
         
         try{
            System.out.println(papers.fetchAllKeywords());
         }
         catch(DLException e){
         }
         JPanel panel = new JPanel();
         uploadFrame.add(panel);
         placeComponents(panel);
         uploadFrame.setVisible(true);
         uploadFrame.setSize(300,430);
         uploadFrame.setLocationRelativeTo(frame);
                           
         JPanel uploadHeader = new JPanel();
         JPanel upload = new JPanel();
         JLabel title = new JLabel("<html><span style='color: black;'>Research Title</span><br /></html>");
         title.setHorizontalAlignment(JLabel.CENTER);
         title.setVerticalAlignment(JLabel.CENTER);
                           
                           
                           
                           //paperTitle
                           //JLabel paperTitleLabel = new JLabel("<html><span style='color: black;'>Research Title</span><br /></html>");
         JTextField paperTitleField = new JTextField();
         paperTitleField.setHorizontalAlignment(JTextField.CENTER);
         paperTitleField.setPreferredSize(new Dimension(250,40));
                           //auther(s) 
         JLabel paperAutherLabel = new JLabel("<html><span style='color: black;'>Auther(s)</span><br /></html>");
         JTextField paperAuther = new JTextField();
         paperAuther.setHorizontalAlignment(JTextField.CENTER);
         paperAuther.setPreferredSize(new Dimension(250,40));
                           
                           //abstrct 
         JLabel paperAbstrctLabel = new JLabel("<html><span style='color: black;'>Abstrct</span><br /></html>");
         JTextArea paperAbstrct = new JTextArea(5,25);
         paperAbstrct.setSize(250,100);
         paperAbstrct.setLineWrap(true);
         JScrollPane abstrctScrollPane = new JScrollPane(paperAbstrct);
                           
                           //citations 
         JLabel papercitationLabel = new JLabel("<html><span style='color: black;'>Citations</span><br /></html>");
         JTextField paperCitations = new JTextField();
         paperCitations.setHorizontalAlignment(JTextField.CENTER);
         paperCitations.setPreferredSize(new Dimension(250,40));
                             
                           //keywords 
         JLabel paperKeywordLabel = new JLabel("<html><span style='color: black;'>Keywords</span><br /></html>");
         JTextField paperKeywords = new JTextField();
         paperKeywords.setHorizontalAlignment(JTextField.CENTER);
         paperKeywords.setPreferredSize(new Dimension(250,40)); 
         paperKeywords.setForeground(Color.GRAY);
         paperKeywords.setText("Seperate by space"); 
      
      
         paperKeywords.addKeyListener( 
            new KeyListener() {
               @Override
               public void keyTyped(KeyEvent arg0) {
                  if(paperKeywords.getText().equals("Seperate by space")){
                     System.out.println("Space was entered! clearing the  text field");
                     paperKeywords.setText("");
                     paperKeywords.setForeground(Color.BLACK);
                     
                     //arg0.consume();
                  }
               
               }
               
               @Override
               public void keyReleased(KeyEvent arg0) {
               // TODO Auto-generated method stub
               
               }
            
               @Override
               public void keyPressed(KeyEvent arg0) {
               // TODO Auto-generated method stub
                  //
               
               
               }
            
            });
      
      
                           
         upload.add(title);      
         upload.add(paperTitleField);
         
         upload.add(paperAutherLabel);
         upload.add(paperAuther);
         
         upload.add(paperKeywordLabel);//label
         upload.add(paperKeywords);
         
         upload.add(paperAbstrctLabel);//lavel
         upload.add(abstrctScrollPane);
         
         upload.add(papercitationLabel);//label
         upload.add(paperCitations); 
                           
                           
         JButton submitButton = new JButton("Submit");
         JButton cancelBut = new JButton("Cancel");
                           // uploadButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                           
                           
         upload.add(cancelBut);
         upload.add(submitButton);
         uploadFrame.add(uploadHeader);
         uploadFrame.add(upload);
                           
         uploadFrame.setLocationRelativeTo(frame);
         uploadFrame.setVisible(true); 
         uploadFrame.setResizable(false);
         
        // System.out.print(paperCitations.getText());
        
        
        
                                
         submitButton.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent e){
                  paperKeywords.getText();
                 // System.out.println(selectedFile)
                  try{
                     System.out.println("title: "+ paperTitleField.getText());
                     System.out.println(paperCitations.getText());
                     System.out.println(paperAbstrct.getText());
                     System.out.println(FinalPDFPath);
                     System.out.println(paperAuther.getText());
                     System.out.println( paperKeywords.getText());
                     ArrayList<String> keywords = new  ArrayList<String>();
                     keywords.addAll(Arrays.asList(paperKeywords.getText().split(" ")));
                     System.out.print(keywords);
                  
                     //title string, citation string, abstrat string, PAper url, String AUther ,arraylist<String> Keywords, userObject
                     action.uploadPaper(paperTitleField.getText(),paperCitations.getText(),paperAbstrct.getText(), FinalPDFPath, paperAuther.getText(),keywords);
                     uploadFrame.setVisible(false); 
                  
                  }
                  catch(DLException dle){
                  
                  }
               }
            });
         cancelBut.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent e){
                  uploadFrame.setVisible(false); 
               }
            });
      }
   
   }
   
   
   
   public static void openEmail(String sendTo, String from, String message){
      Desktop desktop;
      String NewMessage ="Hello,%20I%20am%20writing%20to%20enquire%20about%20your%20research%20paper.%20I%20would%20like%20to%20collaborate%20on%20it.";
      
      if (Desktop.isDesktopSupported() 
      && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
         try{
            URI mailto = new URI("mailto:"+sendTo+"?subject=Notification%20of%20Facility%20research%20request&body="+NewMessage);
            desktop.mail(mailto);
         }
         catch(URISyntaxException | IOException dle){
         }
      } 
      else {
      // TODO fallback to some Runtime.exec(..) voodoo?
         throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
      }
   }
}//end of class


