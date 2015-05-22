#!/usr/bin/env ruby
require 'js_base'

HOME_DIR = File.expand_path('~')
TOMCAT_DIR = File.join(HOME_DIR,"tomcat")

puts "Building servlet .war file, copying to tomcat directory"

scall("gradle war -q")
scall("cp build/libs/Sample.war #{TOMCAT_DIR}/webapps")

puts
puts "Servlet can be found at: http://localhost:8080/Sample"
puts
puts " (if not found, make sure tomcat is running; try 'catalina start')"
