package cn.jwis.qualityworkflow.bean;

public class UserInfo {
	private String id;

	private String account;

	private String name;


	private int isValid;


	private String mobile;

	private String email;

	private int gender;

	private String avatar;


	private String orgName;

	private String orgParent;

	private String companyId;

	private String companyCode;

	private String companyName;

	private int isCompanyManager;

	private String windchillUserName;

	private String windchillPassword;

	private String windchillUserId;

	private String windchillAuthorization;

	private String fullName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAccount() {
		return account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getWindchillUserName() {
		return windchillUserName;
	}

	public void setWindchillUserName(String windchillUserName) {
		this.windchillUserName = windchillUserName;
	}

	public String getWindchillPassword() {
		return windchillPassword;
	}

	public void setWindchillPassword(String windchillPassword) {
		this.windchillPassword = windchillPassword;
	}

	public String getWindchillUserId() {
		return windchillUserId;
	}

	public void setWindchillUserId(String windchillUserId) {
		this.windchillUserId = windchillUserId;
	}

	public String getWindchillAuthorization() {
		return windchillAuthorization;
	}

	public void setWindchillAuthorization(String windchillAuthorization) {
		this.windchillAuthorization = windchillAuthorization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public int getIsAdmin() { return isAdmin; }
	 * 
	 * public void setIsAdmin( int isAdmin ) { this.isAdmin = isAdmin; }
	 */

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	/*
	 * public String getPhone() { return phone; }
	 * 
	 * public void setPhone( String phone ) { this.phone = phone; }
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/*
	 * public Long getOrgId() { return orgId; }
	 * 
	 * public void setOrgId( Long orgId ) { this.orgId = orgId; }
	 */

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getIsCompanyManager() {
		return isCompanyManager;
	}

	public void setIsCompanyManager(int isCompanyManager) {
		this.isCompanyManager = isCompanyManager;
	}

	public String getOrgParent() {
		return orgParent;
	}

	public void setOrgParent(String orgParent) {
		this.orgParent = orgParent;
	}

}
