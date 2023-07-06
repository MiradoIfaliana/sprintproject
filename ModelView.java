package framework;
import java.util.*;
public class ModelView {

    String url;
    HashMap<String,Object> data;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public HashMap<String,Object> getData() {
        return data;
    }
    public void setData(HashMap<String,Object> data) {
        this.data = data;
    }
    public void addItem(String key,Object value){
        if(data==null){ data=new HashMap<String,Object>(); }
        data.put(key, value);
    }
    


}
