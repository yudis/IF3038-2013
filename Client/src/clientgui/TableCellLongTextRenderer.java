/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.awt.Component;  
import javax.swing.JTable;  
import javax.swing.JTextArea;  
import javax.swing.table.TableCellRenderer;  

public class TableCellLongTextRenderer extends JTextArea implements TableCellRenderer{  

@Override  
public Component getTableCellRendererComponent(JTable table, Object value, 
boolean isSelected, boolean hasFocus, int row, int column) {  

    this.setText((String)value);  
    this.setWrapStyleWord(true);                      
    this.setLineWrap(true);

    //set the JTextArea to the width of the table column  
    setSize(table.getColumnModel().getColumn(column).getWidth(),getPreferredSize().height);  

    if (table.getRowHeight(row) < getPreferredSize().height) {  
        //set the height of the table row to the calculated height of the JTextArea  
        table.setRowHeight(row, getPreferredSize().height);  
    }  

    return this;  
}} 