package com.shdatalink.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shdatalink.common.model.Todo;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoMapper extends BaseMapper<Todo> {
}
