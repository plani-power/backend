package com.plani.back.serviceimpl;
import com.plani.back.mapper.TestMapper;
import com.plani.back.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;

    @Override
    public String testDB() {
        return testMapper.testDB();
    }
}
