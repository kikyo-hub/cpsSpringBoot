package cn.nrzt.cps.archives.po;

import cn.nrzt.cps.user.po.User;

import java.util.Set;

/**
 * The persistent class for the C_ENTERPRISE database table.
 * 
 */

public class CEnterprise {

	private String id;

	private String address;

	private String name;
	
	private String tel;
	
	private String credit_rate;
	
	private String pro_sort;
	
	private String category;
	
	private String create_date;
	
	private String enterprise_type;
	
	private Integer employees;
	
	private Double area;
	
	private String enterprise_typename;
	
	private String profile;

	private Set<CBuilding> Buildings;
    
	private Set<CEnterpriseBuildingRel> enterpriseBuildingRels;
	
	private Set<User> users;
    
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Integer getEmployees() {
		return employees;
	}

	public void setEmployees(Integer employees) {
		this.employees = employees;
	}

	

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getEnterprise_type() {
		return enterprise_type;
	}

	public void setEnterprise_type(String enterprise_type) {
		this.enterprise_type = enterprise_type;
	}

	public String getEnterprise_typename() {
		return enterprise_typename;
	}

	public void setEnterprise_typename(String enterprise_typename) {
		this.enterprise_typename = enterprise_typename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCredit_rate() {
		return credit_rate;
	}

	public void setCredit_rate(String credit_rate) {
		this.credit_rate = credit_rate;
	}

	public String getPro_sort() {
		return pro_sort;
	}

	public void setPro_sort(String pro_sort) {
		this.pro_sort = pro_sort;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public Set<CBuilding> getBuildings() {
		return Buildings;
	}

	public void setBuildings(Set<CBuilding> buildings) {
		Buildings = buildings;
	}
    
	
	public Set<CEnterpriseBuildingRel> getEnterpriseBuildingRels() {
		return enterpriseBuildingRels;
	}

	public void setEnterpriseBuildingRels(Set<CEnterpriseBuildingRel> enterpriseBuildingRels) {
		this.enterpriseBuildingRels = enterpriseBuildingRels;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
    
}