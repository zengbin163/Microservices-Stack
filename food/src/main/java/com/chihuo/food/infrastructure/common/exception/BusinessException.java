package com.chihuo.food.infrastructure.common.exception;

import com.chihuo.food.infrastructure.common.api.ResponseStatus;

/** 
* @ClassName: BusinessException
* @Description: 业务异常
* @author zengbin
* @date 2019年11月12日 上午9:25:45
*/
public class BusinessException extends RuntimeException {
    
    private static final long serialVersionUID = 604122701395795861L;
    private ResponseStatus resultCode;
    
    public BusinessException() {
        super();
    }
    
    public BusinessException(String message) {
        super(String.format("===[errorMessage ：  %s]", message));
    }
    
    public BusinessException(ResponseStatus resultCode , String message) {
    	super(String.format("===[errorCode ： %s ]===[errorMessage ：  %s]===", resultCode.getCode(), message));
    	this.resultCode = resultCode;
    }

    public BusinessException(ResponseStatus resultCode , Integer userId , String message) {
        super(String.format("===[errorCode ： %s ]===[userId ： %s]===[errorMessage ：  %s]===", resultCode.getCode(), userId , message));
        this.resultCode = resultCode;
    }
    
    public BusinessException(ResponseStatus resultCode , String mobile , String message) {
        super(String.format("===[errorCode ： %s ]===[mobile ： %s]===[errorMessage ：  %s]===", resultCode.getCode(), mobile , message));
        this.resultCode = resultCode;
    }

    public BusinessException(ResponseStatus resultCode , Integer userId , String message, Throwable cause) {
        super(String.format("===[errorCode ： %s ]===[userId ： %s]===[errorMessage ：  %s]===", resultCode.getCode() , userId , message), cause);
        this.resultCode = resultCode;
    }
    
    public BusinessException(ResponseStatus resultCode , String mobile , String message, Throwable cause) {
        super(String.format("===[errorCode ： %s ]===[mobile ： %s]===[errorMessage ：  %s]===", resultCode.getCode() , mobile , message), cause);
        this.resultCode = resultCode;
    }
    
    public ResponseStatus getResultCode() {
        return resultCode;
    }
    
    public void setResultCode(ResponseStatus resultCode) {
        this.resultCode = resultCode;
    }
}
