#!/usr/bin/env ruby
require 'js_base'

HOME_DIR = File.expand_path('~')
TOMCAT_DIR = File.join(HOME_DIR,"tomcat")
WEBAPPS_DIR = File.join(TOMCAT_DIR,"webapps")

die "No webapps directory at #{WEBAPPS_DIR}" if !File.directory?(WEBAPPS_DIR)

puts "Building servlet .war file, copying to Tomcat directory"

scall("gradle war -q")
FileUtils.cp("build/libs/Sample.war",WEBAPPS_DIR)

puts
puts "Wicket example:             http://localhost:8080/Sample"
puts
puts " ...to monitor System.out:  tail -F ~/tomcat/logs/catalina.out &"

# Restart Tomcat; clear log file
scall("catalina stop",false)
log_file = File.join(TOMCAT_DIR,"logs/catalina.out")
FileUtils.rm_f(log_file)
scall("catalina start")

