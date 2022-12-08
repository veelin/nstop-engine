package test;


import com.nstop.biz.NstopApplication;
import com.nstop.biz.test.AfterSaleServiceImpl;
import com.nstop.biz.test.LeaveServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NstopApplication.class)
public class DemoTest {
    @Resource
    private AfterSaleServiceImpl afterSaleService;
    @Resource
    private LeaveServiceImpl leaveService;

    @Test
    public void runAfterSaleDemo(){
        afterSaleService.run();
    }

    @Test
    public void runLeaveDemo(){
        leaveService.run();
    }
}
