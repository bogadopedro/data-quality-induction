grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
        // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
        //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

        // configure settings for the test-app JVM, uses the daemon by default
        test   : [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon: true],
        // configure settings for the run-app JVM
        run    : [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve: false],
                // configure settings for the Console UI JVM
        console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        mavenCentral()
        grailsCentral()

        // Meli repos:
        mavenRepo "http://git.ml.com:8081/nexus/content/repositories/MLGrailsPlugins"
        mavenRepo "http://git.ml.com:8081/nexus/content/groups/Arquitectura"
        mavenRepo "http://git.ml.com:8081/nexus/content/groups/ML"
        mavenRepo "http://git.ml.com:8081/nexus/content/groups/public"
    }

    dependencies {
        compile 'org.codehaus.jackson:jackson-mapper-asl:1.9.13'
        compile group: 'redis.clients', name: 'jedis', version: '2.9.0'

        test "org.grails:grails-datastore-test-support:1.0.2-grails-2.4"
        test 'com.fiftyonred:mock-jedis:0.4.0'

    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.70" // or ":tomcat:8.0.22"
//
        compile ':mongodb:3.0.3'
        compile ":mlapi:2.4.40"
        // plugins needed at runtime but not for compilation
        runtime ":hibernate4:4.3.10" // or ":hibernate:3.6.10.18"
        runtime ":database-migration:1.4.0"
        compile 'org.grails.plugins:rest-client-builder:2.1.1'

    }
}
