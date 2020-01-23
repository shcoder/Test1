package Test1;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@JsonPropertyOrder({"Id", "X", "Y"})
public class Widget {
    private static final AtomicLong counter = new AtomicLong();

    public Widget(long id) {
        if (id == 0)
            this.Id = counter.incrementAndGet();
        else
            this.Id = id;
        updateModificationTime();
    }

    @JsonCreator
    public Widget(int x, int y, int width, int height) {
        //this.Id = counter.incrementAndGet();
        this.Id = 0;
        this.X = x;
        this.Y = y;
        this.Width = width;
        this.Height = height;
        this.Zindex = Integer.MAX_VALUE;
        updateModificationTime();
    }

    public final long Id;
    public int X;
    public int Y;
    public int Zindex;
    public long Width;
    public long Height;
    public Date LastModificationTime;

    protected void updateModificationTime() {
        this.LastModificationTime = new Date();
    }

    public void updateBy(Widget base) {
        this.X = base.X;
        this.Y = base.Y;
        this.Width = base.Width;
        this.Height = base.Height;
        this.Zindex = base.Zindex;
        updateModificationTime();
    }
}
