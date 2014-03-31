package com.cyning.android.system;

import com.lurencun.android.toolkit.BuildConfig;

import junit.framework.Assert;


import android.content.Context;
import android.util.Log;

public class L {

	static final String TAG = L.class.getSimpleName();

	/**
	 * 输出调试信息(System.out)，并在调试输入信息中附带当前代码在哪个类哪一行的额外数据。
	 * 
	 * @param message
	 *            调试信息
	 */
	public static void l(String message) {
		StackTraceElement ele = Thread.currentThread().getStackTrace()[3];
		int line = ele.getLineNumber();
		String clazz = ele.getClassName();
		System.out.println(":::: @" + clazz + " -> " + line + " :::: "
				+ message);
	}

	/**
	 * 取得当前代码所在的方法名
	 * 
	 * @return 当前方法名
	 */
	public static String getCurrentMethodName() {
		// 0 getThreadStackTrce
		// 1 getStackTrace
		// 2 * this method: getCurrentMethodName
		// 3 your method
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	/**
	 * 输出方法调用链
	 * 
	 * @param object
	 *            对象
	 */
	public static void logCurrentMethodChain(Object object) {
		StackTraceElement[] es = Thread.currentThread().getStackTrace();
		long time = System.currentTimeMillis();
		Log.d(TAG, String.format(
				"###### Object(%s) Method Chain ###### @Time( %d )", object
						.getClass().getSimpleName(), time));
		for (StackTraceElement e : es) {
			String msg = String.format("### Method Chain ### Caller:%s  ->：%s",
					e.getClassName(), e.getMethodName());
			Log.d(TAG, msg);
		}
	}

	/**
	 * 输出当前方法调用
	 * 
	 * @param object
	 *            对象
	 */
	public static void logCurrentMethod(Object object) {
		String methodName = Thread.currentThread().getStackTrace()[3]
				.getMethodName();
		long time = System.currentTimeMillis();
		String msg = String.format(
				"###### Calling Method ###### Object(%s) -> %s @Time( %d )",
				object.getClass().getSimpleName(), methodName, time);
		Log.d(TAG, msg);
	}

	private static boolean debug = BuildConfig.DEBUG;
	private static final String mTag = "zzy";

	public static void onCreate(Context con) {
		String className = con.getClass().getSimpleName();
		if (debug)
			Log.i(className, className + " onCreate");
	}

	public static void onStart(Context con) {
		String className = con.getClass().getSimpleName();
		if (debug)
			Log.i(className, className + " onStart");
	}

	public static void onResume(Context con) {
		String className = con.getClass().getSimpleName();
		if (debug)
			Log.i(className, className + " onResume");
	}

	public static void onPause(Context con) {
		String className = con.getClass().getSimpleName();
		if (debug)
			Log.i(className, className + " onPause");
	}

	public static void onStop(Context con) {
		String className = con.getClass().getSimpleName();
		if (debug)
			Log.i(className, className + " onStop");
	}

	public static void onDestory(Context con) {
		String className = con.getClass().getSimpleName();
		if (debug)
			Log.i(className, className + " onDistory");
	}

	public static void onReStart(Context con) {
		String className = con.getClass().getSimpleName();
		if (debug)
			Log.i(className, className + " onReStart");
	}

	public static void i(String tag, String format, Object... args) {
		if (debug)
			Log.i(tag, String.format(format, args));
	}

	public static void i(String tag, String msg) {
		if (debug)
			Log.i(tag, msg);
	}

	public static void i(String format, Object... args) {
		if (debug)
			i(mTag, format, args);
	}

	public static void i(String msg) {
		if (debug)
			Log.i(mTag, msg);
	}

	public static void e(String tag, String format, Object... args) {
		if (debug)
			Log.e(tag, String.format(format, args));
	}

	public static void e(String format, Object... args) {
		if (debug)
			e(mTag, format, args);
	}

	public static void e(String tag, String msg) {
		if (debug)
			Log.e(tag, msg);
	}

	public static void e(String msg) {
		if (debug)
			Log.e(mTag, msg);
	}

	public static void d(String tag, String msg) {
		if (debug)
			Log.d(tag, msg);
	}

	public static void assertEquals(String expected, String actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(boolean expected, boolean actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(byte expected, byte actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(char expected, char actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(int expected, int actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(long expected, long actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(short expected, short actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(double expected, double actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(float expected, float actual) {
		if (debug) {
			Assert.assertEquals(expected, actual);
		}
	}

	public static void assertEquals(String mssage, String expected, String actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertEquals(String mssage, boolean expected, boolean actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertEquals(String mssage, byte expected, byte actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertEquals(String mssage, char expected, char actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertEquals(String mssage, int expected, int actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertEquals(String mssage, long expected, long actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertEquals(String mssage, short expected, short actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertEquals(String mssage, double expected, double actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertEquals(String mssage, float expected, float actual) {
		if (debug) {
			Assert.assertEquals(mssage, expected, actual);
		}
	}

	public static void assertNull(Object object) {
		if (debug) {
			Assert.assertNull(object);
		}
	}

	public static void assertNull(String msg, Object object) {
		if (debug) {
			Assert.assertNull(msg, object);
		}
	}

	public static void assertTrue(boolean condition) {
		if (debug) {
			Assert.assertTrue(condition);
		}
	}

	public static void assertTrue(String msg, boolean condition) {
		if (debug) {
			Assert.assertTrue(msg, condition);
		}
	}

	public static void assertFalse(boolean condition) {
		if (debug) {
			Assert.assertFalse(condition);
		}
	}

	public static void assertFalse(String msg, boolean condition) {
		if (debug) {
			Assert.assertFalse(msg, condition);
		}
	}

	public static void assertNotNull(Object object) {
		if (debug) {
			Assert.assertNotNull(object);
		}
	}

	public static void assertNotNull(String msg, Object object) {
		if (debug) {
			Assert.assertNotNull(msg, object);
		}
	}

	public static void assertSame(Object expected, Object actual) {
		if (debug) {
			Assert.assertSame(expected, actual);
		}
	}

	public static void assertSame(String msg, Object expected, Object actual) {
		if (debug) {
			Assert.assertSame(msg, expected, actual);
		}
	}

	public static void assertNotSame(Object expected, Object actual) {
		if (debug) {
			Assert.assertNotSame(expected, actual);
		}
	}

	public static void assertNotSame(String msg, Object expected, Object actual) {
		if (debug) {
			Assert.assertNotSame(msg, expected, actual);
		}
	}

	public static void assertShouldNotBeExed() {
		assertNotNull("this code should not be exed, please check the logic", null);
	}
}