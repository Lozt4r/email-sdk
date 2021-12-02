package com.vrdete.email.exception;

import com.vrdete.email.constant.IResultCode;

import java.text.MessageFormat;


public interface BusinessExceptionAssert extends IResultCode, Assert {

    @Override
    default BusinessException newException(String msg) {
        String msgInfo = MessageFormat.format(this.getMessage(), msg);
        return new BusinessException(this, new Object[]{msg}, msg);
    }

    @Override
    default BusinessException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BusinessException newException(String msg, Object... args) {
        return new BusinessException(this, args, msg);
    }

    @Override
    default BusinessException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, t);
    }

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     *
     * @param obj 待判断对象
     */
    @Override
    default void assertNotNull(Object obj) throws BusinessException {
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
    @Override
    default void assertNotNull(Object obj, Object... args) throws BusinessException {
        if (obj == null) {
            throw newException(args);
        }
    }

    @Override
    default void assertNotNull(Object obj, String code) throws BusinessException {
        if (obj == null) {
            throw newException(code);
        }
    }

    @Override
    default void assertNotNull(Object obj, String code, Object... args) throws BusinessException {
        if (obj == null) {
            throw newException(code, args);
        }
    }

    @Override
    default void assertIsTrue(boolean bool) throws BusinessException {
        if (bool) {
            throw newException();
        }
    }

    @Override
    default void assertIsTrue(boolean bool, String code) throws BusinessException {
        if (bool) {
            throw newException(code);
        }
    }

    @Override
    default void assertIsTrue(boolean bool, Object... args) throws BusinessException {
        if (bool) {
            throw newException(args);
        }
    }

    @Override
    default void assertIsTrue(boolean bool, String code, Object... args) throws BusinessException {
        if (bool) {
            throw newException(code, args);
        }
    }

    @Override
    default void assertIsFalse(boolean bool) throws BusinessException {
        if (!bool) {
            throw newException();
        }
    }

    @Override
    default void assertIsFalse(boolean bool, String code) throws BusinessException {
        if (!bool) {
            throw newException(code);
        }
    }

    @Override
    default void assertIsFalse(boolean bool, Object... args) throws BusinessException {
        if (!bool) {
            throw newException(args);
        }
    }

    @Override
    default void assertIsFalse(boolean bool, String code, Object... args) throws BusinessException {
        if (!bool) {
            throw newException(code, args);
        }
    }

}
