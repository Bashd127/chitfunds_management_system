package com.example.forms;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class basic_auction {
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
	    int id;
	    String memberid;
	    String name;
	    String bidamount;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getMemberid() {
			return memberid;
		}
		public void setMemberid(String memberid) {
			this.memberid = memberid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBidamount() {
			return bidamount;
		}
		public void setBidamount(String bidamount) {
			this.bidamount = bidamount;
		}
}
