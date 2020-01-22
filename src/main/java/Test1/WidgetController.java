package Test1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/widget")
public class WidgetController {
    private ArrayList<Widget> widgets = new ArrayList<Widget>();

    public WidgetController() {
        widgets.add(new Widget(0));
        widgets.add(new Widget(0));
        widgets.add(new Widget(0));
    }

    @GetMapping("/list")
    public ArrayList<Widget> List() { //@RequestParam(value = "name", defaultValue = "World") String name) {
        return widgets;
    }
}
