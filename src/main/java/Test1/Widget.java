package Test1;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Widget {
    private static final AtomicLong counter = new AtomicLong();

    public Widget(long id) {
        if (id == 0)
            this.Id = counter.incrementAndGet();
        else
            this.Id = id;
        this.LastModificationTime = new Date();
    }

    public final long Id;
    public int X;
    public int Y;
    public int Zindex;
    public long Width;
    public long Height;
    public Date LastModificationTime;

}
