package com.example.mvctutorial.designpattern._10composite;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Component {
    List<Component> child = new ArrayList();

    public Folder(String name) {
        super(name);
    }

    Boolean addComponent(Component component) {
        return child.add(component);
    }

    public List<Component> getChildren() {
        return child;
    }
}
