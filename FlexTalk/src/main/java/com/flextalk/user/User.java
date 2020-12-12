package com.flextalk.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
//@EqualsAndHashCode(exclude = "participants")
@Table(name = "tb_FT_User")
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
	
	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Participant> participants;
	
	public enum UserType {
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
	
	public User(String userId, String userPw) {
		this.userId = userId;
		this.userPw = userPw;
		this.userType = UserType.FLEXTALK_ID;
		this.regDate = new Date();
	}
}
