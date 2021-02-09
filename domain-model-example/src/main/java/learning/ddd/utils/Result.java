package learning.ddd.utils;

public class Result<T> {
	private T data;
	private boolean success=false;
	private String errorMessage;
	
	private Result(T data,boolean isSuccess,String errorMessage) {
		this.data=data;
		this.success=isSuccess;
		this.errorMessage=errorMessage;
	}
	
	
	
	public T getData() {
		return data;
	}



	public void setData(T data) {
		this.data = data;
	}



	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	public static <T> Result<T> ok(T t){
		return new Result<T>(t,true,null);
	}
	
	
	public static <T> Result<T> error(String errorMessage){
		return new Result<T>(null,false,errorMessage);
	}

	public static final <T> ResponseData<T> fail(String errorMessage){
		return new ResponseData<T>(null,false,errorMessage);
	}
	
	
	public static final <T> ResponseData<T> success(T t){
		return new ResponseData<T>(t,true,null);
	}
	
	
	public static class ResponseData<T>{
		private T data;
		private boolean success=false;
		private String errorMessage;
		
		
		
		public ResponseData(T data,boolean isSuccess,String errorMessage) {
			this.data=data;
			this.success=isSuccess;
			this.errorMessage=errorMessage;
		}
		
	}
	
	
	public static Result combine(Result result1,Result result2) {
		 if(!result1.isSuccess()||!result2.isSuccess()) {
			System.out.println(result1.isSuccess());
			System.out.println(result2.isSuccess());
			 
			 return Result.error("Invalid request"); 
		}
		 
		 
		return Result.ok(""); 
	}
	
}
