package com.flextalk.user;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flextalk.room.ChatRoom;

import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@Table(name = "tb_FT_ChatRoom")
@ToString(exclude = {"user", "room"})
public class Participant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "participant_key")
	private Long participantKey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_key")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_key")
	private ChatRoom room;
	
	@Column(name = "is_bookmark", nullable = false, length = 1)
	@Enumerated(EnumType.STRING)
	private BookmarkType isBookmark;

	@Column(name = "is_alaram", nullable = false, length = 1)
	@Enumerated(EnumType.STRING)
	private AlaramType isAlaram;
	
	@Column(name ="is_master", nullable = false, length = 1)
	@Enumerated(EnumType.STRING)
	private MasterType isMaster;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date partDate;
	
	private enum BookmarkType {
		CHECK("1"),
		UNCHECK("0");
		
		private String value;
		
		BookmarkType(String value) {
			this.value = value;
		}
		
		public String getKey() {
            return name();
        }

        public String getValue() {
            return value;
        }
	}
	
	private enum AlaramType {
		CHECK("1"),
		UNCHECK("0");
		
		private String value;
		
		AlaramType(String value) {
			this.value = value;
		}
		
		public String getKey() {
            return name();
        }

        public String getValue() {
            return value;
        }
	}
	
	private enum MasterType {
		CHECK("1"),
		UNCHECK("0");
		
		private String value;
		
		MasterType(String value) {
			this.value = value;
		}
		
		public String getKey() {
            return name();
        }

        public String getValue() {
            return value;
        }
	}

	private Participant(ChatRoom room) {
		this.room = Objects.requireNonNull(room);
		this.isMaster = MasterType.UNCHECK;
		this.isAlaram = AlaramType.UNCHECK;
		this.isBookmark = BookmarkType.UNCHECK;
		this.partDate = new Date();
		
		//양방향 조회 가능
		this.room.getParticipantList().add(this);
	}
	
	public static Participant of(ChatRoom room) {
		return new Participant(room);
	}

	public void setupBookmark(BookmarkType isBookmark) {
		this.isBookmark = isBookmark;
	}

	public void setupAlaram(AlaramType isAlaram) {
		this.isAlaram = isAlaram;
	}

	public void setupMaster(MasterType isMaster) {
		this.isMaster = isMaster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participantKey == null) ? 0 : participantKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;			
		}
		
		else if(!(obj instanceof Participant)) {
			return false; 
		}
		
		Participant other = (Participant) obj;		
		return Long.compare(this.participantKey, other.participantKey) == 0;
	}
	
}
