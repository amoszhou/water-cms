package com.water.util;

import com.google.common.math.DoubleMath;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * 不可变对象(Immutable Object)
 * 单币种货币类，处理货币算术、币种和取整。
 * <p>
 * 货币类中封装了货币金额和币种。目前金额在内部是long类型表示， 单位是所属币种的最小货币单位（对人民币是分）。
 * <p>
 * 目前，货币实现了以下主要功能：<br>
 * <ul>
 * <li>支持货币对象与double(float)/long(int)/String/BigDecimal之间相互转换。
 * <li>货币类在运算中提供与JDK中的BigDecimal类似的运算接口， BigDecimal的运算接口支持任意指定精度的运算功能，能够支持各种
 * 可能的财务规则。
 * <li>货币类在运算中也提供一组简单运算接口，使用这组运算接口，则在 精度处理上使用缺省的处理规则。
 * <li>推荐使用Money，不建议直接使用BigDecimal的原因之一在于，
 * 使用BigDecimal，同样金额和币种的货币使用BigDecimal存在多种可能 的表示，例如：new BigDecimal("10.5")与new
 * BigDecimal("10.50") 不相等，因为scale不等。使得Money类，同样金额和币种的货币只有 一种表示方式，new
 * Money("10.5")和new Money("10.50")应该是相等的。
 * <p>
 * <li>提供基本的格式化功能。
 * <li>Money类中不包含与业务相关的统计功能和格式化功能。业务相关的功能 建议使用utility类来实现。
 * <li>Money类实现了Serializable接口，支持作为远程调用的参数和返回值。
 * <li>Money类实现了equals和hashCode方法。
 * </ul>
 */
