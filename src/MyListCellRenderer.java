import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListCellRenderer implements ListCellRenderer<String> {

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        JButton button = new JButton(value);
        button.setSize(150,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = button.getText();
                if (text.equals("MAP")){
                    System.out.println("w");
                }
            }
        });
        return button;
    }
}
