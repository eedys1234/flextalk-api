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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userKey;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_pw")
	private String userPw;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "user_type", columnDefinition = "char")
	@Convert(converter = UserTypeConverter.class)
	private UserType userType;

	@Column(name = "user_email")
	private String userEmail;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;

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
	
	
	public User(String userId, String userPw, String userEmail) {
		this.userId = userId;
		this.userPw = userPw;
		this.userEmail = userEmail;
		this.userType = UserType.FLEXTALK_ID;
		this.regDate = new Date();
		this.status = "0";
	}
}
