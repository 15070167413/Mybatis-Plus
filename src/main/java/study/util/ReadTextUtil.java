package study.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.stereotype.Component;

import java.io.*;
@Component
public class ReadTextUtil {

    public  void readText(File text) {
        Reader reader=null;
        BufferedReader br=null;
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        Dispatch sapo=sap.getObject();
        try {
            reader=new FileReader(text);
            br =new BufferedReader(reader);
            //音量
            sap.setProperty("Volume", new Variant(100));
            //语速
            sap.setProperty("Rate",new Variant(0.1));
            String info="";
            while((info=br.readLine())!=null) {
                //语音播放当前行的内容
                Dispatch.call(sapo,"Speak",info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