public final class Money implements Serializable, Comparable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6009335074727417445L;


    private static final Integer CENT_FACTOR = 100;

    private static final RoundingMode GLOBAL_ROUNDING_MODE = RoundingMode.DOWN;

    /**
     * 金额，以分为单位。
     */
    private final double cent;


    public static Money ZERO = Money.valueOf(0L);

    // 构造器 ====================================================

    private Money(double cent) {
        this.cent = cent;
    }


    /**
     * @param yuan 金额元数。不能为null
     * @param cent 金额分数。不能为null
     */
    public static Money valueOf(long yuan, int cent) {
        long realCent = (yuan * CENT_FACTOR) + (cent % CENT_FACTOR);
        Money money = new Money(realCent);
        return money;
    }


    /**
     * @param amount 金额，以元为单位。不能为null
     */
    public static Money valueOf(BigDecimal amount) {
        return valueOf(amount.doubleValue());
    }


    /**
     * @param amount 金额，以元为单位。不能为null
     */
    public static Money valueOf(double amount) {
        Money money = new Money(amount * CENT_FACTOR);
        return money;
    }


    /**
     * 创建一个具有金额<code>amount</code>的货币对象。 如果金额不能转换为整数分，则使用四舍五入方式取整。
     *
     * @param amount 金额字符串，以元为单位。可以为null或空字符串
     * @return 如果<code>amunt</code>是null或空字符串,返回金额为0的货币对象
     * @throws NumberFormatException
     */
    public static Money valueOf(String amount) {
        if (StringUtils.isEmpty(amount)) {
            return new Money(0);
        }

        return valueOf(Double.parseDouble(amount));
    }


    /**
     * 创建一个具有金额<code>cent</code>的货币对象。
     *
     * @param cent 金额，以分为单位。不能为null
     */
    public static Money valueOfCent(Long cent) {
        return new Money(cent == null ? 0 : cent);
    }

    /**
     * 创建一个具有金额<code>cent</code>的货币对象。
     *
     * @param cent 金额，以分为单位。不能为null
     */
    public static Money valueOfCent(Integer cent) {
        return new Money(cent == null ? 0 : cent);
    }

    public static Money valueOfCent(double cent) {
        return new Money(cent);
    }


    // Bean方法 ====================================================

    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，以元为单位。
     */
    public BigDecimal getAmountWithBigDecimal() {
        return BigDecimal.valueOf(getAmount());
    }

    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，以元为单位。
     */
    public double getAmount() {
        return new BigDecimal(cent).divide(new BigDecimal(CENT_FACTOR), 2, GLOBAL_ROUNDING_MODE).doubleValue();
    }


    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，以分为单位。
     */
    public long getCent() {
        return DoubleMath.roundToLong(cent, GLOBAL_ROUNDING_MODE);
    }


    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，以分为单位。
     */
    public int getInt() {
        return (int) getCent();
    }


    // 基本对象方法 ===================================================

    /**
     * 判断本货币对象与另一对象是否相等。
     * <p>
     * 本货币对象与另一对象相等的充分必要条件是：<br>
     * <ul>
     * <li>另一对象也属货币对象类。
     * <li>金额相同。
     * <li>币种相同。
     * </ul>
     *
     * @param other 待比较的另一对象。
     * @return <code>true</code>表示相等，<code>false</code>表示不相等。
     * @see Object#equals(Object)
     */
    public boolean equals(Object other) {
        return (other instanceof Money) && equals((Money) other);
    }

    /**
     * 判断本货币对象与另一货币对象是否相等。
     * <p>
     * 本货币对象与另一货币对象相等的充分必要条件是：<br>
     * <ul>
     * <li>金额相同。
     * <li>币种相同。
     * </ul>
     *
     * @param other 待比较的另一货币对象。
     * @return <code>true</code>表示相等，<code>false</code>表示不相等。
     */
    public boolean equals(Money other) {
        return cent == other.cent;
    }

    /**
     * 计算本货币对象的杂凑值。
     *
     * @return 本货币对象的杂凑值。
     * @see Object#hashCode()
     */
    public int hashCode() {
        long cent = getCent();
        return (int) (cent ^ (cent >>> 32));
    }

    // Comparable接口 ========================================

    /**
     * 对象比较。
     * <p>
     * 比较本对象与另一对象的大小。 如果待比较的对象的类型不是<code>Money</code>，则抛出<code>java.lang.ClassCastException</code>。
     * 如果待比较的两个货币对象的币种不同，则抛出<code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额少于待比较货币对象，则返回-1。 如果本货币对象的金额等于待比较货币对象，则返回0。
     * 如果本货币对象的金额大于待比较货币对象，则返回1。
     *
     * @param other 另一对象。
     * @return -1表示小于，0表示等于，1表示大于。
     * @throws ClassCastException 待比较货币对象不是<code>Money</code>。
     *                            IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Object other) {
        return compareTo((Money) other);
    }

    /**
     * 货币比较。
     * <p>
     * 比较本货币对象与另一货币对象的大小。 如果待比较的两个货币对象的币种不同，则抛出<code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额少于待比较货币对象，则返回-1。 如果本货币对象的金额等于待比较货币对象，则返回0。
     * 如果本货币对象的金额大于待比较货币对象，则返回1。
     *
     * @param other 另一对象。
     * @return -1表示小于，0表示等于，1表示大于。
     * @throws IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     */
    public int compareTo(Money other) {

        if (cent < other.cent) {
            return -1;
        } else if (cent == other.cent) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 货币比较。
     * <p>
     * 判断本货币对象是否大于另一货币对象。 如果待比较的两个货币对象的币种不同，则抛出<code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额大于待比较货币对象，则返回true，否则返回false。
     *
     * @param other 另一对象。
     * @return true表示大于，false表示不大于（小于等于）。
     * @throws IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     */
    public boolean greaterThan(Money other) {
        return compareTo(other) > 0;
    }

    public boolean lessThan(Money other) {
        return compareTo(other) < 0;
    }


    // 货币算术 ==========================================

    /**
     * 货币加法。
     * <p>
     * 如果两货币币种相同，则返回一个新的相同币种的货币对象，其金额为 两货币对象金额之和，本货币对象的值不变。 如果两货币对象币种不同，抛出<code>java.lang.IllegalArgumentException</code>。
     * 内部以长整型运算,注意不要产生溢出
     *
     * @param other 作为加数的货币对象。
     * @return 相加后的结果。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money add(Money other) {

        return new Money(cent + other.cent);
    }

    public Money add(Long other) {
        return this.add(Money.valueOfCent(other));
    }


    /**
     * 货币减法。
     * <p>
     * 如果两货币币种相同，则返回一个新的相同币种的货币对象，其金额为 本货币对象的金额减去参数货币对象的金额。本货币对象的值不变。
     * 如果两货币币种不同，抛出<code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为减数的货币对象。
     * @return 相减后的结果。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money subtract(Money other) {

        return new Money(cent - other.cent);
    }

    public Money subtract(Long other) {
        return this.subtract(Money.valueOfCent(other));
    }


    /**
     * 货币乘法。
     *
     * @param number
     * @return
     */
    public Money multiply(double number) {
        BigDecimal result = new BigDecimal(cent).multiply(new BigDecimal(number)).setScale(2, GLOBAL_ROUNDING_MODE);
        return Money.valueOfCent(result.doubleValue());
    }

    /**
     * 货币乘法。
     *
     * @param number
     * @return
     */
    public Money multiply(long number) {
        BigDecimal result = new BigDecimal(cent).multiply(new BigDecimal(number));
        return Money.valueOfCent(result.longValue());
    }


    /**
     * 货币除法。
     * <p>
     * 四舍五入的除法,精确到分.注意除数不能为0
     * 返回一个新的货币对象，币种与本货币对象相同
     *
     * @param other
     * @return
     */
    public Money divide(Money other) {

        BigDecimal thisAmount = getAmountWithBigDecimal();
        BigDecimal otherAmount = other.getAmountWithBigDecimal();

        return Money.valueOf(thisAmount.divide(otherAmount, 2, GLOBAL_ROUNDING_MODE).doubleValue());
    }

    public Money divide(Money other, int scale, RoundingMode roundingMode) {

        BigDecimal thisAmount = getAmountWithBigDecimal();
        BigDecimal otherAmount = other.getAmountWithBigDecimal();

        return Money.valueOf(thisAmount.divide(otherAmount, scale, roundingMode).doubleValue());
    }

    public Money divide(double number) {
        return divide(Money.valueOf(number));
    }

    /**
     * @param number
     * @param scale        小数点位数
     * @param roundingMode 取整模式
     * @return
     */
    public Money divide(Long number, int scale, RoundingMode roundingMode) {
        return divide(Money.valueOf(number), scale, roundingMode);
    }

    /**
     * 金额的相反数
     *
     * @return
     */
    public Money opposite() {
        return new Money(-cent);
    }

    // 格式化方法 =================================================

    /**
     * 生成本对象的缺省字符串表示
     */
    public String toString() {
        return getAmountWithBigDecimal().toString();
    }

}
