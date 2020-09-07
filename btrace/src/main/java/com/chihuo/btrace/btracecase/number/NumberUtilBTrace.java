package com.chihuo.btrace.btracecase.number;

import org.openjdk.btrace.core.annotations.BTrace;
import org.openjdk.btrace.core.annotations.Kind;
import org.openjdk.btrace.core.annotations.Location;
import org.openjdk.btrace.core.annotations.OnMethod;
import org.openjdk.btrace.core.annotations.Return;

import static org.openjdk.btrace.core.BTraceUtils.*;


@BTrace
public class NumberUtilBTrace {
	
    @OnMethod(
            clazz="com.chihuo.btrace.btracecase.number.NumberUtil",
            method="sum",
            location=@Location(Kind.RETURN)
    )
    public static void func(@Return int result) {
        println("trace: =======================");
        println(strcat("result:", str(result)));
        jstack();
    }

}
