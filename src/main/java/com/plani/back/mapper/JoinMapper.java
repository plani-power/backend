package com.plani.back.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JoinMapper {
    Map<String,Object> userList(String userid);
    Map<String,Object> planList(int planid);
}
