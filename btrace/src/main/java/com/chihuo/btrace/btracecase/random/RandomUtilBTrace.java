package com.chihuo.btrace.btracecase.random;

import static org.openjdk.btrace.core.BTraceUtils.jstack;
import static org.openjdk.btrace.core.BTraceUtils.println;
import static org.openjdk.btrace.core.BTraceUtils.str;
import static org.openjdk.btrace.core.BTraceUtils.strcat;
import static org.openjdk.btrace.core.BTraceUtils.Time;

import org.openjdk.btrace.core.annotations.BTrace;
import org.openjdk.btrace.core.annotations.Duration;
import org.openjdk.btrace.core.annotations.Export;
import org.openjdk.btrace.core.annotations.Kind;
import org.openjdk.btrace.core.annotations.Location;
import org.openjdk.btrace.core.annotations.OnExit;
import org.openjdk.btrace.core.annotations.OnMethod;
import org.openjdk.btrace.core.annotations.Return;
import org.openjdk.btrace.core.annotations.Self;

@BTrace
public class RandomUtilBTrace {

	@OnExit
	public static void onexit(int code) {
		println("----------------BTrace program exits! with code: " + code);
	}

	@Export
	public static long counter;

	@OnMethod (
			clazz = "com.chihuo.btrace.btracecase.random.RandomUtil", 
			method = "add", 
			location = @Location(value = Kind.RETURN)
	)
	public static void m(@Self Object self, int a, int b, @Return int result, @Duration long time) {
        println(Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        println("method self: " + str(self));
        println("pram-a: " + a + " pram-b: " + b);
        println("method return: " + str(result)); 
        println("cost time:  " + time * 1.0 / 1000 + " ms");
		println(strcat("method execute counter:", str(counter)));
		println("jstack start=====================================================================");	
		jstack();
		println("jstack end=======================================================================");	
		counter++;
	}

}
