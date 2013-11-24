package org.algosmart

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	boolean admin = false
	
	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def setRoleDefault() {
		def defaultRole = Role.findByAuthority(Role.defaultAuthority)
		if (this.authorities.isEmpty()) {
			UserRole.create this, defaultRole, true
		}
	}
	
	def setRoleAdmin() {
		def adminRole = Role.findByAuthority(Role.adminAuthority)
		UserRole.create this, adminRole, true
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
