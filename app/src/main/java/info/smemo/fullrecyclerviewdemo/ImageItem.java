package info.smemo.fullrecyclerviewdemo;

/**
 * Created by suzhenpeng on 2015/6/4.
 */
public class ImageItem {

    public static final int LEFT=1;
    public static final int RIGHT=2;

    public String title;
    public int img;
    public int type;

    public ImageItem(String title, int img, int type) {
        this.title = title;
        this.img = img;
        this.type = type;
    }
}
