package com.chihuo.btrace.btracecase.slow;

import static org.openjdk.btrace.core.BTraceUtils.println;
import static org.openjdk.btrace.core.BTraceUtils.str;
import static org.openjdk.btrace.core.BTraceUtils.strcat;

import org.openjdk.btrace.core.annotations.BTrace;
import org.openjdk.btrace.core.annotations.Duration;
import org.openjdk.btrace.core.annotations.Kind;
import org.openjdk.btrace.core.annotations.Location;
import org.openjdk.btrace.core.annotations.OnMethod;
import org.openjdk.btrace.core.annotations.ProbeClassName;
import org.openjdk.btrace.core.annotations.ProbeMethodName;

@BTrace
public class SlowQueryBtrace {
	
	@OnMethod (
			clazz = "/com\\.chihuo\\.btrace\\.btracecase\\..*/",
			method = "/.*/",
			location = @Location(Kind.RETURN)
	)
    public static void slowQuery (
    		@ProbeClassName String pcn,
    		@ProbeMethodName String probeMethod, 
    		@Duration long duration
    ) {
		if (duration > 1000000 * 100) {
			println(strcat("class:", pcn));
			println(strcat("method:", probeMethod));
			println(strcat("duration:", str(duration / 1000000)));
		} else {
			println(strcat("no method:", str(duration)));
		}
    }

}
