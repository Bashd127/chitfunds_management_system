package com.example.forms;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class elite {
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
	    int id;
	    String memberid;
	    String name;
	    String phonenumber;
	    String mail;
	    String monthjoin;
	    String ab;
	    String af;
	    String pf;
	    String pb;
	    public String getAb() {
			return ab;
		}
		public void setAb(String ab) {
			this.ab = ab;
		}
		public String getAf() {
			return af;
		}
		public void setAf(String af) {
			this.af = af;
		}
		public String getPf() {
			return pf;
		}
		public void setPf(String pf) {
			this.pf = pf;
		}
		public String getPb() {
			return pb;
		}
		public void setPb(String pb) {
			this.pb = pb;
		}
		 
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMonthjoin() {
		return monthjoin;
	}
	public void setMonthjoin(String monthjoin) {
		this.monthjoin = monthjoin;
	}
		 
}
