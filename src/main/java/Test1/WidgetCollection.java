package Test1;

import java.util.ArrayList;
import java.util.List;

public class WidgetCollection {
    private final ArrayList<Widget> widgets = new java.util.ArrayList();

    public Widget New() {
        Widget res = new Widget(0);
        widgets.add(res);
        return res;
    }

    public List<Widget> List() {
        return widgets;
    }

    public Widget Get(long Id) {
        return widgets.stream().findFirst(w -> w.Id == Id);
    }


}
