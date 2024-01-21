package storiesclasses;

import android.util.Log;

public class Story {
    private boolean seen;
    String name;
    String imageUrl;

    public Story(String name, String imageUrl,boolean seen) {
        this.name = name;
        setName(name);
        this.imageUrl = imageUrl;
        setImageUrl(imageUrl);
        this.seen =seen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
//        Log.d("name",name);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isSeen(){
        return seen;
    }
    public void setSeen(boolean seen){

    }
}
