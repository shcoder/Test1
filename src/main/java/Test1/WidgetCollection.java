package Test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WidgetCollection {
    private final ArrayList<Widget> widgets = new java.util.ArrayList();

    public void makeTestData() {
        add(newWidget(10, 10, 20 ,20));
        add(newWidget(0, 20, 15 ,15, -2));
        add(newWidget(20, 0, 15 ,15));
        add(newWidget(40, 0, 15 ,15, 5));
        add(newWidget(0, 40, 15 ,15));
    }

    public Widget newWidget() {
        Widget res = new Widget(0);
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

    public Widget newWidget(int x, int y, int width, int height) {
        Widget res = newWidget(x, y, width, height, 0);
        // Zindex не указан. размещаем объект самым верхним
        setTopLevel(res);
        return res;
    }

    public Widget newWidget(Widget base) {
        Widget res = newWidget();
        res.X = base.X;
        res.Y = base.Y;
        res.Zindex = base.Zindex;
        res.Width = base.Width;
        res.Height = base.Height;
        if (res.Zindex == Integer.MAX_VALUE)
            setTopLevel(res);
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
                    forEach(w -> w.Zindex += 1);
        } else {
            widgets.stream().
                    filter(w -> w.Zindex > from && w.Zindex <= to).
                    forEach(w -> w.Zindex -= 1);
        }
    }

    public void add(Widget widget) {
        widgets.add(widget);
    }

    public List<Widget> list() {
        return widgets.stream().sorted((w1, w2) -> Integer.compare(w1.Zindex, w2.Zindex)).collect(Collectors.toList());
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

    public void replace(Widget widget) {
        Widget old = get(widget.Id);
        int pos = widgets.indexOf(old);
        widgets.set(pos, widget);
    }
}
