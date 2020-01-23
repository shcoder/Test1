package Test1;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class WidgetController {
    //private ArrayList<Widget> widgets = new ArrayList<Widget>();
    private WidgetCollection widgets = new WidgetCollection();

    public WidgetController() {
        widgets.makeTestData();
    }

    @GetMapping("/widgets/")
    public List<Widget> list() {
        return widgets.list();
    }

    @PostMapping("/widgets/")
    public Widget newWidget(@RequestBody Widget widget) {
        if (widget.Zindex == Integer.MAX_VALUE) // индекс не указан
            widgets.setTopLevel(widget);
        else
            widgets.shiftLevelUp(widget.Zindex);

        Widget res = widgets.newWidget();
        res.updateBy(widget);
        return res;
    }

    @GetMapping("/widgets/{id}")
    public Widget get(@PathVariable long id) {
        return widgets.get(id);
    }

    @DeleteMapping("/widgets/{id}")
    public void delete(@PathVariable long id) {
        widgets.delete(id);
    }

    @PutMapping("/widgets/{id}")
    public Widget update(@PathVariable long id, @RequestBody Widget widget) {
        Widget res = widgets.get(id);
        widgets.shiftLevelFromTo(res.Zindex, widget.Zindex);
        res.updateBy(widget);
        return res;
    }
}
