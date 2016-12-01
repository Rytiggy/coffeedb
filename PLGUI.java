import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // seems to be missing.

import javax.swing.table.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.*;
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
      Object columnNames[] ={ "ID","Title", "PI" };
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
                  model.addRow(new Object[]{blPaper.getPaperID(),blPaper.getTitle(),blPaper.getAuthor()});  
                  
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
                     }catch(DLException dle){
                     
                     }                     
               // do some action if appropriate column
               
               
                  System.out.println("Table Clicked " + row + " " + column + " " + target);
               // display/center the jdialog when the button is pressed
                  JDialog d = new JDialog(frame, "Preview" , true);
                  d.setLocationRelativeTo(frame);
               //    
                  JPanel result = new JPanel();
                  JLabel p1 = new JLabel("<html><span style='color: black;'>"+paper+"</span></html>");
                  JLabel p2 = new JLabel("<html><span style='color: black;'>"+paper.getPaperAbstract()+"</span></html>");
                  
                  JButton editPaper = new JButton("Edit");   
                  JButton deletePaper = new JButton("Delete"); 
                   
                   
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
                     
               
               
               
                  keywords.setHorizontalAlignment(JTextField.LEFT);
               
                  result.add(p1);
                  result.add(p2);
                  result.add(editPaper);
                  result.add(deletePaper);
                  d.add(result);
                  d.pack();
               
                  d.setVisible(true);
                  
                  
                  
                  
               
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
            
            
            
            }
         });
   
   }
     
  
   public static void upload(JFrame frame){
   
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
                        
                        
      int returnValue = fileChooser.showOpenDialog(null);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
         File selectedFile = fileChooser.getSelectedFile();
                           
                           
         System.out.println(selectedFile);
                           
                           
      }
                        
      if(fileUploaded){
         JFrame uploadFrame = new JFrame("Upload a research project");
         JPanel panel = new JPanel();
         uploadFrame.add(panel);
         placeComponents(panel);
         uploadFrame.setVisible(true);
         uploadFrame.setSize(300,380);
         uploadFrame.setLocationRelativeTo(frame);
                           
         JPanel uploadHeader = new JPanel();
         JPanel upload = new JPanel();
         JLabel title = new JLabel("<html><span style='color: black;'>Research Title</span><br /></html>");
         title.setHorizontalAlignment(JLabel.CENTER);
         title.setVerticalAlignment(JLabel.CENTER);
                           
                           
                           
                           //paperTitle
                           //JLabel paperTitleLabel = new JLabel("<html><span style='color: black;'>Research Title</span><br /></html>");
         JTextField paperTitle = new JTextField();
         paperTitle.setHorizontalAlignment(JTextField.CENTER);
         paperTitle.setPreferredSize(new Dimension(250,40));
                           //auther(s) 
         JLabel paperAutherLabel = new JLabel("<html><span style='color: black;'>Auther(s)</span><br /></html>");
         JTextField paperAuther = new JTextField();
         paperAuther.setHorizontalAlignment(JTextField.CENTER);
         paperAuther.setPreferredSize(new Dimension(250,40));
                           
                           //abstrct 
         JLabel paperAbstrctLabel = new JLabel("<html><span style='color: black;'>Abstrct</span><br /></html>");
         JTextArea paperAbstrct = new JTextArea();
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
                           
                           
         uploadHeader.add(paperTitle);
         upload.add(title);
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
      }
   
   }
     
     
}//end of class


