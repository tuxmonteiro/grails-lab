import org.algosmart.Role
import org.algosmart.User
import org.algosmart.UserRole
import org.algosmart.RequestMap

class BootStrap {
	
	def init = { servletContext ->
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?:
						new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = Role.findByAuthority('ROLE_USER') ?:
						new Role(authority: 'ROLE_USER').save(flush: true)

		def adminUser = User.findByUsername('admin') ?: new User(
							username: 'admin',
							password: 'admin',
							enabled: true).save(flush: true)

		if (!adminUser.authorities.contains(adminRole)) {
		    UserRole.create adminUser, adminRole, true
		}

		if (RequestMap.count() == 0) {
			for (String url in [
				'/',
				'/index',
				'/index.gsp',
				'/**/favicon.ico',
				'/**/js/**',
				'/**/css/**',
				'/**/images/**',
				'/login',
				'/login.*',
				'/login/*',
				'/logout',
				'/logout.*',
				'/logout/*'
			]) {
				new RequestMap(url: url, configAttribute: 'permitAll').save(flush: true)
			}
			new RequestMap(url: '/user/**',
				configAttribute: 'ROLE_ADMIN,IS_AUTHENTICATED_FULLY').save(flush: true)
			new RequestMap(url: '/**',
				 configAttribute: 'ROLE_USER,IS_AUTHENTICATED_FULLY').save(flush: true)
		}

	}
	
	def destroy = {
		
	}
}
