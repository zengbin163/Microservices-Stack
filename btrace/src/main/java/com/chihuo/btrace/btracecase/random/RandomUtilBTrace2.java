package com.chihuo.btrace.btracecase.random;

import static org.openjdk.btrace.core.BTraceUtils.println;
import static org.openjdk.btrace.core.BTraceUtils.str;
import static org.openjdk.btrace.core.BTraceUtils.strcat;

import org.openjdk.btrace.core.annotations.BTrace;
import org.openjdk.btrace.core.annotations.Duration;
import org.openjdk.btrace.core.annotations.Kind;
import org.openjdk.btrace.core.annotations.Location;
import org.openjdk.btrace.core.annotations.OnMethod;
import org.openjdk.btrace.core.annotations.Self;
import org.openjdk.btrace.core.annotations.TargetInstance;
import org.openjdk.btrace.core.annotations.TargetMethodOrField;
import org.openjdk.btrace.core.annotations.Where;

@BTrace
public class RandomUtilBTrace2 {

	@OnMethod (
			clazz = "com.chihuo.btrace.btracecase.random.RandomUtil",
			method = "add", 
            location = @Location(value = Kind.CALL, 
            					 clazz = "/.*/", 
            					 method = "/.*/", 
            					 where = Where.AFTER)
	)
    public static void onBind (
    		@Self Object self, 
    		@TargetInstance Object instance, 
    		@TargetMethodOrField String method, 
    		@Duration long duration) {
        println(strcat("self: ", str(self)));
        println(strcat("instance: ", str(instance)));
        println(strcat("method: ", str(method)));
        println(strcat("duration(ms): ", str(duration / 1000000)));
    }

}
