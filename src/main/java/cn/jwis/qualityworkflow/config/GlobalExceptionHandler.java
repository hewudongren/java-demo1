package cn.jwis.qualityworkflow.config;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jwis.qualityworkflow.bean.ResultBean;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
	@ExceptionHandler
	@ResponseBody
	public ResultBean runtimeExceptionHandler(Exception e) {
		if (e.getMessage().contains("登陆")) {
			return new ResultBean(100011, e.getMessage(), null);
		}
		return new ResultBean(-1, e.getMessage(), null);
	}

	@ExceptionHandler
	@ResponseBody
	public ResultBean handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		String errorMesssage = "";
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorMesssage += fieldError.getDefaultMessage() + "\n";
		}
		if (errorMesssage.lastIndexOf("\n") != -1) {
			errorMesssage = errorMesssage.substring(0, errorMesssage.lastIndexOf("\n"));
		}
		return new ResultBean(-1, errorMesssage, null);
	}
//
//    //Hivernate Validate验证
//    @ResponseBody
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResultInfo methodArgumentNotValidExceptionHanler(MethodArgumentNotValidException e) {
//        e.printStackTrace();
//        return new ResultInfo(1, e.getBindingResult().getFieldError().getDefaultMessage());
//    }
}

