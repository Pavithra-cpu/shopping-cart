package servletclass;
import java.io.*;
import java.util.*;
public class ObjectToFileWrite <T> {
public void saveItem(String filename, T obj) throws IOException
{
    File fi = new File(filename);
    if(!fi.exists())
    {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fi, true));
      oos.writeObject(obj);
      oos.flush();
      oos.close();
    }
    else
    {
      Appendobj appendo = new Appendobj(new FileOutputStream(fi, true));
      appendo.writeObject(obj);
      appendo.flush();
      appendo.close();
    }
}
}
class Appendobj extends ObjectOutputStream {
public Appendobj(OutputStream out) throws IOException {
    super(out);
}
@Override
protected void writeStreamHeader() throws IOException {
    reset();
}
}
