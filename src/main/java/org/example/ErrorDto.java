package org.example;

public class ErrorDto {
    public String reason;
    public ErrorDto(){}
    public ErrorDto(String reason){
       this.reason = reason;
    }
 
    @Override
    public String toString(){
       return "{reason: "+this.reason+"}";
    }
}
