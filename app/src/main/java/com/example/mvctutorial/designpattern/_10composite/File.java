package com.example.mvctutorial.designpattern._10composite;

public class File extends Component{

    Object data;

    public File(String name) {
        super(name);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
