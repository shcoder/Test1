package Test1;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class WidgetController {

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
        Widget res = widgets.newWidget(widget);
        widgets.add(res);
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
        Widget res = new Widget(id);
        res.updateBy(widget);
        Widget old = widgets.get(id);
        widgets.shiftLevelFromTo(old.Zindex, widget.Zindex);
        widgets.replace(res);
        return res;
    }
}
