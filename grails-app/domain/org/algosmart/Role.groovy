package org.algosmart

class Role {

	static String defaultAuthority = 'ROLE_USER'
	static String adminAuthority = 'ROLE_ADMIN'

	String authority = defaultAuthority
	
	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
