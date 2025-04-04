package com.aiweb.domain;

import java.util.Date;

import lombok.Data;
//@Data 하나로 getter&setter 저절로 생성

@Data
public class BoardVO {
	private long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;	
}
