#!/usr/bin/env ruby
require 'js_base'

HOME_DIR = File.expand_path('~')
TOMCAT_DIR = File.join(HOME_DIR,"tomcat")
WEBAPPS_DIR = File.join(TOMCAT_DIR,"webapps")

die "No webapps directory at #{WEBAPPS_DIR}" if !File.directory?(WEBAPPS_DIR)

puts "Building servlet .war file, copying to tomcat directory"

scall("gradle war -q")
FileUtils.cp("build/libs/Sample.war",WEBAPPS_DIR)

puts
puts "Servlet can be found at: http://localhost:8080/Sample"
puts
puts " (if not found, make sure tomcat is running; try 'catalina start')"
