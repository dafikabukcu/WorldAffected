import javax.swing.*;
import java.util.List;

public class MyListModel extends AbstractListModel {

    private final List<String> options;

    public MyListModel(List<String> options) {
        this.options = options;
    }

    @Override
    public int getSize() {
        return options.size();
    }

    @Override
    public Object getElementAt(int index) {
        return options.get(index);
    }
}
