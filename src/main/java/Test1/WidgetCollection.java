package Test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WidgetCollection {
    private final ArrayList<Widget> widgets = new java.util.ArrayList();

    public Widget newWidget() {
        Widget res = new Widget(0);
        widgets.add(res);
        return res;
    }

    public Widget newWidget(int x, int y, int width, int height, int zindex) {
        Widget res = newWidget();
        res.X = x;
        res.Y = y;
        res.Width = width;
        res.Height = height;
        res.Zindex = zindex;
        return res;
    }

    protected void setTopLevel(Widget widget) {
        int maxZindex =  widgets.stream().map(w -> w.Zindex).max(Comparator.naturalOrder()).orElse(0);
        widget.Zindex = maxZindex + 1;
    }

    protected void shiftLevelUp(int fromLevel) {
        widgets.stream().
                filter(w -> w.Zindex >= fromLevel).
                forEach(w -> w.Zindex += 1);
    }

    public void shiftLevelFromTo(int from, int to) {
        if (from == to)
            return;
        if (from > to) {
            widgets.stream().
                    filter(w -> w.Zindex < from && w.Zindex >= to).
                    forEach(w -> w.Zindex -= 1);
        } else {
            widgets.stream().
                    filter(w -> w.Zindex > from && w.Zindex <= to).
                    forEach(w -> w.Zindex += 1);
        }
    }

    public Widget newWidget(int x, int y, int width, int height) {
        Widget res = newWidget(x, y, width, height, 0);
        // Zindex не указан. размещаем объект самым верхним
        setTopLevel(res);
        return res;
    }

    public void add(Widget widget) {
        widgets.add(widget);
    }

    public void makeTestData() {
        newWidget(10, 10, 20 ,20);
        newWidget(0, 20, 15 ,15, -2);
        newWidget(20, 0, 15 ,15);
        newWidget(40, 0, 15 ,15, 5);
        newWidget(0, 40, 15 ,15);
    }

    public List<Widget> list() {
        return widgets;
    }

    public Widget get(long Id) {
        return widgets.stream().
                filter(w -> w.Id == Id).
                findFirst().
                get();
    }

    public void delete(long Id) {
        widgets.remove(get(Id));
    }

}
