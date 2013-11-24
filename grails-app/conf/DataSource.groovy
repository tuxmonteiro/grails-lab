dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
}

// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://localhost/grails1?useUnicode=yes&characterEncoding=UTF-8"
			username = "root"
			password = ""
		}
		hibernate {
			show_sql = true
		}
	}
	test {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://localhost/grails1?useUnicode=yes&characterEncoding=UTF-8"
			username = "root"
			password = ""
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost/grails1?useUnicode=yes&characterEncoding=UTF-8"
			username = "root"
			password = ""
		}
	}
}
