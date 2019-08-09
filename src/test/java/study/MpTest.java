/*
package study;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import study.dto.Student;
import study.mapper.MpMapper;
import study.util.ReadTextUtil;
import study.util.RedisUtil;
import study.util.TxtToVoice;

import java.io.*;
import java.security.PrivateKey;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpTest {
@SuppressWarnings("All")
@Autowired
private MpMapper mpMapper;
    @Test
    public void selectById(){
        Student stu=mpMapper.selectById(2);
        System.out.println(stu.toString());
    }
    @Test
    public void selectBylist(){
        List list=new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Student> stu=mpMapper.selectBatchIds(list);
        stu.forEach(System.out::println);
    }
    @Test
    public void selectByMap(){

        Map<String,Object> map=new HashMap<>();
        map.put("id",1);
        List<Student> stu=mpMapper.selectByMap(map);
        stu.forEach(System.out::println);
    }

    @Test
    public void selectByQueryWrapper(){

        QueryWrapper<Student> qw=new QueryWrapper();
        qw.orderByDesc("id").and(q->q.lt("age",30).gt("age",20).isNotNull("age"));


        List<Student> stu=mpMapper.selectList(qw);
        stu.forEach(System.out::println);
    }

    @Test
    public void selectByQueryWrapper1(){

        QueryWrapper<Student> qw=new QueryWrapper();
//        qw.like("name","c");
        qw.select("name","age").likeLeft("name","s");


        List<Student> stu=mpMapper.selectList(qw);
        stu.forEach(System.out::println);
    }
    @Test
    public void selectByLambdaQueryWrapper1(){

        LambdaQueryWrapper<Student> qw=new LambdaQueryWrapper<>();
//        qw.like("name","c");
        qw.like(Student::getName,"c").and(lap->lap.eq(Student::getAge,21).or().eq(Student::getAge,29));
        List<Student> stu=mpMapper.selectList(qw);
        stu.forEach(System.out::println);
    }

    @Test
    public void selectPage(){
        QueryWrapper<Student> qw=new QueryWrapper();
        qw.like("name","c");

        Page <Student> ss=new Page<>(1,2);
        IPage<Student> p=mpMapper.selectPage(ss,qw);
        System.out.println("总页数:"+ p.getPages());
        System.out.println("总记录:"+ p.getTotal());
        List<Student> s=p.getRecords();
        s.forEach(System.out::println);

    }
    @Test
    public void updata(){
        Student stu=new Student();
        stu.setAge(35);
        stu.setCity("北京");
        stu.setId(4);
        stu.setName("lv");
        int row=mpMapper.updateById(stu);
        System.out.println(row);




    }
    @Test
    public void updataByMrapper(){
        UpdateWrapper<Student> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("age",33).set("age",50);


        int row=mpMapper.update(null,updateWrapper);
        System.out.println(row);
        QueryWrapper<Student> qw=new QueryWrapper<>();
        qw.eq("age",50);
        Student stu=mpMapper.selectOne(qw);
        System.out.println(stu);
    }
    @Test
    public void delet(){
        mpMapper.deleteById(4);
    }
    @Test
    public void deletByWrapper(){
        QueryWrapper<Student> qw=new QueryWrapper<>();
        qw.eq("age",22);
        mpMapper.delete(qw);//参数为Wrapper;
    }
    @Test
    public void insert(){
       Student stu=new Student();
       stu.setName("bb");
       stu.setId(7);
       stu.setAge(10);
       stu.setCity("九江");
       boolean row=stu.insertOrUpdate();
        Student s=stu.selectById(7);
        System.out.println(row);
        System.out.println(s);
    }

    @Test
    public void test(){
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        // Dispatch是做什么的？
        Dispatch sapo = sap.getObject();
        try {
            // 音量 0-100
            sap.setProperty("Volume", new Variant(20));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(0));

            Variant defalutVoice = sap.getProperty("Voice");

            Dispatch dispdefaultVoice = defalutVoice.toDispatch();
            Variant allVoices = Dispatch.call(sapo, "GetVoices");
            Dispatch dispVoices = allVoices.toDispatch();

            Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
            ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
            ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);

            Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant("嗨，小明，早上好"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }


    @Autowired
    private TxtToVoice txtToVoice;

    @Autowired
    private ReadTextUtil readTextUtil;


    @Test
    public void test1(){

        String str ="山间小屋：那绿树掩映之间是一幢幢依山而建的小木屋，它如一颗明珠被青山绿水环抱，带上家人亦或约上三五好友小住几晚，听小桥流水、观青竹摇曳、赏花间美景，体验山中不一样的夜晚。\n";//待翻译的字符串
        int volume=10;//音量
        int rate=0;//速度
        txtToVoice.texToVoice(str,volume,rate);




    }
    @Test
    public void test2(){

        String path;
        path="C:/Users/ccs/Desktop/ccs.txt";
        File file=new File(path);
        readTextUtil.readText(file);

    }
    @Test
    public void test3(){
        String path="C:/Users/ccs/Desktop/ccs.txt";
        File infile=new File(path);
        BufferedReader bufferedReader=null;
        String str="";
        StringBuffer sb=new StringBuffer();
        try {
             bufferedReader=new BufferedReader(new FileReader(infile));
              while ((str=bufferedReader.readLine())!=null){
                  sb.append(str);
              }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

             System.out.println(sb);
    }
    @Test
    public void test4() throws Exception{
        String path="C:/Users/ccs/Desktop/ccs.txt";
        File infile=new File(path);
        String path2="C:/Users/ccs/Desktop/ccsout.txt";
        File outfile=new File(path2);
        FileInputStream is=new FileInputStream(infile);
        FileOutputStream os=new FileOutputStream(outfile);
         byte[] buff = new byte[1024];
        StringBuffer sb=new StringBuffer();
          int len=0;
          while((len=is.read(buff))!=-1){
              os.write(buff);
          }
          os.close();
          is.close();


    }






}
*/
