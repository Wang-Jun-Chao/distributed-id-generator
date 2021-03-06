package wjc.example;

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
    private static final long DEFAULT = 0;
    // 2017-01-01 00:00:00.000距1900-01-01 00:00:00毫秒数
    private static final long START = new GregorianCalendar(2017, 0, 1, 0, 0, 0).getTimeInMillis();

    /**
     * 用于生成1毫秒内的序号，值的范围[0, 127]
     */
    private final AtomicInteger seq = new AtomicInteger(0);

    /**
     * 区域编号
     */
    private long region = DEFAULT;

    /**
     * 应用编号
     */

    private long application = DEFAULT;
    /**
     * 保留位
     */
    private long reservation = DEFAULT;

    public DistributedIdGenerator() {
    }

    public DistributedIdGenerator(long region, long application, long reservation) {
        this.region = region;
        this.application = application;
        this.reservation = reservation;
    }

    public long getRegion() {
        return region;
    }

    public void setRegion(byte region) {
        this.region = region;
    }

    public long getApplication() {
        return application;
    }

    public void setApplication(byte application) {
        this.application = application;
    }

    public long getReservation() {
        return reservation;
    }

    public void setReservation(byte reservation) {
        this.reservation = reservation;
    }

    /**
     * 获取从2017.01.01 00:00:00以来的毫秒数
     *
     * @return
     */
    public long getMillisecond() {
        return System.currentTimeMillis() - START;
    }

    /**
     * 下一个序列号
     *
     * @return
     */
    public long getNextNumber() {
        int n = seq.getAndIncrement();
        // 在1毫秒内生成的序列号只占7位，值[0, 127]，超过要重新开始
        seq.compareAndSet(128, 0);
        return n;
    }

    public long next() {
        return (getReservation() << (40 + 6 + 7 + 7))   // 保留位
                + ((getMillisecond() << (6 + 7 + 7)))   // 当前距2017.01.01 00:00:00以来的毫秒数
                + (getRegion() << (7 + 7))              // 区域编号
                + (getApplication() << 7)               // 保留位编号
                + getNextNumber();                      // 毫秒内的序号
    }


    private List<Long> next(int size) {
        List<Long> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            result.add(next());
        }

        return result;
    }

    public static String toString(long n) {
        StringBuilder builder = new StringBuilder(64);
        String s = Long.toBinaryString(n);
        for (int i = 0; i < 64 - s.length(); i++) {
            builder.append(0);
        }
        builder.append(s);
        return builder.toString();
    }
}
