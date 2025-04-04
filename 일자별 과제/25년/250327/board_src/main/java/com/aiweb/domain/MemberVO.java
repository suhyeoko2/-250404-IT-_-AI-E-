package com.aiweb.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private long mno;
	private String email;
	private String pwd;
	private String mname;
	private Date cre_date;
	private Date mod_date;
}
