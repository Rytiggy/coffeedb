import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // seems to be missing.

import javax.swing.table.*;
import javax.swing.*;


public class PLGUI {
  
  
  
   public static void main(String[] args){
      JFrame frame = new JFrame("Faculty Research Assistant");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
      //mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
   
      JPanel title = new JPanel();
      title.setBackground(Color.orange);
   
      JLabel header = new JLabel("<html><span style='color: white;'>Faculty Research Assistant</span></html>");
      header.setFont(header.getFont().deriveFont(64.0F));
   
      title.add(header);
   
      mainPanel.add(title);
      mainPanel.add(Box.createVerticalStrut(2));
   
      JPanel info = new JPanel();
      info.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 10));
   
      JLabel version = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='text-align:center'>By CoffeeDB</span> Version 1.0 </html>");
      info.add(version);  
   
      mainPanel.add(info);
      mainPanel.add(Box.createVerticalStrut(20));
   
      JTextField searchBox = new JTextField();
      searchBox.setHorizontalAlignment(JTextField.CENTER);
      searchBox.setPreferredSize(new Dimension(150,40));
      
      JButton searchButton = new JButton("Search");
      
      mainPanel.add(searchBox);
      mainPanel.add(searchButton);
    
      JPanel result = new JPanel();
      JLabel keywords = new JLabel("<html><span style='color: black;'></span></html>");
      keywords.setHorizontalAlignment(JTextField.LEFT);
   
      result.add(keywords);
      mainPanel.add(result);
   
      Object rowData[][] ={ { "The Stars and how they  shine ", "Bob hattington", "Finalized" },{ "A Paper about science", "Ryan Mason", "In Progress" } };
      Object columnNames[] ={ "Title", "PI", "Status" };
      //JTable table = new JTable(rowData, columnNames);
      JTable table = 
         new JTable(rowData, columnNames) {
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
      scrollPane.setVisible(true);
   
   
   
   
      //Finish up
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setVisible(true);
      
      
      
      
      
      
      
      
      
               
      searchButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed(ActionEvent e){
               String searchTerms = searchBox.getText();
               //System.out.println(data);
               keywords.setText("Finding papers that match: " + searchTerms);
            }
         });
               
         
      table.addMouseListener(
         new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  JTable target = (JTable)e.getSource();
                  int row = target.getSelectedRow();
                  int column = target.getSelectedColumn();
               // do some action if appropriate column
               
               
                  System.out.println("Table Clicked " + row + " " + column + " " + target);
               // display/center the jdialog when the button is pressed
                  JDialog d = new JDialog(frame, "Hello", true);
                  d.setLocationRelativeTo(frame);
               //    
                  JPanel result = new JPanel();
                  JLabel p1 = new JLabel("<html><span style='color: black;'>Paper "+row+" found </span></html>");
                  JLabel p2 = new JLabel("<html><span style='color: black;'>Show data about paper here</span></html>");
               
                  keywords.setHorizontalAlignment(JTextField.LEFT);
               // 
               //  
                  result.add(p1);
                  result.add(p2);
                  d.add(result);
                  d.pack();
               
                  d.setVisible(true);
               }
            }
         });
   }//end of run
   
   
   public boolean createList(){
      return true;
   }
 


}

