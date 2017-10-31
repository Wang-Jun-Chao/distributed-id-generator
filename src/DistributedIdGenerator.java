import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 * 需要40位用于记录毫秒数
 * 需要6位记录区域
 * 需要7位记录毫秒内的序列号
 * 需要7位记录应用编号
 * 剩下4位进行保留
 *
 *  ---- ---------------------------------------- ------ ------- -------
 * |4   |40                                      |6     |7      |7      |
 *  ---- ---------------------------------------- ------ ------- -------
 * </pre>
 * Author: 王俊超
 * Date: 2017-10-31 08:05
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class DistributedIdGenerator {
    private static final byte DEFAULT = 0;
    // 2017-01-01 00:00:00.000距1900-01-01 00:00:00毫秒数
    private static final long START = new GregorianCalendar(2017, 0, 1, 0, 0, 0).getTimeInMillis();

    private final AtomicInteger seq = new AtomicInteger(0);

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
        return System.currentTimeMillis() - START;
    }

    private long getNextNumber() {
        int n = seq.incrementAndGet();
        seq.compareAndSet(0b0111_1111, 0);
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
