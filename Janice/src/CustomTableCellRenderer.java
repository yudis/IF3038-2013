/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SPACE;

/**
 *
 * @author User
 */
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer 
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) 
    {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value instanceof String && value.toString().contains("7")){
             cell.setBackground(new java.awt.Color(255, 50, 20));
        }else{
             cell.setBackground(table.getBackground());
        }
        return cell;
    }
}
