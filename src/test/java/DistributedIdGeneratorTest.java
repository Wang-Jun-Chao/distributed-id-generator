import org.junit.Test;
import wjc.example.DistributedIdGenerator;

/**
 * Author: 王俊超
 * Date: 2017-11-01 08:01
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class DistributedIdGeneratorTest {
    @Test
    public void test() {
        DistributedIdGenerator generator = new DistributedIdGenerator();
        System.out.println(Long.toBinaryString(generator.next()));
        System.out.println(Long.toBinaryString(generator.next()));
    }
}
