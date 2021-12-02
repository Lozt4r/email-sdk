package com.vrdete.email.exception;

/**
 * @author fu
 */
public interface Assert {
    /**
     * 创建异常
     * @param msg 提示语
     * @return BaseException
     */
    BaseException newException(String msg);

    /**
     * 创建异常
     *
     * @param args 返回值列表
     * @return BaseException
     */
    BaseException newException(Object... args);

    /**
     * 创建异常
     *
     * @param msg  提示语
     * @param args 返回值列表
     * @return BaseException
     */
    BaseException newException(String msg, Object... args);

    /**
     * 创建异常
     *
     * @param t    异常
     * @param args 返回值列表
     * @return BaseException
     */
    BaseException newException(Throwable t, Object... args);

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     *
     * @param obj 待判断对象
     */
    default void assertNotNull(Object obj) throws BaseException {
        if (obj == null) {
            throw newException(obj);
        }
    }

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     * <p>异常信息<code>message</code>支持传递参数方式，避免在判断之前进行字符串拼接操作
     *
     * @param obj  待判断对象
     * @param args message占位符对应的参数列表
     */
    default void assertNotNull(Object obj, Object... args) throws BaseException {
        if (obj == null) {
            throw newException(args);
        }
    }

    default void assertNotNull(Object obj, String msg) throws BaseException {
        if (obj == null) {
            throw newException(msg);
        }
    }

    default void assertNotNull(Object obj, String msg, Object... args) throws BaseException {
        if (obj == null) {
            throw newException(msg, args);
        }
    }

    default void assertIsTrue(boolean bool) throws BaseException {
        if (bool) {
            throw newException();
        }
    }

    default void assertIsTrue(boolean bool, Object... args) throws BaseException {
        if (bool) {
            throw newException(args);
        }
    }

    default void assertIsTrue(boolean bool, String msg) throws BaseException {
        if (bool) {
            throw newException(msg);
        }
    }

    default void assertIsTrue(boolean bool, String msg, Object... args) throws BaseException {
        if (bool) {
            throw newException(msg, args);
        }
    }

    default void assertIsFalse(boolean bool) throws BaseException {
        if (!bool) {
            throw newException();
        }
    }

    default void assertIsFalse(boolean bool, Object... args) throws BaseException {
        if (!bool) {
            throw newException(args);
        }
    }

    default void assertIsFalse(boolean bool, String msg) throws BaseException {
        if (!bool) {
            throw newException(msg);
        }
    }

    default void assertIsFalse(boolean bool, String msg, Object... args) throws BaseException {
        if (!bool) {
            throw newException(msg, args);
        }
    }
}
