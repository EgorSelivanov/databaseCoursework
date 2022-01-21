package tableModels;

import javax.swing.*;
import java.util.Vector;

public class CitiesBoxModel extends DefaultComboBoxModel<String> {
public CitiesBoxModel(Vector<String> items) {
        super(items);
        }

@Override
public String getSelectedItem() {
        String selectedCity = (String) super.getSelectedItem();
        return selectedCity;
        }
}
