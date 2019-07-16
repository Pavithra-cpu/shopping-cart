package queryclass;
import java.io.Serializable;
import java.io.*;
import java.util.*;
public class Purchases implements Serializable {

    private Object pid;
    private int uid;
    private String uname;
    private String email;
    private int count;

    public void setPid(Object pid){
        this.pid = pid;
    }

    public Object getPid(){
        return this.pid;
    }

    public void setUid(int uid){
        this.uid = uid;
    }

    public int getUid(){
        return this.uid;
    }

    public void setUname(String uname){
        this.uname = uname;
    }

    public String getUname() {
        return this.uname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

}
