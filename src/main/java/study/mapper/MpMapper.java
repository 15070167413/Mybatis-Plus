package study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import study.dto.Student;

import java.util.LinkedList;
import java.util.List;

@Mapper

public interface MpMapper extends BaseMapper<Student>{

}
