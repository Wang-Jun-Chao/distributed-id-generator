import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2017-10-31 08:05
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class DistributedIdGenerator {
    private static final byte DEFAULT = 0;
    // 2017-01-01 00:00:00.000距1900-01-01 00:00:00毫秒数
    private static final long START =  new GregorianCalendar(2017, 0, 1,0,0,0).getTimeInMillis();
    /**
     * 区域编号
     */
    private byte region;
    /**
     * 应用编号
     */
    private byte application;
    /**
     * 保留位
     */
    private byte reservation = DEFAULT;



    private long getMillisecond() {
        return 0;
    }

    private long next() {
        return 0;
    }


    private long getNext() {
        return 0;
    }

    private List<Long> getNext(int size) {
        return new ArrayList<>();
    }
}
