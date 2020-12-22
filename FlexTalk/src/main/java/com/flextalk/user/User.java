package com.flextalk.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flextalk.common.AbstractBaseEnumConverter;
import com.flextalk.common.BaseEnumCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
@AllArgsConstructor @Builder 
@Entity
@Table(name = "tb_User")
public class User {

	@Id @GeneratedValue
	private Long user_key;

	private String user_id;
	
	private String user_pw;
	
	private String status;
	
	@Column(columnDefinition = "char")
	@Convert(converter = UserTypeConverter.class)
	private UserType user_type;

	private String user_email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reg_date;

	public enum UserType implements BaseEnumCode<String>{
		FLEXTALK_ID("0"),
		SNS_ID("1");
		
		private String value;
		
		UserType(String value) {
			this.value = value;
		}
		
		public String getKey() {
			return name();
		}
		
		public String getValue() {
			return this.value;
		}
	}
	
	
	public User(String user_id, String user_pw, String user_email) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_email = user_email;
		this.user_type = UserType.FLEXTALK_ID;
		this.reg_date = new Date();
		this.status = "0";
	}
}
