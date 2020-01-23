package Test1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

class WidgetCollectionTest {

    @Test
    void newWidget() {
        WidgetCollection collection = new WidgetCollection();
        Widget widget = collection.newWidget(0, 0, 10, 10);
        assertEquals(0, widget.X);
        assertEquals(0, widget.Y);
        assertEquals(10, widget.Width);
        assertEquals(10, widget.Height);
        assertEquals(1, widget.Zindex);
    }

    @Test
    void setTopLevel() {
        WidgetCollection collection = new WidgetCollection();
        Widget w1 = collection.newWidget(0, 0, 10, 10, 1); collection.add(w1);
        Widget w2 = collection.newWidget(0, 0, 10, 10, 2); collection.add(w2);
        Widget w3 = collection.newWidget(0, 0, 10, 10, 3); collection.add(w3);
        collection.setTopLevel(w1);
        assertEquals(4, w1.Zindex);
        assertEquals(2, w2.Zindex);
        assertEquals(3, w3.Zindex);
    }

    @Test
    void shiftLevelUp() {
        WidgetCollection collection = new WidgetCollection();
        Widget w1 = collection.newWidget(0, 0, 10, 10, 1); collection.add(w1);
        Widget w2 = collection.newWidget(0, 0, 10, 10, 2); collection.add(w2);
        Widget w3 = collection.newWidget(0, 0, 10, 10, 3); collection.add(w3);
        collection.shiftLevelUp(2);
        assertEquals(1, w1.Zindex);
        assertEquals(3, w2.Zindex);
        assertEquals(4, w3.Zindex);
    }

    @Test
    void shiftLevelFromTo1() {
        WidgetCollection collection = new WidgetCollection();
        Widget w1 = collection.newWidget(0, 0, 10, 10, 1); collection.add(w1);
        Widget w2 = collection.newWidget(0, 0, 10, 10, 2); collection.add(w2);
        Widget w3 = collection.newWidget(0, 0, 10, 10, 3); collection.add(w3);
        collection.shiftLevelFromTo(1, 3);
        assertEquals(1, w1.Zindex);
        assertEquals(1, w2.Zindex);
        assertEquals(2, w3.Zindex);
    }

    @Test
    void shiftLevelFromTo2() {
        WidgetCollection collection = new WidgetCollection();
        Widget w1 = collection.newWidget(0, 0, 10, 10, 1); collection.add(w1);
        Widget w2 = collection.newWidget(0, 0, 10, 10, 2); collection.add(w2);
        Widget w3 = collection.newWidget(0, 0, 10, 10, 3); collection.add(w3);
        collection.shiftLevelFromTo(3, 1);
        assertEquals(2, w1.Zindex);
        assertEquals(3, w2.Zindex);
        assertEquals(3, w3.Zindex);
    }

    @Test
    void get() {
        WidgetCollection collection = new WidgetCollection();
        Widget w1 = collection.newWidget(0, 0, 10, 10, 1); collection.add(w1);
        Widget w2 = collection.newWidget(0, 0, 10, 10, 2); collection.add(w2);
        Widget w3 = collection.newWidget(0, 0, 10, 10, 3); collection.add(w3);
        Widget res = collection.get(w3.Id);
        assertEquals(w3, res);
    }

    @Test
    void replace() {
        WidgetCollection collection = new WidgetCollection();
        Widget w1 = collection.newWidget(0, 0, 10, 10, 1); collection.add(w1);
        Widget w2 = collection.newWidget(0, 0, 10, 10, 2); collection.add(w2);
        Widget w3 = collection.newWidget(0, 0, 10, 10, 3); collection.add(w3);
        Widget wr = new Widget(w2.Id) {{
           X = 5; Y = 6;
           Width = 15; Height = 16; Zindex = 10;
        }};
        collection.replace(wr);
        Widget wn = collection.get(w2.Id);
        assertEquals(5, wn.X);
        assertEquals(6, wn.Y);
        assertEquals(15, wn.Width);
        assertEquals(16, wn.Height);
        assertEquals(10, wn.Zindex);
    }
}