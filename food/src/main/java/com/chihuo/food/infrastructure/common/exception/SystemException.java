package com.chihuo.food.infrastructure.common.exception;

import com.chihuo.food.infrastructure.common.api.ResponseStatus;

/** 
 * @ClassName: SystemException
 * @Description: 系统异常
 * @author zengbin
 * @date 2019年11月12日 上午9:35:45
*/
public class SystemException extends RuntimeException {
    
    private static final long serialVersionUID = 604122701395795861L;
    
    private ResponseStatus resultCode;
    
    public SystemException() {
        super();
    }
    
    public SystemException(String message) {
        super(String.format("====[errorMessage ：  %s]", message));
    }
    
    public SystemException(ResponseStatus resultCode , String message) {
    	super(String.format("===[errorCode ： %s ]===[errorMessage ：  %s]===", resultCode.getCode(), message));
    	this.resultCode = resultCode;
    }
    
    public SystemException(ResponseStatus resultCode , Integer userId , String message) {
        super(String.format("===[errorCode ： %s ]===[userId ： %s]===[errorMessage ：  %s]===", resultCode.getCode(), userId , message));
        this.resultCode = resultCode;
    }
    
    public SystemException(ResponseStatus resultCode , String deviceId , String message) {
        super(String.format("===[errorCode ： %s ]===[deviceId ： %s]===[errorMessage ：  %s]===", resultCode.getCode(), deviceId , message));
        this.resultCode = resultCode;
    }

    public SystemException(ResponseStatus resultCode , Integer userId , String message, Throwable cause) {
        super(String.format("===[errorCode ： %s ]===[userId ： %s]===[errorMessage ：  %s]===", resultCode.getCode() , userId , message), cause);
        this.resultCode = resultCode;
    }
    
    public SystemException(ResponseStatus resultCode , String deviceId , String message, Throwable cause) {
        super(String.format("===[errorCode ： %s ]===[deviceId ： %s]===[errorMessage ：  %s]===", resultCode.getCode() , deviceId , message), cause);
        this.resultCode = resultCode;
    }

    public ResponseStatus getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResponseStatus resultCode) {
        this.resultCode = resultCode;
    }
    
}
